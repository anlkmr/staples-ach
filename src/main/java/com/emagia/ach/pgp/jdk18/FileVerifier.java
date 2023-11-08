package com.emagia.ach.pgp.jdk18;

import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;

import java.io.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;

public class FileVerifier {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Load the public key for verification
        PGPPublicKey publicKey = readPublicKey(new FileInputStream("src/main/resources/DeSign/emaigakeys.asc"));

        // File to verify
        File fileToVerify = new File("signature.asc");

        // Load the signature to verify
        byte[] signature = readSignature(new FileInputStream("signature.asc"));

        // Verify the file's signature
        boolean verified = verifyFile(fileToVerify, publicKey);

        if (verified) {
            System.out.println("Signature verified: File has not been tampered with.");
        } else {
            System.out.println("Signature verification failed: File may have been altered.");
        }
    }

    public static PGPPublicKey readPublicKey(InputStream keyIn) throws IOException, PGPException {


        keyIn = PGPUtil.getDecoderStream(keyIn);
        Collection<PGPPublicKeyRing> incollection = new ArrayList<>();
        PGPPublicKeyRing keyRing1 = new BcPGPPublicKeyRing(keyIn);
        incollection.add(keyRing1);
        
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(incollection);

        PGPPublicKey publicKey = null;

        java.util.Iterator<PGPPublicKeyRing> keyRingIter = pgpPub.getKeyRings();
        while (publicKey == null && keyRingIter.hasNext()) {
            PGPPublicKeyRing keyRing = keyRingIter.next();
            java.util.Iterator<PGPPublicKey> keyIter = keyRing.getPublicKeys();
            while (publicKey == null && keyIter.hasNext()) {
                PGPPublicKey k = keyIter.next();
                if (k.isEncryptionKey()) {
                    publicKey = k;
                }
            }
        }

        if (publicKey == null) {
            throw new IllegalArgumentException("No encryption key found in the key ring.");
        }

        return publicKey;
    }

    public static byte[] readSignature(InputStream signatureIn) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int ch;
        while ((ch = signatureIn.read()) >= 0) {
            baos.write(ch);
        }
        return baos.toByteArray();
    }



    public static boolean verifyFile(File file, PGPPublicKey publicKey) throws IOException, PGPException {
        InputStream fileInputStream = new BufferedInputStream(new FileInputStream(file));

        PGPObjectFactory objectFactory = new PGPObjectFactory(PGPUtil.getDecoderStream(fileInputStream), new JcaKeyFingerprintCalculator());

        Object object = objectFactory.nextObject();
        while (object != null) {
            if (object instanceof PGPSignatureList) {
                PGPSignatureList signatureList = (PGPSignatureList) object;
                for (int i = 0; i < signatureList.size(); i++) {
                    PGPSignature signature = signatureList.get(i);
                    if (signature.getKeyID() == publicKey.getKeyID()) {
                        signature.init(new JcaPGPContentVerifierBuilderProvider().setProvider("BC"), publicKey);
                        byte[] buffer = new byte[2048];
                        int bytesRead;
                        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                            signature.update(buffer, 0, bytesRead);
                        }
                        fileInputStream.close();
                        return signature.verify();
                    }
                }
            }
            object = objectFactory.nextObject();
        }
        fileInputStream.close();
        return false;
    }

}
