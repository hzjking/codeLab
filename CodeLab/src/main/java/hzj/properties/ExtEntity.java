package hzj.properties;


public enum ExtEntity {
	// log4j日志配置文件
	LOG_FILE("log4j.properties"),
	// 数据库配置
	JDBC_FILE("jdbc.properties"),
	// 上下文配置
	CONTEXT("context.properties"),
	// 环境变量
	ENV_CONS("CODE_HOME");
	
	
	private String value;
	
	ExtEntity(String value){
		this.value=value;
	}
	public String getValue(){
		return value;
	}
}
