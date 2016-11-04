package hzj.vo.base;

public class ResponseVO<T extends BaseVO> {
	private String resultCode; // 响应吗
	private String resultMsg; // 响应提示
	private T body; // 返回业务数据

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
