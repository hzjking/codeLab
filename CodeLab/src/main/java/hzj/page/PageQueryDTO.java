package hzj.page;


import hzj.vo.page.PaginationVO;

import java.util.HashMap;
import java.util.Map;

public class PageQueryDTO extends Page<PageQueryDTO> {

	private Map<String, Object> params = new HashMap<String, Object>();
	

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public void put(String key, Object value) {
		params.put(key, value);
	}

	public void putPagination(PaginationVO pagination) {
		setPageNo(pagination.getPage());
		setPageSize(pagination.getSize());
	}
}
