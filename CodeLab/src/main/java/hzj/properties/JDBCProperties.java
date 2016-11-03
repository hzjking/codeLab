package hzj.properties;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class JDBCProperties {

	private static Logger logger=Logger.getLogger(JDBCProperties.class);
			
	private static String fileName=null;
	
	private static Map<String,String> JDBC_CONFIG=null;
	
	public static String getValue(String key){
		if(JDBC_CONFIG==null){
			if(fileName==null){
				reloadByLocalConf();
			}else{
				reloadByEnvConf();
			}
		}
		logger.info("#######"+key+":"+JDBC_CONFIG.get(key)+"#######");
		return JDBC_CONFIG.get(key);
	}
	
	public final static String DriverClass="driverClass";
	public final static String JdbcUrl="jdbcUrl";
	public final static String User="user";
	public final static String Password="password";
	public final static String AutoCommitOnClose="autoCommitOnClose";
	public final static String AcquireIncrement="acquireIncrement";
	public final static String InitialPoolSize="initialPoolSize";
	public final static String MinPoolSize="minPoolSize";
	public final static String MaxPoolSize="maxPoolSize";
	public final static String MaxStatements="maxStatements";
	public final static String MaxIdleTime="maxIdleTime";
	public final static String IdleConnectionTestPeriod="idleConnectionTestPeriod";
	public final static String NumHelperThreads="numHelperThreads";
	
	public static void reloadByLocalConf(){
		Map<String,String> result = new HashMap<String,String>();
		ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config.jdbc");
		result.put(DriverClass, RESOURCE_BUNDLE.getString(DriverClass));
		result.put(JdbcUrl, RESOURCE_BUNDLE.getString(JdbcUrl));
		result.put(User, RESOURCE_BUNDLE.getString(User));
		result.put(Password, RESOURCE_BUNDLE.getString(Password));
		result.put(AutoCommitOnClose, RESOURCE_BUNDLE.getString(AutoCommitOnClose));
		result.put(AcquireIncrement, RESOURCE_BUNDLE.getString(AcquireIncrement));
		result.put(InitialPoolSize, RESOURCE_BUNDLE.getString(InitialPoolSize));
		result.put(MinPoolSize, RESOURCE_BUNDLE.getString(MinPoolSize));
		result.put(MaxPoolSize, RESOURCE_BUNDLE.getString(MaxPoolSize));
		result.put(MaxStatements, RESOURCE_BUNDLE.getString(MaxStatements));
		result.put(MaxIdleTime, RESOURCE_BUNDLE.getString(MaxIdleTime));
		result.put(IdleConnectionTestPeriod, RESOURCE_BUNDLE.getString(IdleConnectionTestPeriod));
		result.put(NumHelperThreads, RESOURCE_BUNDLE.getString(NumHelperThreads));
		
		JDBC_CONFIG=result;
		result=null;
	}
	
	public static void reloadByEnvConf(){
		if(fileName!=null){
			InputStream in = null;
			try {
				in = new BufferedInputStream(
						new FileInputStream(fileName));
				Properties prop=new Properties();
				prop.load(in);
				Map<String,String> result=new HashMap<String,String>();
				result.put(DriverClass,(String)prop.get(DriverClass));
				result.put(JdbcUrl,(String)prop.get(JdbcUrl));
				result.put(User,(String)prop.get(User));
				result.put(Password,(String)prop.get(Password));
				result.put(AutoCommitOnClose,(String)prop.get(AutoCommitOnClose));
				result.put(AcquireIncrement,(String)prop.get(AcquireIncrement));
				result.put(InitialPoolSize,(String)prop.get(InitialPoolSize));
				result.put(MinPoolSize,(String)prop.get(MinPoolSize));
				result.put(MaxPoolSize,(String)prop.get(MaxPoolSize));
				result.put(MaxStatements,(String)prop.get(MaxStatements));
				result.put(MaxIdleTime,(String)prop.get(MaxIdleTime));
				result.put(IdleConnectionTestPeriod,(String)prop.get(IdleConnectionTestPeriod));
				result.put(NumHelperThreads,(String)prop.get(NumHelperThreads));
				
				JDBC_CONFIG=result;
				result=null;
				
			} catch (FileNotFoundException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			} finally{
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	public static void setFileName(String fileName) {
		JDBCProperties.fileName = fileName;
	} 
}
