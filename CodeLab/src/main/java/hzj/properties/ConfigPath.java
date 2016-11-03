package hzj.properties;

import java.io.File;

public class ConfigPath {
	/**
	 * 如果配置了环境变量，则从环境变量的地址中找配置文件
	 * 如果没有配置环境变量，则读取工程中的配置文件
	 * @param envName 环境变量名称
	 * @return 实际的配置文件地址
	 */
	public static String getConfigPath(String envName){
		//获取启动参数
		String configDir = System.getProperty(envName);
		if (configDir == null) {
			//获取环境变量
			configDir = System.getenv(envName);
		}
		if (configDir == null) {
			//获取CLASS目录
			configDir = ConfigPath.class.getClassLoader()
					.getResource("").getPath()+"config"+File.separator;
		}
		if(configDir.startsWith("file:")){
			//替换file:
			configDir = configDir.replaceFirst("file:", "");
		}
		return configDir;
	}
}
