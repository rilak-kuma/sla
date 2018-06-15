package kr.maxerve.admin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class StringUtil {
	private static final String AESKEY = "abcdefghijklmnopqrstuvwxyz123456"; // 32bite
	private static byte[] aes256Iv = null;

	private static byte[] getAes256Iv() throws Exception {
		if (aes256Iv == null) {
			aes256Iv = new byte[Cipher.getInstance("AES/CBC/PKCS5Padding").getBlockSize()];
		}

		return aes256Iv;
	}

	/**
     * 문자열에서 특정 패턴을 검색한다.
     *
     * @param pattern정규표현식  text검색할문자열 index그룹 없을시 0
     * @return String
     * @exception Exception
     * @see
     */
	public static String getPatternString(String pattern, String text, int index) throws Exception
	{

        String result = "";
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher wordMatche= patternCompile.matcher(text);

        if(wordMatche.find() == true) result = wordMatche.group(index);

        return result;
    }

	public static String getMd5(String str) throws Exception {
		String output = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			output = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			output = null;
		}

		return output;
	}

	/**
	 * sha256 encrypt
	 * @param plain
	 * @return
	 * @throws Exception
	 */
	public static String getSha256Enc(String plain) throws Exception {
		byte[] bEnc;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(plain.getBytes());
        bEnc = messageDigest.digest();

        String encode = Base64.getEncoder().encodeToString(bEnc);

        return encode;
	}

	/**
	 * aes256 encrypt
	 * @param plain
	 * @return
	 * @throws Exception
	 */
	public static String getAes256Enc(String plain) throws Exception {
		byte[] bEnc;

        SecretKey secretKey = new SecretKeySpec(AESKEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getAes256Iv()));

        bEnc = cipher.doFinal(plain.getBytes());

        String encode = Base64.getEncoder().encodeToString(bEnc);

        return encode;
	}

	/**
	 * aes256 decrypt
	 * @param enc
	 * @return
	 * @throws Exception
	 */
	public static String getAes256Dec(String enc) throws Exception {
		byte[] bEnc = Base64.getDecoder().decode(enc);

        SecretKey secretKey = new SecretKeySpec(AESKEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getAes256Iv()));

        byte[] bDec = cipher.doFinal(bEnc);

        return new String(bDec);
	}
}