package hzj.vo.base;

public class HeadVO {
	private String timestamp;
	private String accountToken;
	private String version;

	private String identityStr;//为配合mis后台
	private String signId;//为配合mis后台

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIdentityStr() {
		return identityStr;
	}

	public void setIdentityStr(String identityStr) {
		this.identityStr = identityStr;
	}

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}
}
