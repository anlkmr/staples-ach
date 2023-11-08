package com.emagia.ach.pgp.jdk18;
import com.emagia.ach.pgp.PgpEncryption;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;

import java.io.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class FileSigner {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        String inputFilePath="achtest1StaplesCommercialLLC.ach";
        PgpEncryption pgpEncryption = new PgpEncryption();
        pgpEncryption.encrypt(inputFilePath, "src/main/resources/encrypted/achtest1StaplesEnc.pgp");

        // Load the private key for signing
        PGPSecretKey secretKey = readSecretKey(new FileInputStream("src/main/resources/Sign/emaigakeys.asc"));

        // File to sign
        File fileToSign = new File("src/main/resources/encrypted/achtest1StaplesEnc.pgp");
        char[] privateKeyPassword = new char[]{'h','a','n','u','m','a','n'};
        PGPPrivateKey privateKey = secretKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(privateKeyPassword));

        // Sign the file
        byte[] signature = signFile(fileToSign, privateKey, secretKey);

        // Save the signature to a file or handle it as needed
        try (FileOutputStream signatureOut = new FileOutputStream("src/main/resources/signedEncrypted/achtest1StaplesEncSigned.ach.asc")) {
            ArmoredOutputStream armoredOutputStream = new ArmoredOutputStream(signatureOut);
            armoredOutputStream.write(signature);
            armoredOutputStream.close();
        }
    }

     static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
        // in = PGPUtil.getDecoderStream(in);

        input = PGPUtil.getDecoderStream(input);

        Collection<PGPSecretKeyRing> incollection = new ArrayList<>();
        KeyFingerPrintCalculator keyFingerCalculator = new JcaKeyFingerprintCalculator();
        PGPSecretKeyRing keyRing1 = new PGPSecretKeyRing (input,keyFingerCalculator);
        incollection.add(keyRing1);
        //PGPPublicKeyRingCollection pgpSec = new PGPPublicKeyRingCollection(incollection);


        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(incollection);

        PGPSecretKey key = null;
        Iterator<PGPSecretKeyRing> keyRings = pgpSec.getKeyRings();

        while (key == null && keyRings.hasNext()) {
            PGPSecretKeyRing keyRing = keyRings.next();
            Iterator<PGPSecretKey> keys = keyRing.getSecretKeys();

            while (key == null && keys.hasNext()) {
                PGPSecretKey k = keys.next();

                if (k.isSigningKey()) {
                    key = k;
                }
            }
        }

        if (key == null) {
            throw new IllegalArgumentException("No signing key found in the provided key ring.");
        }

        return key;
    }

    public static PGPPrivateKey readPrivateKey(InputStream input, String passphrase) throws IOException, PGPException {
        input = PGPUtil.getDecoderStream(input);

        Collection<PGPSecretKeyRing> incollection = new ArrayList<>();
        KeyFingerPrintCalculator keyFingerCalculator = new JcaKeyFingerprintCalculator();
        PGPSecretKeyRing keyRing1 = new PGPSecretKeyRing (input,keyFingerCalculator);
        incollection.add(keyRing1);
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(incollection);

        PGPSecretKey secretKey = null;

        java.util.Iterator<PGPSecretKeyRing> keyRingIter = pgpSec.getKeyRings();
        while (secretKey == null && keyRingIter.hasNext()) {
            PGPSecretKeyRing keyRing = keyRingIter.next();
            java.util.Iterator<PGPSecretKey> keyIter = keyRing.getSecretKeys();
            while (secretKey == null && keyIter.hasNext()) {
                PGPSecretKey k = keyIter.next();
                if (k.isSigningKey()) {
                    secretKey = k;
                }
            }
        }

        if (secretKey == null) {
            throw new IllegalArgumentException("No secret key found for signing.");
        }

        PBESecretKeyDecryptor decryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(passphrase.toCharArray());

        return secretKey.extractPrivateKey(decryptor);
    }

    public static byte[] signFile(File file, PGPPrivateKey privateKey,PGPSecretKey secretKey) throws IOException, PGPException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(
                new JcaPGPContentSignerBuilder(secretKey.getPublicKey().getAlgorithm(), PGPUtil.SHA256).setProvider("BC")
        );
        //PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator();
        //PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(PGPPublicKey.RSA_GENERAL, HashAlgorithmTags.SHA256));

        signatureGenerator.init(PGPSignature.BINARY_DOCUMENT, privateKey);

        PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator();
        OutputStream literalOut = literalDataGenerator.open(outputStream, PGPLiteralData.BINARY, file.getName(), file.length(), new Date());
        FileInputStream fileInput = new FileInputStream(file);
        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = fileInput.read(buffer)) != -1) {
            signatureGenerator.update(buffer, 0, bytesRead);
            literalOut.write(buffer, 0, bytesRead);
        }
        literalDataGenerator.close();
        fileInput.close();

        PGPSignature signature = signatureGenerator.generate();
        signature.encode(outputStream);
        return outputStream.toByteArray();
    }
}
