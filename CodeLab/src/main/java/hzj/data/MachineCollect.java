package hzj.data;

import com.sand.cache.MachineCache;
import com.sand.cache.MachineData;
import com.sand.dao.base.BaseDAO;
import com.sand.dbhandler.DataSourceConsts;
import com.sand.dbhandler.DataSourceContextHolder;
import com.sand.utils.DateUtil;
import com.sand.utils.SpringContextsUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 设备信息采集
 *
 */
public class MachineCollect {
	
	private Logger logger = Logger.getLogger(MachineCollect.class);

	private HttpServletRequest request;
	public MachineCollect(HttpServletRequest request){
		this.request = request;
	}
	
	public void run() {

		String ipAddr = getIpAddr(request);
		String agent = request.getHeader("User-Agent");
		logger.info("IP = ["+ipAddr+"], agent = ["+agent+"]");
		if (agent == null || agent.length() == 0) {
//			logger.error("IP = ["+ipAddr+"], agent is null");
			return;
		}
		String[] info = agent.split("#");
		if (info == null || info.length != 3) {
//			logger.error("IP = ["+ipAddr+"], agent = ["+agent+"]");
			return;
		}
		
//		String machineName 		= 	"0";
//		String systemName 		= 	"1";
//		String systemVersion 	= 	"2";
		
		String machineName 		= 	info[0];
		String systemName 		= 	info[1];
		String systemVersion 	= 	info[2];
		
		MachineData data = new MachineData();
		DataSourceContextHolder
				.setCustomerType(DataSourceConsts.MIS_DATA_SUOURCE);
		BaseDAO baseDAO = SpringContextsUtil.getBean("baseDAO", BaseDAO.class);
		data.setSeqId(new Integer(baseDAO.getNextValueOfSequence("seq_machine_info_id")));
		data.setIp(ipAddr);
		data.setMachineName(machineName);
		data.setSystemName(systemName);
		data.setSystemVersion(systemVersion);
		data.setCallDate(DateUtil.getCurrentDateStr());
		data.setApiCode(request.getServletPath());
		// 加入的当前线程中
		MachineCache.put(data);
		
	}

	/**
	 * 获取真实的客户端IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
	private static int i = 0;
	
	public synchronized static BigDecimal getNum(){
		i++;
		return new BigDecimal(i); 
	}
	
}
