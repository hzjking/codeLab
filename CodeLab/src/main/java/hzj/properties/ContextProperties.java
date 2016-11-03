package hzj.properties;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class ContextProperties {
	private static Logger logger = Logger.getLogger(ContextProperties.class);

	private static String fileName = null;

	private static Map<String, String> CONTEXT_CONFIG = null;

	public static String getValue(String key) {
		if (CONTEXT_CONFIG == null) {
			if (fileName == null) {

			} else {
				reloadByEnvConf();
			}
		}
		return CONTEXT_CONFIG.get(key);
	}

	public final static String ENV = "env";
	public final static String IMG_PRE_URL = "img_pre_url";

	public static void reloadByLocalConf() {
		Map<String, String> result = new HashMap<String, String>();
		ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("context");
		result.put(ENV, RESOURCE_BUNDLE.getString(ENV));
		result.put(IMG_PRE_URL, RESOURCE_BUNDLE.getString(IMG_PRE_URL));

		CONTEXT_CONFIG = result;
		result = null;
	}

	public static void reloadByEnvConf() {
		if (fileName != null) {
			InputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(fileName));
				Properties prop = new Properties();
				prop.load(in);
				Map<String, String> result = new HashMap<String, String>();
				result.put(ENV, (String) prop.get(ENV));
				result.put(IMG_PRE_URL, (String) prop.get(IMG_PRE_URL));

				CONTEXT_CONFIG = result;
				result = null;

			} catch (FileNotFoundException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
	public static void setFileName(String fileName) {
		ContextProperties.fileName = fileName;
	} 
}
