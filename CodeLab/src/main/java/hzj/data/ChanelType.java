package hzj.data;


public class ChanelType {
	/** IOS客户端 */
	private static String IPHONE_TYPE = "1";
	/** 安卓客户端 */
	private static String ANDROID_TYPE = "2";
	/** 其他客户端 */
	private static String OTHER_TYPE = "3";

	public static String get(String system) {

		if (system.matches("^.*(?i)iphone.*$")) {
			return IPHONE_TYPE;
		} else if (system.matches("^.*(?i)android.*$")) {
			return ANDROID_TYPE;
		}else{
			return OTHER_TYPE;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(get("123androidf"));
	}
}
