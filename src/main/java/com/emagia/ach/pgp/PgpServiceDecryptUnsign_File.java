/*
package com.emagia.ach.pgp;

import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.bc.BcPGPSecretKeyRing;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBEDataDecryptorFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.bouncycastle.cert.DeltaCertificateTool.signature;

@Service
public class PgpServiceDecryptUnsign_File {

    public void decryptAndVerifyData(String inputFilePath, String privateKeyPath, char[] privateKeyPassword, String outputFilePath) throws IOException, PGPException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));

        // Load recipient's private key
        PGPSecretKey secretKey = readSecretKey(new FileInputStream(privateKeyPath));

        // Decrypt and verify the message
        decryptAndVerify(inputStream, outputStream, secretKey, privateKeyPassword);

        inputStream.close();
        outputStream.close();
    }

    private void decryptAndVerify(InputStream inputStream, OutputStream outputStream, PGPSecretKey secretKey, char[] privateKeyPassword) throws IOException, PGPException {
        inputStream = PGPUtil.getDecoderStream(inputStream);
        PGPObjectFactory pgpFactory = new PGPObjectFactory(inputStream, new JcaKeyFingerprintCalculator());

        Object message = pgpFactory.nextObject();
        if (message instanceof PGPEncryptedDataList) {
            PGPEncryptedDataList encryptedDataList = (PGPEncryptedDataList) message;
            PGPPBEEncryptedData encryptedData = (PGPPBEEncryptedData) encryptedDataList.get(0);

            InputStream decryptedStream = encryptedData.getDataStream(new JcePBEDataDecryptorFactoryBuilder(new JcaPGPDigestCalculatorProviderBuilder().build())
                    .setProvider("BC")
                    .build(privateKeyPassword));

            PGPObjectFactory plainFact = new PGPObjectFactory(decryptedStream, new JcaKeyFingerprintCalculator());

            Object nextObject;
            while ((nextObject = plainFact.nextObject()) != null) {
                if (nextObject instanceof PGPOnePassSignatureList) {
                    PGPOnePassSignatureList signatureList = (PGPOnePassSignatureList) nextObject;
                    PGPOnePassSignature signature = signatureList.get(0);
                    PGPPublicKey publicKey = findPublicKey(secretKey.getPublicKey().getKeyID());

                    signature.init(new JcaPGPContentVerifierBuilderProvider().setProvider("BC"), publicKey);
                } else if (nextObject instanceof PGPLiteralData) {
                    PGPLiteralData literalData = (PGPLiteralData) nextObject;
                    InputStream dataStream = literalData.getInputStream();
                    int ch;

                    */
/*while ((ch = dataStream.read()) >= 0) {
                        outputStream.write(ch);
                        if (signature != null) {
                            signature.update((byte) ch);
                        }
                    }*//*

                }
            }

            */
/*if (signature != null && signature.verify()) {
                System.out.println("Signature verified");
            } else {
                throw new PGPException("Signature verification failed");
            }*//*

            decryptedStream.close();
        } else {
            throw new PGPException("No encrypted data found in the provided file.");
        }
    }

    private PGPSecretKey readSecretKey(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);

        Collection<PGPSecretKeyRing> incollection = new ArrayList<>();
        PGPSecretKeyRing keyRing1 = new BcPGPSecretKeyRing(in);
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

    private PGPPublicKey findPublicKey(long keyID) throws PGPException, FileNotFoundException {
        // Here you should find the public key based on the key ID in your keyrings
        // This function should locate the public key that corresponds to the provided key ID
        // Replace this logic with your actual method to find the public key

        // For example (this is a placeholder, replace with your logic):
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection();
        PGPPublicKey key = pgpPub.getPublicKey(keyID);
        if (key != null) {
            return key;
        } else {
            throw new PGPException("Public key not found");
        }
    }
}
*/
