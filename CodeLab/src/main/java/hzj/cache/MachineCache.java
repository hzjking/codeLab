package hzj.cache;

import hzj.data.ChanelType;
import hzj.utils.DateUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MachineCache {

	private static Logger logger = Logger.getLogger(MachineCache.class);
	
	// 当前线程所绑定的设备信息主键
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();
	// 交易渠道
	private static ThreadLocal<String> channel = new ThreadLocal<String>();
	
	// 设备信息缓存
	private static Map<Integer, MachineData> machineCacheMap = new HashMap<Integer, MachineData>(1024);
	
	
	/**
	 * 加入缓存对象
	 * @param machineData
	 */
	public static void put(MachineData machineData){
		local.set(machineData.getSeqId());
		channel.set(ChanelType.get(machineData.getSystemName()));
		machineCacheMap.put(machineData.getSeqId(), machineData);
	}
	
	/**
	 * 获取当前线程内的设备信息对象
	 * @return
	 */
	public static Integer getLocalMachineDataId(){
		return local.get(); 
	}
	
	/**
	 * 获取渠道类型
	 * @return
	 */
	public static String getChanel(){
		return channel.get();
	}
	
	/**
	 * 修改设备信息中的数据
	 */
	public static void modifyMachineData(){
		MachineData data = machineCacheMap.get(getLocalMachineDataId());
		if(data != null){
			//	data.setApiCode(apiCode);
			data.setEndDate(DateUtil.getCurrentDateStr());
			data.setFlag(true);
		}

	}
	/*public static void modifyMachineData(String apiCode, String otherCode){
		MachineData data = machineCacheMap.get(getLocalMachineDataId());
		if(data != null){
		//	data.setApiCode(apiCode);
			data.setEndDate(DateUtil.getCurrentDateStr());
			data.setFlag(true);
		}
		
	}*/
	
	/**
	 * 获取缓存中所有已执行完毕的数据采集对象
	 * @return
	 */
	public static Map<Integer, MachineData> getMachineCache(){
	//	logger.debug("now machine cache size : " + machineCacheMap.size());
		// 如果缓存中没对象, 或少于50个大小, 则跳过
		if(machineCacheMap.size() == 0 || machineCacheMap.size() < 50){
			return null;
		}
		// 反之开始刷内存
		Map<Integer, MachineData> resultMap = new HashMap<Integer, MachineData>(machineCacheMap.size());
		Set<Integer> ids = new HashSet<Integer>(machineCacheMap.keySet());
		for (Integer id : ids) {
			MachineData data = machineCacheMap.get(id);
			if(data.isFlag()){ // 判断是否执行完毕
				resultMap.put(id, data); // 加入返回结果map
				machineCacheMap.remove(id); // 从缓存对象中移除
			}
		}
		logger.debug("pending machine size : " + resultMap.size());
		return resultMap;
	}
	

	
}
