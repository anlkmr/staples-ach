package com.emagia.ach.pgp;

import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PGPEncryptionExample {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Replace this with the public key file or keyring containing the recipient's public key
        String publicKeyFilePath = "src/main/resources/AnilPGP_Public.asc";

        // Message to be encrypted
        String message = "This is a secret message.";

        // Load the recipient's public key
        PGPPublicKey publicKey = readPublicKey(new FileInputStream(publicKeyFilePath));

        // Create an output stream to write the encrypted data
        ByteArrayOutputStream encryptedOutput = new ByteArrayOutputStream();

        // Encrypt the message
        encryptMessage(message, publicKey, encryptedOutput);

        // Display the encrypted message (you might want to save this to a file)
        System.out.println("Encrypted message: " + new String(encryptedOutput.toByteArray()));
    }

    public static void encryptMessage(String message, PGPPublicKey publicKey, OutputStream outputStream) throws IOException, PGPException {
        // Compressed data flag
        boolean withIntegrityCheck = true;

        // Create an encrypted data generator
        PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(
                new JcePGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.AES_256)
                        .setWithIntegrityPacket(withIntegrityCheck)
                        .setSecureRandom(new SecureRandom())
                        .setProvider("BC")
        );

        // Add the recipient's public key
        encryptedDataGenerator.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(publicKey).setProvider("BC"));

        // Create an output stream with the encrypted data
        OutputStream encryptedOut = encryptedDataGenerator.open(outputStream, new byte[4096]);

        // Write the message to the encrypted output stream
        PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator();
        OutputStream literalOut = literalDataGenerator.open(encryptedOut, PGPLiteralData.BINARY, "", message.getBytes().length, new java.util.Date());
        literalOut.write(message.getBytes());
        literalOut.close();

        // Close the encrypted output stream
        encryptedOut.close();
    }

    public static PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);

        Collection<PGPPublicKeyRing> incollection = new ArrayList<>();
        PGPPublicKeyRing keyRing1 = new BcPGPPublicKeyRing(in);
        incollection.add(keyRing1);
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(incollection);
        PGPPublicKey key = null;

        // Get the first key found
        Iterator keyRingIter = pgpPub.getKeyRings();

        while (key == null && keyRingIter.hasNext()) {
            PGPPublicKeyRing keyRing = (PGPPublicKeyRing) keyRingIter.next();
            Iterator keyIter = keyRing.getPublicKeys();

            while (key == null && keyIter.hasNext()) {
                PGPPublicKey k = (PGPPublicKey) keyIter.next();

                if (k.isEncryptionKey()) {
                    key = k;
                }
            }
        }

        if (key == null) {
            throw new IllegalArgumentException("Can't find encryption key in key ring.");
        }

        return key;
    }
}
