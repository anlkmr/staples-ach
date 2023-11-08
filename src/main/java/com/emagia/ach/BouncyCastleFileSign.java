package com.emagia.ach;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class BouncyCastleFileSign {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        String filePath = "achtest1StaplesCommercialLLC.ach";
        String privateKeyPath = "src/main/resources/AnilPGP_Private.asc";
        String signaturePath = "src/main/resources/signed";

        // Read private key from file
        PrivateKey privateKey = readPrivateKey(privateKeyPath);


        // Load the data to sign
        byte[] fileData = readFileData(filePath);

        // Create a signature
        Signature signature = Signature.getInstance("SHA256withRSA", "BC");
        signature.initSign(privateKey);
        signature.update(fileData);

        // Generate the signature
        byte[] digitalSignature = signature.sign();

        // Save the signature to a file
        saveSignatureToFile(digitalSignature, signaturePath);
    }

    private static PrivateKey readPrivateKey(String privateKeyPath) throws Exception {
        // Read private key from file
        try (FileReader fileReader = new FileReader(privateKeyPath);
             PemReader pemReader = new PemReader(fileReader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] keyBytes = pemObject.getContent();

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            return keyFactory.generatePrivate(keySpec);
        }
    }

    private static byte[] readFileData(String filePath) throws IOException {
        // Read file content
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] fileData = new byte[(int) file.length()];
            fis.read(fileData);
            return fileData;
        }
    }

    private static void saveSignatureToFile(byte[] signature, String signaturePath) throws IOException {
        // Write the signature to a file
        try (FileOutputStream fos = new FileOutputStream(signaturePath)) {
            fos.write(signature);
        }
    }
}
