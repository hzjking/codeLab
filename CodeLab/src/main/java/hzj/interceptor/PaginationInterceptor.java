package hzj.interceptor;

import hzj.page.Page;
import hzj.utils.ReflectionUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Intercepts( {
	@Signature(method = "prepare",
			type = StatementHandler.class,
			args = {Connection.class}) }) 
public class PaginationInterceptor implements Interceptor {

	private Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

	private String databaseType;// 数据库类型，不同的数据库有不同的分页方法

	/**
	 * 拦截后要执行的方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation
				.getTarget();
		// 通过反射获取到当前RoutingStatementHandler对象的delegate属性
		StatementHandler delegate = (StatementHandler) ReflectionUtil
				.getFieldValue(handler, "delegate");
		// 获取到当前StatementHandler的boundSql
		BoundSql boundSql = delegate.getBoundSql();
		// 拿到当前绑定Sql的参数对象，
		Object obj = boundSql.getParameterObject();
		//logger.info(boundSql.getSql());
		// 这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
		if (obj instanceof Page<?>) {
			Page<?> page = (Page<?>) obj;
			// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
			MappedStatement mappedStatement = (MappedStatement) ReflectionUtil
					.getFieldValue(delegate, "mappedStatement");
			// 拦截到的prepare方法参数是一个Connection对象
			Connection connection = (Connection) invocation.getArgs()[0];
			// 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
			String sql = boundSql.getSql();
			// 给当前的page参数对象设置总记录数
			this.setTotalRecord(page, mappedStatement, connection);
			// 获取分页Sql语句
			String pageSql = this.getPageSql(page, sql);
			// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
			ReflectionUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		return invocation.proceed();
	}

	/**
	 * 拦截器对应的封装原始对象的方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置注册拦截器时设定的属性
	 */
	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType");
	}

	/**
	 * 根据当前数据库类型获取分页sql, 目前只支持mysql和oracle
	 *
	 * @param page 分页对象
	 * @param sql 原sql语句
	 * @return
	 */
	private String getPageSql(Page<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		if ("mysql".equalsIgnoreCase(databaseType)) {
			return getMysqlPageSql(page, sqlBuffer);
		} else if ("oracle".equalsIgnoreCase(databaseType)) {
			return getOraclePageSql(page, sqlBuffer);
		}
		return sqlBuffer.toString();
	}

	/**
	 * 获取Mysql数据库的分页查询语句
	 *
	 * @param page 分页对象
	 * @param sqlBuffer 包含原sql语句的StringBuffer对象
	 * @return Mysql数据库分页语句
	 */
	private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",")
				.append(page.getPageSize());
		return sqlBuffer.toString();
	}

	/**
	 * 获取Oracle数据库的分页查询语句
	 *
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Oracle数据库的分页查询语句
	 */
	private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer) {
		// 如果需要排序则加入排序条件
		if (!"".equals(page.getSortField())&&null!=page.getSortField()
				&&!"".equals(page.getSortMethod())&&null!=page.getSortMethod()) {
			sqlBuffer.insert(0, "SELECT OD.* FROM (")
					.append(") OD ORDER BY OD.")
					.append(page.getSortField())
					.append(" ")
					.append(page.getSortMethod());
		}
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (")
				.append(") u )");
		sqlBuffer.insert(0, "select * from (").append(" where r >= ")
				.append(offset).append("and r <").append(offset + page.getPageSize());;
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u
		// where rownum < 31) where r >= 16
		return sqlBuffer.toString();
	}

	/**
	 * 给当前的参数对象page设置总记录数
	 *
	 * @param page Mapper映射语句对应的参数对象
	 * @param mappedStatement Mapper映射语句
	 * @param connection 当前的数据库连接
	 */
	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement,
								Connection connection) {
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		// 获取到我们自己写在Mapper映射语句中对应的Sql语句
		String sql = boundSql.getSql();
		// 通过查询Sql语句获取到对应的计算总记录数的sql语句
		String countSql = this.getCountSql(sql);
		// 通过BoundSql获取对应的参数映射
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
		BoundSql countBoundSql = new BoundSql(
				mappedStatement.getConfiguration(), countSql,
				parameterMappings, page);
		// 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
		ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, page, countBoundSql);
		// 通过connection建立一个countSql对应的PreparedStatement对象。
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Integer totalRecord = rs.getInt(1);
				logger.debug("本次查询结果集条数：" +totalRecord);
				// 给当前的参数page对象设置总记录数
				page.setTotalRecord(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 *
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql) {
		//int index = sql.indexOf("from");
		return "select count(*) from ("+sql+") c";
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
}
