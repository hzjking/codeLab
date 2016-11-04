package hzj.dao.base;

import hzj.page.Page;
import hzj.page.PageDataDTO;

/**
 * @FileName: PageQueryDAO.java
 * @Description:
 */
public interface PageQueryDAO {

	PageDataDTO query(String statement, Page<?> parameter);
}
