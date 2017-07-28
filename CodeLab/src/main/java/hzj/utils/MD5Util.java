package hzj.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

/**
 * @FileName: MD5Util.java
 * @Description: MD5工具类
 * @author honggang.li
 * @date 2014年7月8日
 */
public class MD5Util {

	/**
	 * 将传入的字符串做MD5摘要
	 * 
	 * @param text
	 *            需要摘要的字符串
	 * @return 摘要结果
	 */
	public static String sign(String text) {
		return DigestUtils.md5Hex(getContentBytes(text, "utf-8"));
	}

	/**
	 * 将传入的明文做MD5摘要，比较传入的摘要，判断是否相等
	 * 
	 * @param text
	 *            需要摘要的字符串
	 * @param sign
	 *            需要比较的摘要对象
	 * @return 验证结果
	 */
	public static boolean verify(String text, String sign) {
		String mysign = DigestUtils.md5Hex(getContentBytes(text, "utf-8"));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:"
					+ charset);
		}
	}

	public static void main(String[] args) {
		System.out
				.println(verify("123456", "e10adc3949ba59abbe56e057f20f883e"));
	}

}