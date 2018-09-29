package com.yoyu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class StreamUtil {

    protected static InputStream getReadStream(File file) throws FileNotFoundException {
        if (file == null) {
            return null;
        }
        if (!file.exists()) {
            return null;
        }
        FileInputStream inputStream = new FileInputStream(file);
        return inputStream;
    }

    protected static OutputStream getWriteStream(File file) throws FileNotFoundException {
        if (file == null) {
            return null;
        }
        if (!file.exists()) {
            return null;
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        return outputStream;
    }

    protected static void writeData(InputStream in, OutputStream out) {
        if (in == null || out == null) {
            return;
        }
        try {
            byte[] cache = new byte[128];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected static Cipher getCipher(int cipherMode, String seed) {
        try {
            Cipher mCipher = Cipher.getInstance("AES/CFB/NoPadding");
            byte[] rawkey = getRawKey(seed);
            SecretKeySpec secretKey = new SecretKeySpec(rawkey, "AES");

            mCipher.init(cipherMode, secretKey, new IvParameterSpec(
                    new byte[mCipher.getBlockSize()]));
            return mCipher;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static byte[] getRawKey(String seed) throws NoSuchAlgorithmException, InvalidKeySpecException {

        /**
         *
         * 密钥的比特位数，注意这里是比特位数
         * AES 支持 128、192 和 256 比特长度的密钥
         * 盐值的字节数组长度，注意这里是字节数组的长度
         * 其长度值需要和最终输出的密钥字节数组长度一致
         * 由于这里密钥的长度是 256 比特，则最终密钥将以 256/8 = 32 位长度的字节数组存在
         * 所以盐值的字节数组长度也应该是 32
         * 先获取一个随机的盐值
         * 你需要将此次生成的盐值保存到磁盘上下次再从字符串换算密钥时传入
         * 如果盐值不一致将导致换算的密钥值不同
         * 保存密钥的逻辑官方并没写，需要自行实现
         * int saltLength = 32;
         * SecureRandom random = new SecureRandom();
         * byte[] salt = new byte[saltLength];
         * random.nextBytes(salt);
         * */

        //为了省事，直接用密码的字节
        int keyLength = 256;
        byte[] salt = seed.getBytes();
        // 将密码明文、盐值等使用新的方法换算密钥
        int iterationCount = 1000;
        KeySpec keySpec = new PBEKeySpec(seed.toCharArray(), salt,
                iterationCount, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA1");
        // 到这里你就能拿到一个安全的密钥了
        byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        return key.getEncoded();
    }

    protected static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    protected static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    protected static String buildFileName(File file, String customer, int fileType, int i) {
        boolean isCustomer = !customer.equals("");
        customer = getTypeName(customer, fileType);
        String path = buildNewFileName(file, customer, isCustomer);
        while (!path.equals("")) {
            File f = new File(path);
            if (f.exists()) {
                path = buildNewFileName(file, customer + "_" + i++, isCustomer);
            } else {
                System.out.println(path);
                return path;
            }
        }
        return "";
    }

    protected static String getTypeName(String customer, int fileType) {
        if (customer.equals("")) {
            customer = "Temp";
            if (fileType == Cipher.ENCRYPT_MODE) {
                customer = "EncryptFile";
            } else if (fileType == Cipher.DECRYPT_MODE) {
                customer = "DecryptFile";
            }
        }
        return customer;
    }

    protected static String buildNewFileName(File file, String typeName, boolean customer) {
        String path = file.getPath();
        int dorIndex = path.lastIndexOf(".");
        int nameIndex = path.lastIndexOf("\\");
        if (nameIndex < 0) {
            nameIndex = path.lastIndexOf("/");
        }
        if (customer) {
            return path.substring(0, nameIndex + 1) + typeName + path.substring(dorIndex, path.length());
        } else {
            return path.substring(0, dorIndex) + "_" + typeName + path.substring(dorIndex, path.length());
        }
    }

    protected static InputStream getDecryptStream(File encryptFile, String pwd) {

        if (encryptFile == null || pwd.equals("")) {
            return null;
        }
        String path = "";
        if (!encryptFile.exists() || encryptFile.isDirectory()) {
            return null;
        }
        path = encryptFile.getPath();
        if (path.equals("")) {
            return null;
        }

        try {
            byte[] cache = new byte[128];
            int nRead = 0;
            FileInputStream inputStream = (FileInputStream) StreamUtil.getReadStream(encryptFile);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CipherOutputStream cout = new CipherOutputStream(byteArrayOutputStream,
                    getCipher(Cipher.DECRYPT_MODE, pwd));
            while ((nRead = inputStream.read(cache)) != -1) {
                cout.write(cache, 0, nRead);
                cout.flush();
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            cout.close();
            inputStream.close();
            byteArrayOutputStream.close();
            return byteArrayInputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}