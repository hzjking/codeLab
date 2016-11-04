package hzj.dao.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("baseDAO")
@Scope("prototype")
public class BaseDAOImpl extends SqlSessionDaoSupport implements BaseDAO {

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public void insert(String statementName, Object record) {
		getSqlSession().insert(statementName, record);
	}

	public int update(String statementName, Object record) {
		return getSqlSession().update(statementName, record);
	}

	public Object selectByPrimaryKey(String statementName, Object key) {
		Object record = getSqlSession().selectOne(statementName, key);
		return record;
	}

	public int updateByPrimaryKey(String statementName, Object record) {
		int rows = getSqlSession().update(statementName, record);
		return rows;
	}

	public List selectList(final String statementName,
												 final Object parameterObject) {
		List result = getSqlSession()
				.selectList(statementName, parameterObject);
		return result;
	}

	public List selectAll(final String statementName) {
		List result = getSqlSession()
				.selectList(statementName);
		return result;
	}

	public Object selectObject(final String statementName,
			final Object parameterObject) {
		Object result = getSqlSession().selectOne(statementName,
				parameterObject);
		return result;
	}

	public int batchInsert(final String statementName, final List<?> parameters) {
		int i = 0;
		for (Object record : parameters) {
			insert(statementName, record);
			i++;
		}
		return i;
	}

	public int batchUpdate(final String statementName, final List<?> parameters) {
		int i = 0;
		for (Object record : parameters) {
			update(statementName, record);
			i++;
		}
		return i;
	}

	public String getNextValueOfSequence(String sequenceName) {
		return getSqlSession().selectOne(
				"com.sand.CommonsMapper.getNextValueOfSequence",
				sequenceName);
	}
}
