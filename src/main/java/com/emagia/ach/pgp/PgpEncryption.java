package com.emagia.ach.pgp;
// This is a variation of the example found in the Bouncy Castle Java examples on github.
// I'm using a public key provided by 3rd party to encrypt a file before sending to them. They
// have the private key so it's a one-way system, I have no need for a decrypt method. This is
// being used in a Spring Boot app, hence the annotations.
//

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRing;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PgpEncryption {

    private static final Logger LOG = LoggerFactory.getLogger(PgpEncryption.class);

  //  @Value("${pgp.passphrase}")
  //  private String _passphrases;

   // @Value("D:\\Ravali\\PGPencryption\\0xABA2ADAF-pub.asc")
    @Value("src/main/resources/Sign/staplesKey.asc")
    //@Value("src/main/resources/WF_STAPL656_1.asc")
    private String _keyFile;

    public PgpEncryption() {
        LOG.info("Configuring PGP using Bouncy Castle");
        Security.addProvider(new BouncyCastleProvider());
    }

    public void encrypt(final String inputFile, final String outputFile) throws IOException, PGPException, NoSuchProviderException {
        _keyFile = "src/main/resources/Sign/staplesKey.asc";
        try (final InputStream keyIn = new FileInputStream(_keyFile);
             final OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            encryptFile(out, inputFile, readPublicKey(keyIn));
        }
    }
    
  

    private byte[] compressFile(String fileName, int algorithm) throws IOException {
        final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        final PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));
        comData.close();
        return bOut.toByteArray();
    }

    private static PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException {

        input = PGPUtil.getDecoderStream(input);

        Collection<PGPPublicKeyRing> incollection = new ArrayList<>();
        PGPPublicKeyRing keyRing1 = new BcPGPPublicKeyRing(input);
        incollection.add(keyRing1);
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(incollection);

        //final PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(input));

        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //
        final Iterator keyRingIter = pgpPub.getKeyRings();

        while (keyRingIter.hasNext()) {
            final PGPPublicKeyRing keyRing = (PGPPublicKeyRing) keyRingIter.next();
            final Iterator keyIter = keyRing.getPublicKeys();

            while (keyIter.hasNext()) {
                final PGPPublicKey key = (PGPPublicKey) keyIter.next();

                if (key.isEncryptionKey()) {
                    LOG.info("found {} bit public key", key.getBitStrength());
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find encryption key in key ring.");
    }

    private void encryptFile(final OutputStream out, final String fileName, final PGPPublicKey encKey)
    		throws IOException, NoSuchProviderException {
        try {
            final byte[] bytes = compressFile(fileName, CompressionAlgorithmTags.ZIP);

            final PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                    new JcePGPDataEncryptorBuilder(PGPEncryptedData.AES_256)
                            .setWithIntegrityPacket(true)
                            .setSecureRandom(new SecureRandom())
                            .setProvider("BC"));

            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));

            final OutputStream cOut = encGen.open(out, bytes.length); // the returned output stream is not auto closeable

            cOut.write(bytes);
            cOut.close();
        } catch (final PGPException e) {
            LOG.error("There was a problem encrypting file", e);
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }
    }
    

}