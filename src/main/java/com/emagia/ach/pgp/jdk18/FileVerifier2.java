package com.emagia.ach.pgp.jdk18;

import com.emagia.ach.pgp.PgpEncryption;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;

import java.io.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FileVerifier2 {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Load the public key for verification
        PGPPublicKey publicKey = readPublicKey(new FileInputStream("src/main/resources/DeSign/emaigakeys.asc"));

        // File to verify
        File fileToVerify = new File("src/main/resources/signedEncrypted/achtest1StaplesEncSigned.ach.pgp");

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

    public static boolean verifyFile(File file, PGPPublicKey publicKey) throws IOException, PGPException {
        InputStream fileInputStream = new BufferedInputStream(new FileInputStream(file));

        PGPObjectFactory objectFactory = new PGPObjectFactory(PGPUtil.getDecoderStream(fileInputStream), new JcaKeyFingerprintCalculator());

        PGPSignatureList signatureList;
       // Object object;
        /*while ((object = objectFactory.nextObject()) != null) {
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
        }*/

        Object object = objectFactory.nextObject();
            if (object instanceof PGPSignatureList) {
                 signatureList = (PGPSignatureList) object;
            }else{
                signatureList = (PGPSignatureList) objectFactory.nextObject();
            }
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

        fileInputStream.close();
        return false;
    }



    /*public static void decryptAndVerify(InputStream in, OutputStream fOut, InputStream publicKeyIn) throws IOException, SignatureException, PGPException {
        in = PGPUtil.getDecoderStream(in);

        PGPObjectFactory pgpF = new PGPObjectFactory(in);
        PGPEncryptedDataList enc;

        Object o = pgpF.nextObject();
        //
        // the first object might be a PGP marker packet.
        //
        if (o instanceof PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }

        //
        // find the secret key
        //
        Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData pbe = null;
        while (sKey == null && it.hasNext()) {
            pbe = it.next();
            PBESecretKeyDecryptor decryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(INSTANCE._secretKeyPass.toCharArray());
            PGPSecretKey psKey = INSTANCE._secretKeyRingCollection.getSecretKey(pbe.getKeyID());
            if (psKey != null) {
                sKey = psKey.extractPrivateKey(decryptor);
            }
        }
        if (sKey == null) {
            throw new IllegalArgumentException("Unable to find secret key to decrypt the message");
        }

        InputStream clear = pbe.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));

        PGPObjectFactory plainFact = new PGPObjectFactory(clear);

        Object message;

        PGPOnePassSignatureList onePassSignatureList = null;
        PGPSignatureList signatureList = null;
        PGPCompressedData compressedData;

        message = plainFact.nextObject();
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        while (message != null) {
            __l.trace(message.toString());
            if (message instanceof PGPCompressedData) {
                compressedData = (PGPCompressedData) message;
                plainFact = new PGPObjectFactory(compressedData.getDataStream());
                message = plainFact.nextObject();
            }

            if (message instanceof PGPLiteralData) {
                // have to read it and keep it somewhere.
                Streams.pipeAll(((PGPLiteralData) message).getInputStream(), actualOutput);
            } else if (message instanceof PGPOnePassSignatureList) {
                onePassSignatureList = (PGPOnePassSignatureList) message;
            } else if (message instanceof PGPSignatureList) {
                signatureList = (PGPSignatureList) message;
            } else {
                throw new PGPException("message unknown message type.");
            }
            message = plainFact.nextObject();
        }
        actualOutput.close();
        PGPPublicKey publicKey = null;
        byte[] output = actualOutput.toByteArray();
        if (onePassSignatureList == null || signatureList == null) {
            throw new PGPException("Poor PGP. Signatures not found.");
        } else {

            for (int i = 0; i < onePassSignatureList.size(); i++) {
                PGPOnePassSignature ops = onePassSignatureList.get(0);
                __l.trace("verifier : " + ops.getKeyID());
                PGPPublicKeyRingCollection pgpRing = new PGPPublicKeyRingCollection(
                        PGPUtil.getDecoderStream(publicKeyIn));
                publicKey = pgpRing.getPublicKey(ops.getKeyID());
                if (publicKey != null) {
                    ops.init(new JcaPGPContentVerifierBuilderProvider().setProvider("BC"), publicKey);
                    ops.update(output);
                    PGPSignature signature = signatureList.get(i);
                    if (ops.verify(signature)) {
                        Iterator<?> userIds = publicKey.getUserIDs();
                        while (userIds.hasNext()) {
                            String userId = (String) userIds.next();
                            __l.trace(String.format("Signed by {%s}", userId));
                        }
                        __l.trace("Signature verified");
                    } else {
                        throw new SignatureException("Signature verification failed");
                    }
                }
            }

        }

        if (pbe.isIntegrityProtected() && !pbe.verify()) {
            throw new PGPException("Data is integrity protected but integrity is lost.");
        } else if (publicKey == null) {
            throw new SignatureException("Signature not found");
        } else {
            fOut.write(output);
            fOut.flush();
            fOut.close();
        }
    }
*/

}
