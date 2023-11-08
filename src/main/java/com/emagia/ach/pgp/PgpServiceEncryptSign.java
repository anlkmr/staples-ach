package com.emagia.ach.pgp;

import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Service
public class PgpServiceEncryptSign {


    public void encryptAndSignData(String inputFilePath, String publicKeyPath, String privateKeyPath, char[] privateKeyPassword, String outputFilePath) throws IOException, PGPException {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));

        // Load recipient's public key
        PGPPublicKey publicKey = readPublicKey(new FileInputStream(publicKeyPath));

        // Load sender's private key
        PGPSecretKey secretKey = readSecretKey(new FileInputStream(privateKeyPath));

        // Encrypt and sign the inputFilePath
        encryptAndSign(inputFilePath, outputStream, publicKey, secretKey, privateKeyPassword);

        outputStream.close();
    }

    private void encryptAndSign(String inputFilePath, OutputStream outputStream, PGPPublicKey publicKey, PGPSecretKey secretKey, char[] privateKeyPassword) throws IOException, PGPException {
        // Create an encrypted data generator

        InputStream inputFileToProcess = new FileInputStream(inputFilePath);
        PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(
                new JcePGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.AES_256)
                        .setWithIntegrityPacket(true)
                        .setSecureRandom(new SecureRandom())
                        .setProvider("BC")
        );
        encryptedDataGenerator.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(publicKey).setProvider("BC"));

        OutputStream encryptedOut = encryptedDataGenerator.open(outputStream, new byte[2048]);

        // Create the signed data generator
        PGPPrivateKey privateKey = secretKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(privateKeyPassword));
        PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(
                new JcaPGPContentSignerBuilder(secretKey.getPublicKey().getAlgorithm(), PGPUtil.SHA1).setProvider("BC")
        );

        signatureGenerator.init(PGPSignature.BINARY_DOCUMENT, privateKey);

        Iterator<String> userIDs = secretKey.getPublicKey().getUserIDs();
        if (userIDs.hasNext()) {
            PGPSignatureSubpacketGenerator subpacketGenerator = new PGPSignatureSubpacketGenerator();
            subpacketGenerator.setSignerUserID(true, userIDs.next());
            signatureGenerator.setHashedSubpackets(subpacketGenerator.generate());
        }

        signatureGenerator.generateOnePassVersion(true).encode(encryptedOut);

        PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator();
        //inputFileToProcess.readAllBytes();
        OutputStream literalOut = literalDataGenerator.open(encryptedOut, PGPLiteralData.BINARY, "", inputFileToProcess.readAllBytes().length, new Date());
        literalOut.write(inputFileToProcess.readAllBytes());
        literalOut.close();

        signatureGenerator.update(inputFileToProcess.readAllBytes());
        signatureGenerator.generate().encode(encryptedOut);

        encryptedOut.close();
    }

    private PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException {
        input = PGPUtil.getDecoderStream(input);


        Collection<PGPPublicKeyRing> incollection = new ArrayList<>();
        PGPPublicKeyRing keyRing1 = new BcPGPPublicKeyRing(input);
        incollection.add(keyRing1);

        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(incollection);

        PGPPublicKey key = null;
        Iterator<PGPPublicKeyRing> keyRings = pgpPub.getKeyRings();

        while (key == null && keyRings.hasNext()) {
            PGPPublicKeyRing keyRing = keyRings.next();
            Iterator<PGPPublicKey> keys = keyRing.getPublicKeys();

            while (key == null && keys.hasNext()) {
                PGPPublicKey k = keys.next();

                if (k.isEncryptionKey()) {
                    key = k;
                }
            }
        }

        if (key == null) {
            throw new IllegalArgumentException("No encryption key found in the provided key ring.");
        }

        return key;
    }

    private PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
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
}
