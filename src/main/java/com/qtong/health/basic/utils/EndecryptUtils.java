package com.qtong.health.basic.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.qtong.health.basic.model.User;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.Key;

/**
 * Created by ZML on 2015/4/22.
 * 备注： shiro进行加密解密的工具类封装
 */
public class EndecryptUtils {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    /**
     * base64进制加密
     *
     * @param password
     * @return
     */
    public static String encrytBase64(String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
        byte[] bytes = password.getBytes();
        return Base64.encodeToString(bytes);
    }

    /**
     * base64进制解密
     *
     * @param cipherText
     * @return
     */
    public static String decryptBase64(String cipherText) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
        return Base64.decodeToString(cipherText);
    }

    /**
     * 16进制加密
     *
     * @param password
     * @return
     */
    public static String encrytHex(String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
        byte[] bytes = password.getBytes();
        return Hex.encodeToString(bytes);
    }

    /**
     * 16进制解密
     *
     * @param cipherText
     * @return
     */
    public static String decryptHex(String cipherText) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
        return new String(Hex.decode(cipherText));
    }

    public static String generateKey() {
        AesCipherService aesCipherService = new AesCipherService();
        Key key = aesCipherService.generateNewKey();
        return Base64.encodeToString(key.getEncoded());
    }

    /**
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中
     *
     * @return 密文和salt
     */
    public static User md5Password(User user) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getUsername()), "username不能为空");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getPassword()), "password不能为空");
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        //组合username,两次迭代，对密码进行加密
        String password_cipherText = new Md5Hash(user.getPassword(), user.getUsername() + salt, 2).toBase64();
        user.setPassword(password_cipherText);
        user.setSalt(salt);
        return user;
    }

    public static void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        EndecryptUtils.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        EndecryptUtils.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        EndecryptUtils.hashIterations = hashIterations;
    }
}

