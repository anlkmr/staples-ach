package com.emagia.ach;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;

public class BouncyCastleSignStringExample {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Create a signature
        Signature signature = Signature.getInstance("SHA256withRSA", "BC");
        signature.initSign(privateKey);

        // Data to be signed
        String message = "Hello, this is a message to be signed.";
        byte[] data = message.getBytes();

        // Update the signature with the data
        signature.update(data);

        // Generate the signature
        byte[] digitalSignature = signature.sign();

        // Verify the signature
        Signature verifySignature = Signature.getInstance("SHA256withRSA", "BC");
        verifySignature.initVerify(publicKey);
        verifySignature.update(data);

        boolean isVerified = verifySignature.verify(digitalSignature);

        // Print the verification result
        if (isVerified) {
            System.out.println("Signature verified successfully!");
        } else {
            System.out.println("Failed to verify the signature.");
        }
    }
}
