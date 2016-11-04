package hzj.dao.base;

import hzj.page.Page;
import hzj.page.PageDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;


/** 
 * @FileName: PageQueryDAOImpl.java
 */

@Repository("pageQueryDAO")
@Scope("prototype")
public class PageQueryDAOImpl implements PageQueryDAO {

	@Autowired
	private BaseDAO baseDAO;

	public PageDataDTO query(String statement, Page<?> parameter) {
		PageDataDTO pageDataDTO =new PageDataDTO();
		List<?> result=baseDAO.selectList(statement, parameter);
		pageDataDTO.setData(result);
		pageDataDTO.setTotalRecord(result.size());
		return pageDataDTO;
	}
}
