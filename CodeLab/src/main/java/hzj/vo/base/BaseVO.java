package hzj.vo.base;

import java.io.Serializable;

/**
 * 此类除了设定响应码和响应描述没有实际意义, 只是为了类型安全
 * @author Administrator
 *
 */
public class BaseVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String apiCode;
	private String account;

	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
