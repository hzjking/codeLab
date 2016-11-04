package hzj.page;

import java.util.List;

/**
 * @FileName: PageDataDTO.java
 * @Description: 分页查询返回类型
 */
public class PageDataDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private List<?> data;
	private Integer totalRecord = 0;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
}
