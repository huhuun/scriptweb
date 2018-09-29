package com.yoyu.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;


public class AESManager {

    public static void encryptFile(File tagFile, String pwd) {
        AESUtil.encryptFile(tagFile, "", pwd);
    }

    public static void encryptFile(File tagFile, String encryptFileName, String pwd) {
        AESUtil.encryptFile(tagFile, encryptFileName, pwd);
    }

    public static void decryptFile(File encryptFile, String pwd) {
        AESUtil.decryptFile(encryptFile, "", pwd);
    }

    public static void decryptFile(File encryptFile, String decryptFileName, String pwd) {
        AESUtil.decryptFile(encryptFile, decryptFileName, pwd);
    }

    public static String encrypt(String content, String password) {
        return AESUtil.encrypt(content, password);
    }

    public static String encrypt(byte content[], String password) {
        return AESUtil.encrypt(content, password);
    }

    public static String decrypt(String content, String password) {
        return AESUtil.decrypt(content, password);
    }

    public static InputStream getDecryptStream(File encryptFile, String pwd) {
        return StreamUtil.getDecryptStream(encryptFile, pwd);
    }


    public static InputStream getReadStream(File file) throws FileNotFoundException {
        return StreamUtil.getReadStream(file);
    }

    public static OutputStream getWriteStream(File file) throws FileNotFoundException {
        return StreamUtil.getWriteStream(file);
    }

    public static void writeData(InputStream in, OutputStream out) {
        StreamUtil.writeData(in, out);
    }

    public static Cipher getCipher(int cipherMode, String seed) {
        return StreamUtil.getCipher(cipherMode, seed);
    }

    public static void encryptStream(ByteArrayOutputStream outputStream, File file, String key) throws IOException {
        AESUtil.encryptStream(outputStream, file, key);
    }


}