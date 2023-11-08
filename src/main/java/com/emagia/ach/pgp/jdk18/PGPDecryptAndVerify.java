package com.emagia.ach.pgp.jdk18;

import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;

import java.io.*;
import java.security.Security;

public class PGPDecryptAndVerify {

    public static void decryptAndVerify(String encryptedFile, String privateKeyFile, String publicKeyFile, char[] passphrase) throws IOException, PGPException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        try (FileInputStream privateKeyInput = new FileInputStream(privateKeyFile);
             FileInputStream publicKeyInput = new FileInputStream(publicKeyFile);
             FileInputStream encryptedInput = new FileInputStream(encryptedFile)) {

            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(privateKeyInput), new org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator());
            PGPSecretKey secretKey = pgpSec.getKeyRings().next().getSecretKey();

            PGPPublicKeyRingCollection pgpRing = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(publicKeyInput), new org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator());

            InputStream in = PGPUtil.getDecoderStream(encryptedInput);
            PGPObjectFactory pgpF = new PGPObjectFactory(in, new org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator());

            Object message;
            while ((message = pgpF.nextObject()) != null) {
                if (message instanceof PGPEncryptedDataList) {
                    PGPEncryptedDataList enc = (PGPEncryptedDataList) message;
                    PGPPublicKeyEncryptedData pbe = (PGPPublicKeyEncryptedData) enc.get(0);

                    PGPPrivateKey privateKey = secretKey.extractPrivateKey(new BcPBESecretKeyDecryptorBuilder(new JcaPGPDigestCalculatorProviderBuilder().build()).build(passphrase));

                    InputStream clear = pbe.getDataStream(new BcPublicKeyDataDecryptorFactory(privateKey));

                    PGPObjectFactory plainFact = new PGPObjectFactory(clear, new org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator());
                    Object obj;
                    while ((obj = plainFact.nextObject()) != null) {
                        if (obj instanceof PGPCompressedData) {
                            PGPCompressedData cData = (PGPCompressedData) obj;
                            InputStream compressedData = cData.getDataStream();
                            PGPObjectFactory plainFact2 = new PGPObjectFactory(compressedData, new org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator());

                            Object data;
                            while ((data = plainFact2.nextObject()) != null) {
                                if (data instanceof PGPLiteralData) {
                                    PGPLiteralData ld = (PGPLiteralData) data;
                                    InputStream unc = ld.getInputStream();
                                    // Handle the decrypted content here.
                                } else if (data instanceof PGPOnePassSignatureList) {
                                    // Handle One Pass Signatures if needed.
                                } else {
                                    System.out.println("Unknown object type: " + data.getClass().getName());
                                }
                                // You can handle other packet types if required.
                            }
                        } else {
                            System.out.println("Unknown object type: " + obj.getClass().getName());
                        }
                    }
                } else {
                    System.out.println("Unknown object type: " + message.getClass().getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        String encryptedFile = "src/main/resources/achtest1StaplesEncSigned.asc";
        String privateKeyFile = "src/main/resources/AnilPGP_Private.asc";
        String publicKeyFile = "src/main/resources/Anil2_Public.asc";
//char[] privateKeyPassword = new char[]{'h','a','n','u','m','a','n'};
        char[] passphrase = "hanuman".toCharArray(); // Replace with the passphrase
        try {
            decryptAndVerify(encryptedFile, privateKeyFile, publicKeyFile, passphrase);
        } catch (IOException | PGPException e) {
            e.printStackTrace();
        }
    }
}
