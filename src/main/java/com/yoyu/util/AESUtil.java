package com.yoyu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;

public class AESUtil {


    protected static void encryptStream(ByteArrayOutputStream outputStream, File file, String key) throws IOException {
        if (outputStream == null || file == null) {
            return;
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        CipherInputStream cin = new CipherInputStream(inputStream, StreamUtil.getCipher(Cipher.ENCRYPT_MODE, key));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        StreamUtil.writeData(cin, fileOutputStream);
    }


    protected static void encryptFile(File tagFile, String encryptFileName, String pwd) {

        if (tagFile == null || pwd.equals("")) {
            return;
        }
        String path = "";
        if (!tagFile.exists() || tagFile.isDirectory()) {
            return;
        }
        path = tagFile.getPath();
        if (path.equals("")) {
            return;
        }
        String necrytpFilePath = StreamUtil.buildFileName(tagFile, encryptFileName, Cipher.ENCRYPT_MODE, 0);
        try {
            File necryptFile = new File(necrytpFilePath);
            necryptFile.createNewFile();
            FileInputStream inputStream = (FileInputStream) StreamUtil.getReadStream(tagFile);
            FileOutputStream outputStream = (FileOutputStream) StreamUtil.getWriteStream(necryptFile);
            CipherInputStream cin = new CipherInputStream(inputStream, StreamUtil.getCipher(Cipher.ENCRYPT_MODE, pwd));
            StreamUtil.writeData(cin, outputStream);
            necryptFile.setReadable(true);
            necryptFile.setReadOnly();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void decryptFile(File encryptFile, String decryptFileName, String pwd) {
        String decryptFilePath = StreamUtil.buildFileName(encryptFile, decryptFileName, Cipher.DECRYPT_MODE, 0);
        File decryptFile = new File(decryptFilePath);
        try {
            decryptFile.createNewFile();
            ByteArrayInputStream inputStream = (ByteArrayInputStream) StreamUtil.getDecryptStream(encryptFile, pwd);
            FileOutputStream outputStream = (FileOutputStream) StreamUtil.getWriteStream(decryptFile);
            StreamUtil.writeData(inputStream, outputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String encrypt(String content, String password) {
        try {
            return encrypt(content.getBytes("UTF-8"), password);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    protected static String encrypt(byte content[], String password) {
        try {
            Cipher cipher = StreamUtil.getCipher(Cipher.ENCRYPT_MODE, password);
            if (cipher == null) {
                return null;
            }
            byte[] byteContent = content;
            byte result[] = cipher.doFinal(byteContent);
            return StreamUtil.parseByte2HexStr(result);
        } catch (IllegalBlockSizeException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        }
    }

    protected static String decrypt(String content, String password) {
        try {
            Cipher cipher = StreamUtil.getCipher(Cipher.DECRYPT_MODE, password);
            if (cipher == null) {
                return null;
            }
            byte[] decryptFrom = StreamUtil.parseHexStr2Byte(content);
            if (decryptFrom == null) {
                return null;
            }
            byte[] result = cipher.doFinal(decryptFrom);
            return new String(result, "UTF-8");
        } catch (IllegalBlockSizeException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}