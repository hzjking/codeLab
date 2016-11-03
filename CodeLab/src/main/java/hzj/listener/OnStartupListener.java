package hzj.listener;

import hzj.properties.ConfigPath;
import hzj.properties.ContextProperties;
import hzj.properties.ExtEntity;
import hzj.properties.JDBCProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OnStartupListener implements ServletContextListener {

	// 记录日志
	final Logger logger = Logger.getLogger(OnStartupListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}
	public void contextInitialized(ServletContextEvent arg0) {
		
		// 加载环境变量
		String prefix = ConfigPath.getConfigPath(ExtEntity.ENV_CONS.getValue()) + "/";
		logger.info("misServer prefix path: "+prefix);
		
		// 加载log4j.properties配置文件
		logger.info("start load log4j.properties");
		String log4jPath=prefix+ExtEntity.LOG_FILE.getValue();
		PropertyConfigurator.configure(log4jPath);
		logger.info("log4.properties path: "+log4jPath);
		
		// 加载jdbc.properties配置文件
		logger.info("start load jdbc.properties");
		String jdbcPath=prefix+ExtEntity.JDBC_FILE.getValue();
		JDBCProperties.setFileName(jdbcPath);
		JDBCProperties.reloadByEnvConf();
		logger.info("jdbc.properties path: "+jdbcPath);

		
		// 加载context.properties配置文件
		logger.info("start load context.properties");
		String contextPath=prefix+ExtEntity.CONTEXT.getValue();
		ContextProperties.setFileName(contextPath);
		ContextProperties.reloadByEnvConf();
		logger.info("context.properties path: " + contextPath);

		
	}
}
