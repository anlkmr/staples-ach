package com.emagia.ach.pgp;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.PBEProtectionRemoverFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;

public class MySecretKeyDecryptor extends PBESecretKeyDecryptor {
    private char[] passphrase;

    protected MySecretKeyDecryptor(char[] chars, PGPDigestCalculatorProvider pgpDigestCalculatorProvider) {
        super(chars, pgpDigestCalculatorProvider);
    }


    @Override
    public byte[] recoverKeyData(int i, byte[] bytes, byte[] bytes1, byte[] bytes2, int i1, int i2) throws PGPException {
        return new byte[0];
    }
}
