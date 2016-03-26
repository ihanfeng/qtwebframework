package com.qtong.core.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.qtong.core.model.User;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.Key;

/**
 * 加密工具类
 */
@SuppressWarnings("all")
public class EndecryptUtils {
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
     * @param username 用户名
     * @param password 密码
     * @return 密文和salt
     */
    public static User md5Password(String username, String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "username不能为空");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "password不能为空");
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        //组合username,两次迭代，对密码进行加密
        String password_cipherText = new Md5Hash(password, username + salt, 2).toBase64();
        User user = new User();
        user.setPassword(password_cipherText);
        user.setSalt(salt);
        user.setUsername(username);
        return user;
    }

    public static boolean checkLoginUser(User loginUser, User dbUser) {

        Preconditions.checkArgument(loginUser != null, "登录用户不能为空");

        if (dbUser == null) {
            return false;
        }
        /*登录名为空，登录密码为空，两个用户名不一致*/
        if (loginUser.getUsername() == null || loginUser.getPassword() == null || !loginUser.getUsername().equals(dbUser.getUsername())) {
            return false;
        }
        /*开发测试的时候方便起见，允许测试帐号密码为空*/
        if (dbUser.getPassword() == null || dbUser.getPassword().equals("")) {
            return true;
        }
        if (new Md5Hash(loginUser.getPassword(), loginUser.getUsername() + loginUser.getSalt(), 2).toBase64().equals(dbUser.getPassword())) {
            return true;
        }

        return false;
    }

    ;
}
