package com.ciba.datasynchronize.coder;

import android.util.Base64;

import org.json.JSONObject;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * Created by lizhlin83 on 2017/12/27.
 */
public class PublicKey {
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9AnMx0xJR5Oy/7k0MPedEsYLv3U3iRue/+GyqBEH4rQB6rKp54NeKr8B5kZWx0KvRjlnEyz44pMc495ZTsr2gJwjPRPIUVfmLQuB6qXOngf5O2E5X9YpXPKURi2UWzpVabHiD1nD7tJoyE8HMYCa7zQOaG45oJOXLBOPpFdppPQIDAQAB";
    private static final String KEY_ALGORITHM = "RSA";

    private PublicKey() {
    }

    public static String keyboards(String value) {
        try {
            AES aes = new AES();
            SecretKey secretKey = aes.geneKey();
            byte[] encoded = secretKey.getEncoded();
            String raw = encryptBASE64(encoded);
            String aesCipher = aes.aesCipher(value, secretKey);
            byte[] bytes = encryptByPublicKey(raw, PUBLIC_KEY);
            String key = encryptBASE64(bytes);
            JSONObject object = new JSONObject();
            object.put("key", key);
            object.put("content", aesCipher);
            String line = object.toString();
            return encryptBASE64(line.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    public static byte[] decryptBASE64(String key) {
        return Base64.decode(key.trim(), Base64.DEFAULT);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
