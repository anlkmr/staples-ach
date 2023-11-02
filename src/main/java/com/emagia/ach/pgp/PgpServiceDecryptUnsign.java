package com.emagia.ach.pgp;

import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPPrivateKey;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class PgpServiceDecryptUnsign {

    public void decryptAndVerifyData(String inputFilePath, String publicKeyPath, String privateKeyPath, char[] privateKeyPassword, String outputFilePath) throws IOException, PGPException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));

        // Load sender's public key
        PGPPublicKey publicKey = readPublicKey(new FileInputStream(publicKeyPath));

        // Load recipient's private key
        PGPSecretKey secretKey = readSecretKey(new FileInputStream(privateKeyPath));

        // Decrypt and verify the message
        decryptAndVerify(inputStream, outputStream, publicKey, secretKey, privateKeyPassword);

        inputStream.close();
        outputStream.close();
    }

    private void decryptAndVerify(InputStream inputStream, OutputStream outputStream, PGPPublicKey publicKey, PGPSecretKey secretKey, char[] privateKeyPassword) throws IOException, PGPException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        InputStream decoderStream = PGPUtil.getDecoderStream(inputStream);
        PGPObjectFactory pgpFactory = new PGPObjectFactory(decoderStream, new JcaKeyFingerprintCalculator());

        PGPPublicKeyEncryptedData encryptedDataInfo = null;
        PGPOnePassSignature signature = null;
        PGPLiteralData literalData = null;

        Object nextObject;
        while ((nextObject = pgpFactory.nextObject()) != null) {
            //if (nextObject instanceof PGPPublicKeyEncryptedData) {
            PGPEncryptedDataList encryptedDataInfoList = (PGPEncryptedDataList) nextObject;
                encryptedDataInfo = (PGPPublicKeyEncryptedData) encryptedDataInfoList.get(0);
            //    break;
            //}
        }

        if (encryptedDataInfo == null) {
            throw new IllegalArgumentException("No encrypted data found in the provided file.");
        }


       // PBESecretKeyDecryptor pbeSecretKeyDecryptor = new MySecretKeyDecryptor()
        PGPPrivateKey pgpPrivateKey = secretKey.extractPrivateKey(
                new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(privateKeyPassword)
        );
        //PGPPrivateKey pgpPrivateKey = new JcaPGPPrivateKey();
        InputStream decryptedStream = encryptedDataInfo.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(pgpPrivateKey));//secretKey.extractPrivateKey(pbeSecretKeyDecryptor)

        PGPObjectFactory plainFact = new PGPObjectFactory(decryptedStream, new JcaKeyFingerprintCalculator());

        Object message = plainFact.nextObject();
        if (message instanceof PGPOnePassSignatureList) {
            PGPOnePassSignatureList sigList = (PGPOnePassSignatureList) message;
            signature = sigList.get(0);
        }

        if (signature == null) {
            throw new IllegalArgumentException("No signature found in the provided file.");
        }

        literalData = (PGPLiteralData) plainFact.nextObject();
        InputStream literalDataStream = literalData.getInputStream();

        byte[] buffer = new byte[2048];
        int bytesRead;
        System.out.println(buffer);
        while ((bytesRead = literalDataStream.read(buffer)) != -1) {

            outputStream.write(buffer, 0, bytesRead);
           // signature.update(buffer, 0, bytesRead);
        }

        literalDataStream.close();
        outputStream.close();

        PGPPublicKey publicKeySignature = publicKey;
        Iterator<PGPSignature> subPackets = publicKeySignature.getSignatures();
        //TODO

        /*if (signature.verify(subPackets.next())) {
            System.out.println("Signature verified");
        } else {
            throw new PGPException("Signature verification failed");
        }*/
    }

    private PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        Collection<PGPPublicKeyRing> incollection = new ArrayList<>();
        PGPPublicKeyRing keyRing1 = new BcPGPPublicKeyRing(in);
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

    private PGPSecretKey readSecretKey(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);

        Collection<PGPSecretKeyRing> incollection = new ArrayList<>();
        KeyFingerPrintCalculator keyFingerCalculator = new JcaKeyFingerprintCalculator();
        PGPSecretKeyRing keyRing1 = new PGPSecretKeyRing (in,keyFingerCalculator);
        incollection.add(keyRing1);
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
