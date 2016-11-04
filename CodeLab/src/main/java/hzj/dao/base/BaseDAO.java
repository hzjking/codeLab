package hzj.dao.base;

import java.util.List;

public interface BaseDAO {
	/**
	 * 通过sql往数据库插入记录
	 * 
	 * @param statementName
	 * @param record
	 */
	public void insert(String statementName, Object record);

	/**
	 * 更新数据库一条记录
	 * 
	 * @param statementName
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(String statementName, Object record);

	
	/**
	 * 更新数据库记录
	 * 
	 * @param statementName
	 * @param record
	 * @return
	 */
	public int update(String statementName, Object record);

	/**
	 * 通过主键查找对象.通常直接使用mapper中的方法
	 * 
	 * @param statementName
	 * @param key
	 * @return
	 */
	public Object selectByPrimaryKey(String statementName, Object key);

	/**
	 * 查询list
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public List<?> selectList(final String statementName,
                              final Object parameterObject);



	public List<?> selectAll(final String statementName);

	/**
	 * 查询对象.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public Object selectObject(final String statementName,
                               final Object parameterObject);
	
	public int batchInsert(final String statementName, final List<?> parameters);
	
	public int batchUpdate(final String statementName, final List<?> parameters);

	public String getNextValueOfSequence(String sequenceName);

}
