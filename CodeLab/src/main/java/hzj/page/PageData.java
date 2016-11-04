package hzj.page;

import java.util.List;

/**
 * @FileName: PageData.java
 * @DDescription: 分页查询返回类型
 */
public class PageData implements java.io.Serializable {

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
