package hzj.vo.base;

public final class RequestVO<T extends BaseVO> {
	private HeadVO head; // 请求头对象
	private T body; // 请求报文体

	public HeadVO getHead() {
		return head;
	}

	public void setHead(HeadVO head) {
		this.head = head;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
