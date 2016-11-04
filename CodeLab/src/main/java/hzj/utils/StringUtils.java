package hzj.utils;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils{

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 将金额从元转为分
	 * @param amtY String
	 * @return 
	 */
	public static String formatAmtY2F(String amtY){
		int index = amtY.indexOf(".");
		int len = amtY.length();
		StringBuffer amtF = new StringBuffer();
		if(index == -1){
			amtF.append(amtY).append("00");
		}else if((len - index) == 1){
			amtF.append(Long.parseLong(amtY.replace(".", ""))).append("00");
		}else if ((len - index) ==2){
			amtF.append(Long.parseLong(amtY.replace(".", ""))).append("0");
		}else {
			amtF.append(Long.parseLong(amtY.replace(".", "")));
		}
		return amtF.toString();
	}
	
	/**
	 * 将金额从分转为元,
	 * @param amtF String
	 * @return xx.xx
	 */
	public static String formatAmtF2Y(String amtF){
		if (amtF == null || "".equals(amtF = amtF.trim()) || "null".equals(amtF)) {
			return "0.00";
		}
		// true:负数;false:正数
		boolean flag = false;
		if ('-' == amtF.charAt(0)) {
			flag = true;
			amtF = amtF.substring(1);
		}
		StringBuffer amtY = new StringBuffer();
		if (amtF.length() == 1) {
			amtY.append("0.0").append(amtF);
		} else if (amtF.length() == 2) {
			amtY.append("0.").append(amtF);
		} else {
			amtY.append(amtF.substring(0, amtF.length() - 2))
				.append(".")
					.append(amtF.substring(amtF.length() - 2));
		}
		String result = amtY.toString();
		return flag ? "-" + result : result;
	}
	
    /**
    * 汉字转拼音的方法
    * @param name 汉字
    * @return 拼音
    */
   /* public static String HanyuToPinyin(String name){
    	String pinyinName = "";
        char[] nameChar = name.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        }
        return pinyinName;
    }*/
    
    /**
     * 当前时间转换yyyyMMddHHmmss格式
     * @return
     */
    public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 str = df.format(date);
		 return str;
	}

	/**
	 * 将字节数组转为16进制
	 * @param src 数组
	 * @return 16进制字符串
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	/**
	 * 16进制转字节数组
	 * @param hexString 16进制字符串
	 * @return 数组
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	
	/**
	 * get the left string by the findStr from oraStr
	 * 
	 * @param oraStr
	 *            字符串
	 * @param findStr
	 *            要查找的字符串
	 * @return 要查找的字符串左边的字符串
	 */
	public static String getLeftOfFindString(String oraStr, String findStr) {
		if (StringUtils.isBlank(oraStr) || StringUtils.isBlank(findStr))
			return null;
		int position = oraStr.indexOf(findStr);
		if (position <= 0)
			return oraStr;
		return oraStr.substring(0, position);
	}

	/**
	 * get the right string by the findStr from oraStr
	 * 
	 * @param oraStr
	 *            字符串
	 * @param findStr
	 *            要查找的字符串
	 * @return 要查找的字符串右边的字符串
	 */
	public static String getRightOfFindString(String oraStr, String findStr) {
		if (StringUtils.isBlank(oraStr) || StringUtils.isBlank(findStr))
			return null;

		int position = oraStr.lastIndexOf(findStr);
		if (position <= 0)
			return oraStr;

		return oraStr.substring(position + 1);
	}

	/**
	 * 将camel大小写形式的字符串转换为大写加下划线的字符串 sample: helloWorld --> HELLO_WORLD
	 * 
	 * @param camelStyleString
	 *            camelStyle格式的字符串
	 * @return 大写带下划线的字符串
	 */
	public static String convertCamelStyleToUpperCase(String camelStyleString) {
		if (StringUtils.isBlank(camelStyleString)) {
			return null;
		}
		if (camelStyleString.length() == 1) {
			return camelStyleString.toUpperCase();
		}
		String upperCaseString = "";
		upperCaseString += camelStyleString.charAt(0);
		for (int i = 1; i < camelStyleString.length(); i++) {
			if ((camelStyleString.charAt(i) >= 'A')
					&& (camelStyleString.charAt(i) <= 'Z')) {
				// 如果为大写字母,说明为单词开头,则加下划线分割
				upperCaseString += "_";
			}
			upperCaseString += camelStyleString.charAt(i);
		}
		upperCaseString = upperCaseString.toUpperCase();
		return upperCaseString;
	}

	/**
	 * 将大写加下划线的字符串转换为camel大小写形式的字符串 sample: HELLO_WORLD --> helloWorld
	 * 
	 * @param upperCaseString
	 *            大写带下划线的字符串
	 * @return camelStyle格式的字符串
	 */
	public static String convertUpperCaseToCamelStyle(String upperCaseString) {
		return parseMethodType(upperCaseString);
	}

	public static String transformCode(String str) {
		try {
			transformCodeTest(str);
			return new String(str.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	private static void transformCodeTest(String str)
			throws UnsupportedEncodingException {
		String[] code = { "GBK", "UTF-8", "ISO8859-1", "gb2312" };
		for (int i = 0; i < code.length; i++) {
			System.out.println(code[i] + " : "
					+ new String(str.getBytes(code[i])));
			for (int j = 0; j < code.length; j++) {
				if (i != j)
					System.out.println(code[j] + " -> " + code[i] + " : "
							+ new String(str.getBytes(code[j]), code[i]));
			}
		}
	}

	public static String parseLowercase(String str, String space) {
		ArrayList<String> strs = StringUtils.splitString(str);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.size(); i++) {
			str = (String) strs.get(i);
			sb.append(str.toLowerCase());
			if (i < strs.size() - 1)
				sb.append(space);
		}
		return sb.toString();
	}

	public static String parseUppercase(String str, String space) {
		ArrayList<String> strs = StringUtils.splitString(str);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.size(); i++) {
			str = (String) strs.get(i);
			sb.append(str.toUpperCase());
			if (i < strs.size() - 1)
				sb.append(space);
		}
		return sb.toString();
	}

	public static String parseMethodType(String str) {
		ArrayList<String> strs = StringUtils.splitString(str);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.size(); i++) {
			str = (String) strs.get(i);
			if (i > 0)
				sb.append(str.substring(0, 1).toUpperCase());
			else
				sb.append(str.substring(0, 1).toLowerCase());
			sb.append(str.substring(1, str.length()).toLowerCase());
		}
		return sb.toString();
	}

	public static String parseClassType(String str) {
		ArrayList<String> strs = StringUtils.splitString(str);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.size(); i++) {
			str = (String) strs.get(i);
			sb.append(str.substring(0, 1).toUpperCase());
			sb.append(str.substring(1, str.length()).toLowerCase());
		}
		return sb.toString();
	}

	public static String wipeUnderline(String str) {
		while (str.indexOf("_", 0) == 0) {
			;
		}
		return str;
	}

	/**
	 * 将字符串拆分为单词列表
	 * 
	 * @param str
	 *            字符串
	 * @return 单词列表
	 */
	private static ArrayList<String> splitString(String str) {
		ArrayList<String> strArray = new ArrayList<String>();
		strArray.add(str);
		strArray = StringUtils.splitStrWithStr(strArray, "_");
		strArray = StringUtils.splitStrWithStr(strArray, " ");
		return strArray;
	}

	/**
	 * 将字符串组中每个字符串以split拆分，然后再组成字符串组
	 * 
	 * @param in
	 *            包含字符串的列表
	 * @param split
	 *            拆分标志
	 * @return 包含拆分后数组的列表
	 */
	private static ArrayList<String> splitStrWithStr(ArrayList<String> in, String split) {
		ArrayList<String> out = new ArrayList<String>();
		for (int i = 0; i < in.size(); i++) {
			String tempStr = (String) in.get(i);
			String[] strs = tempStr.split(split);
			for (int j = 0; j < strs.length; j++) {
				out.add(strs[j]);
			}
		}
		return out;
	}

	/**
	 * 用字府c给字符串str补足位数 123，c=0，digitNum=2，则返回00123
	 * 
	 * @param str
	 *            待处理的字符串
	 * @param c
	 *            要补位的字符
	 * @param digitNum
	 *            要补的位数
	 * @return 不足位数之后的字符串
	 */
	public static String getFullChar(String str, char c, int digitNum) {
		if (str != null) {
			for (int i = str.length(); i < digitNum; i++) {
				str = c + str;
			}
		}
		return str;
	}

	/**
	 * 得到字符串中第一个不是c字符的后面的字符串
	 * 
	 * @param str
	 *            待处理的字符串
	 * @param c
	 *            字符
	 * @return 字符串中第一个不是c字符的后面的字符串
	 */
	public static long getNumberFromString(String str, char c) {
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				if (c != str.charAt(i)) {
					return Long.parseLong(str.substring(i, str.length()));
				}
			}
		}
		return 0;
	}

	/**
	 * 判断输入金额中"."的情况
	 * 
	 * @param str
	 *            数字
	 * @return true or false
	 */
	public static boolean checkDotNumber(String str) {
		if (!StringUtils.isEmpty(str)) {
			// 首位或末位为"."
			if (".".equals(str.substring(0, 1))) {
				return false;
			} else if ("."
					.equals(str.substring(str.length() - 1, str.length()))) {
				return false;
			}

			// 输入的"."的数量大于1
			int j = 0;
			for (int i = 0; i < str.length(); i++) {
				if (".".equals(str.substring(i, i + 1))) {
					j = j + 1;
				}
			}
			if (j > 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将第一位是"."的数字前自动补0 比如.1==>0.1，.120==>0.120
	 * 
	 * @param str
	 *            数字
	 * @return 补0后的数字
	 */
	public static String formatZeroNumber(String str) {
		if (str != null) {
			if (".".equals(str.substring(0, 1))) {
				str = "0" + str;
			}
		}
		return str;
	}

	/**
	 * 将大数字转换为科学计数法表现的形式 如12345表示为1.2345000E4 1000000表示为1.0000000E6
	 * 
	 * @param bd
	 *            大数字
	 * @return 科学计数法表现的大数字
	 */
	public static String convertBigDecimalToStringWithE(BigDecimal bd) {
		int exponent = 0;
		BigDecimal newBigDecimal = null;
		if (bd == null)
			return "";
		if (bd.compareTo(new BigDecimal("0")) == 0) {
			return "0";
		}
		if (bd.abs().compareTo(new BigDecimal(10)) < 0
				&& bd.abs().compareTo(new BigDecimal(1)) >= 0) {
			newBigDecimal = bd;
			exponent = 0;
		} else if (bd.abs().compareTo(new BigDecimal(10)) >= 0) {
			do {
				newBigDecimal = bd.divide(new BigDecimal(10), 7, 6);
				exponent++;
				bd = newBigDecimal;
			} while (newBigDecimal.abs().compareTo(new BigDecimal(10)) >= 0);

		} else if (bd.abs().compareTo(new BigDecimal(1)) < 0) {
			do {
				newBigDecimal = bd.multiply(new BigDecimal(10));
				exponent--;
				bd = newBigDecimal;
			} while (newBigDecimal.abs().compareTo(new BigDecimal(1)) < 0);

		}

		DecimalFormat df = new DecimalFormat("#.0000000");

		return df.format(newBigDecimal) + "E" + exponent;
	}

	/**
	 * 页面js表达的文件路径
	 * 
	 * @param standardPath
	 *            标准路径
	 * @return 页面js表达的文件路径
	 */
	public static String getJSFilePath(String standardPath) {
		if (standardPath == null) {
			return null;
		} else {
			String[] t = StringUtils.split(standardPath, "\\");
			String newPath = "";
			for (int i = 0; i < t.length - 1; i++) {
				newPath = newPath + t[i] + "\\\\";
			}
			newPath = newPath + t[t.length - 1];
			return newPath;
		}
	}

	/**
	 * 取得文件扩展名
	 * 
	 * @param fileName
	 *            原始文件名
	 * @return 文件扩展名
	 */
	public static String getFileExtendName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return "";
		} else {
			int indexOfDot = fileName.lastIndexOf(".");
			return fileName.substring(indexOfDot + 1);
		}
	}

	/**
	 * 取得文件名(无扩展名)
	 * 
	 * @param fileName
	 *            原始文件名
	 * @return 文件名（无扩展名）
	 */
	public static String getFileNameWithoutExtend(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return "";
		} else {
			int indexOfDot = fileName.lastIndexOf(".");
			return fileName.substring(0, indexOfDot);
		}
	}

	/**
	 * a,b,c ===> 'a','b','c' a ===> 'a' null ===> null
	 * 
	 * @param str
	 *            字符串
	 * @return 待单引号的字符串
	 */
	public static String getStringWithSingleQuote(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String[] arr = StringUtils.split(str, ",");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = "'" + arr[i] + "'";
		}
		return StringUtils.join(arr, ",");
	}

	/**
	 * ['a','b','c'] ===> 'a','b','c' ['a'] ===> 'a' [] ===> '' null ===> null
	 * 
	 * @param str
	 *            字符串数组
	 * @return 待单引号的字符串所组成的数组
	 */
	public static String getStringWithSingleQuote(String[] str) {
		if (str == null)
			return null;
		if (str.length == 0)
			return "''";
		for (int i = 0; i < str.length; i++) {
			str[i] = "'" + str[i] + "'";
		}
		return StringUtils.join(str, ",");
	}

	/**
	 * 计算百分比
	 * 
	 * @param totalCount
	 *            总数
	 * @param count
	 *            个体数
	 * @return 百分比
	 */
	public static String getPercentString(String totalCount, String count) {
		BigDecimal c = new BigDecimal(count);
		BigDecimal total = new BigDecimal(totalCount);
		double percent = c.divide(total, 5, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		BigDecimal percentValue = new BigDecimal(Double.toString(percent));
		BigDecimal value = new BigDecimal("100");
		String percentString = String.valueOf(percentValue.multiply(value)
				.toString())
				+ "%";
		return percentString;
	}

	/**
	 * 15位身份证号码转换成18位算法
	 * 
	 * @param s 15位身份证号码
	 * @return 18位身份证号码
	 */
	public static String generate18BitFrom15Bit(String s) {
		final int vList[] = { 7, 9, 10, 5, 8, 4, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		int intN = 0;
		int r = 0;
		String t = "";
		// int[] n = new int[15];
		String s1 = s.substring(0, 6);
		String s2 = s.substring(6, 15);
		for (int i = 14; i >= 0; i--) {
			intN += Integer.parseInt(s.substring(i, i + 1)) * vList[i];
		}// 15位号码的 加权*号码
		intN += 2 * 1 + 1 * 9;// 添加1,9位的加权*号码
		r = intN % 11;// 获取mod值
		switch (r) {
		case 0:
			t = "1";
			break;
		case 1:
			t = "0";
			break;
		case 2:
			t = "X";
			break;
		case 3:
			t = "9";
			break;
		case 4:
			t = "8";
			break;
		case 5:
			t = "7";
			break;
		case 6:
			t = "6";
			break;
		case 7:
			t = "5";
			break;
		case 8:
			t = "4";
			break;
		case 9:
			t = "3";
			break;
		case 10:
			t = "2";
			break;
		default:
		}
		return s1 + "19" + s2 + t;
	}

	/**
	 * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名. xiadi
	 * 
	 * @param s
	 *            原文件名
	 * @return 重新编码后的文件名
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 转换为字节数组
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str){
		if (str != null){
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * @return
	 */
	public static String toString(byte[] bytes){
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs){
		if (str != null){
			for (String s : strs){
				if (str.equals(trim(s))){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html){
		if (html == null){
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param txt
	 * @return
	 */
	/*public static String toHtml(String txt){
		if (txt == null){
			return "";
		}
		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}*/

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)",
				"$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
				"$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}

	/**
	 * 获得i18n字符串
	 */
	/*public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}*/

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		}else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)){
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * @param objectString 对象串
	 *   例如：row.user.id
	 *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString){
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i=0; i<vals.length; i++){
			val.append("." + vals[i]);
			result.append("!"+(val.substring(1))+"?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	public static String getDaoMainSimpleName(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}
}