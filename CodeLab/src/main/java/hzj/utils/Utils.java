package hzj.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static String formatMoney(String s, int len) {
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		if (len == 0) {
			formater = new DecimalFormat("###,###");

		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		if (result.indexOf(".") == -1) {
			result = result + ".00";
		}else if ((result.length()-result.indexOf("."))==2){
			result = result + "0";
		}else {
			result = result;
		}
		return result;
	}
	
	public static String formatBalance(String balance){
		BigDecimal bd = new BigDecimal(balance);
		String realBalance=bd.divide(new BigDecimal("100")).toString();
		return realBalance=Utils.formatMoney(realBalance, 2);
	}
	
	public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 str = df.format(date);
		 return str;
	}
	
	 public static String formatText(String str){
		 if(null==str||"".equals(str)){
			 return str;
		 }else{
			 if(str.length()>180){
			    	str.substring(0, 180);
			    	StringBuffer sb = new StringBuffer(str.substring(0, 180)).append("......");
					return sb.toString();
			    }else{
			    	return str;
			    } 
		 }
	 }
	 
	 public static String formatCityName(String str){
		 if(null==str||"".equals(str)){
			 return str;
		 }else{
			 if(str.length()>12){
			    	StringBuffer sb = new StringBuffer(str.substring(0, 12)).append("...");
					return sb.toString();
			    }else{
			    	return str;
			    } 
		 }
	 }
	 
	 public static String formatMerchName(String str){
		 if(null==str||"".equals(str)){
			 return str;
		 }else{
			 if(str.length()>10){   	
			    	StringBuffer sb = new StringBuffer(str.substring(0, 10)).append("...");
					return sb.toString();
			    }else{
			    	return str;
			    } 
		 }
	 }
	 
	 public static String dateFormat(String date){
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			String hour = date.substring(8, 10);
			String minute = date.substring(10, 12);
			String second = date.substring(12, 14);
			String result=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
			return result;
		}

	/**经营范围固定值查询*/
	public static List<String> getKey(){
		List<String > keys=new ArrayList<String >();
		keys.add("5691");
		keys.add("5411");
		keys.add("5812");
		keys.add("4814");
		keys.add("5814");
		keys.add("5998");
		keys.add("5131");
		keys.add("5331");

		return keys;
	}
	/**错误码固定值查询*/
	public static List<String> getErrorCode(){
		List<String> ErrorCodeList = new ArrayList<String>();
		ErrorCodeList.add("01");
		ErrorCodeList.add("06");
		ErrorCodeList.add("51");
		ErrorCodeList.add("55");
		ErrorCodeList.add("68");
		ErrorCodeList.add("75");
		return ErrorCodeList;
	}

	/**字符串截取(以百分号分割)*/
	public static String subString(String substr){
		String newstr="";
		String str[]=substr.split("\\%");
		for(int i=0;i<str.length;i++){
			if(str[i].indexOf("$")==-1){//当前字符串中是否包含“$”字符
				newstr+=str[i];
				continue;
			}
			newstr+=str[i].substring(0,str[i].indexOf("$"))+str[i].substring(str[i].indexOf("$")+1);
		}
		return newstr;
	}

	/**固话区号校验规则*/
	public static boolean regNumber(String num){
		String regx = "(0[1-9]{2,4})";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(num);
		return m.matches();
	}

	/**判断字符是字母还是数字且取数字*/

	public static String regStr(String num){
		String newStr="";
		for(int i=0;i<num.length();i++){
			if(!Character.isDigit(num.charAt(i))){
				continue;
			}
			newStr+=num.substring(i,i+1);
		}

		return newStr;
		}

	/**
	 * 营业执照号码的校验功能
	 * 按照GB/T 17710,MOD11,10校验
	 * @param shopLicense
	 * @return
	 */
	public static boolean checkMod1110(String shopLicense) {
		// 验证是否全部都是数字
		for (char tc : shopLicense.toCharArray()) {
			if (tc < '0' || tc > '9') {
				//System.out.println("非全数字的营业执照号码！");
				return false;
			}
		}
		// 验证最后一位是否根据MOD11,10校验规则得到1
		int t1 = 10;
		// 去除最后一位校验位后的号码校验
		String tmpStr = shopLicense.substring(0, shopLicense.length() - 1);
		for (char tc : tmpStr.toCharArray()) {
			// 10 + 当前位置的数字 除 10 求余
			int mod10 = (t1 + Integer.parseInt(Character.toString(tc))) % 10;
			if (mod10 ==0) mod10 = 10;
			t1 = (mod10 * 2) % 11;
		}
		if ((t1 + Integer.parseInt(shopLicense.substring(shopLicense.length() -1))) % 10 != 1) {
			//System.out.println("营业执照号码校验位出错！");
			return false;
		}
		return true;
	}


	/**保理方法*/

	/*验证用户名是否合法*/
	public static boolean userNameReg(String username){
		String regx="/^[a-zA-Z]\\w{3,50}$/";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(username);
		return m.matches();
	}
	/*验证手机号是否合法*/
	public static boolean userPhoneReg(String userphone){
		String regx="/^1[3|4|5|7|8][0-9]\\d{8}$/";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(userphone);
		return m.matches();
	}
	/*日期时间分割组合*/
	public static String timeSplit(String time){
		String strArr[]=time.split("/");
		String str=strArr[2]+strArr[0]+strArr[1];
		return str;
	}
	}


