package com.ciba.datasynchronize.coder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
/**
 * Created by lizhlin83 on 2018/1/31.
 */
public class AES {
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public SecretKey geneKey() throws Exception {
        //获取一个密钥生成器实例
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public String aesCipher(String content, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(content.getBytes("UTF-8"));
            return PublicKey.encryptBASE64(result);
        } catch (Exception e) {
            return null;
        }
    }
}
