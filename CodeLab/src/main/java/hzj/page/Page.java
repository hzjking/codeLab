package hzj.page;


import com.sand.vo.page.PaginationVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> {
	private Integer pageNo = 1;// 页码，默认是第一页
	private Integer pageSize = 8;// 每页显示的记录数，默认是8
	private Integer totalRecord;// 总记录数
	private Integer totalPage;// 总页数
	private List<T> results;// 对应的当前页记录

	private static int PAGE_MAX = 10;// 页码最大数
	private List<String> pageNums;

	private String sortField;// 排序字段
	private String sortMethod;// 默认是正序

	private Map<String, Object> params = new HashMap<String, Object>();// �����Ĳ������ǰ�����װ��һ��Map����

	public void setPagination(PaginationVO pagination) {
		if (pagination != null) {
			setPageNo(pagination.getPage());
			setPageSize(pagination.getSize());
		}
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		if(pageNo != null){
			this.pageNo = pageNo;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize != null){
			this.pageSize = pageSize;
		}
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		if(totalRecord==null)
			totalRecord=0;
		this.totalRecord = totalRecord;
		Integer totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
		this.getPageRange();
	}

	private void getPageRange() {
		int before = PAGE_MAX / 2;
		int after = PAGE_MAX % 2 == 0 ? PAGE_MAX / 2 - 1 : PAGE_MAX / 2;
		int startPage;// ��ʼҳ��
		int endPage;// ����ҳ��

		if (totalPage<=PAGE_MAX) {// ��ҳ���������ҳ��
			startPage = 1;
			endPage = totalPage;
		}else if(totalPage>PAGE_MAX&&pageNo<=before){ // ��ʼ��ҳ
			startPage = 1;
			endPage = PAGE_MAX;
		}else if (totalPage>PAGE_MAX&&totalPage - pageNo<=after) {// ���ҳ
			startPage = totalPage-PAGE_MAX+1;
			endPage = totalPage;
		}else{// �������
			startPage=pageNo-before;
			endPage = pageNo+after;
		}
		List<String> result = new ArrayList<String>();
		for (int i = startPage; i <= endPage; i++) {
			result.add(i + "");
		}
		this.setPageNums(result);
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")
				.append(pageSize).append(", results=").append(results)
				.append(", totalPage=").append(totalPage)
				.append(", totalRecord=").append(totalRecord).append("]");
		return builder.toString();
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortMethod() {
		return sortMethod;
	}

	public void setSortMethod(String sortMethod) {
		this.sortMethod = sortMethod;
	}

	public List<String> getPageNums() {
		return pageNums;
	}

	public void setPageNums(List<String> pageNums) {
		this.pageNums = pageNums;
	}
}
