package pro.mickey.logically.deleted.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

import pro.mickey.logically.deleted.SqlLogicallyDeleted;
import pro.mickey.util.ReflectUtil;

/**
 * @author Mickey 数据�?
 */
public class SqlLogicallyDeletedDynamicSqlSource extends SqlLogicallyDeleted implements SqlSource {

	private SqlSource source;

	/**
	 * @param source
	 *            数据�?
	 * @param isSelect
	 *            是否是查询语�?
	 * @param variable
	 *            表示删除字段名称
	 * @param variable_delete
	 *            表示删除名称
	 * @param variable_delete_not
	 *            表示未删除名�?
	 * @param sql_type
	 *            数据库类�?
	 */
	public SqlLogicallyDeletedDynamicSqlSource(SqlSource source, String variable, String variable_delete, String variable_delete_not, String sql_type) {
		super(variable, variable_delete, variable_delete_not, sql_type);
		this.source = source;

	}

	/*
	 * 修改BoundSql 的where语句
	 * 
	 * @see org.apache.ibatis.mapping.SqlSource#getBoundSql(java.lang.Object)
	 */
	@Override
	public BoundSql getBoundSql(Object parameterObject) {
		BoundSql boundSql = source.getBoundSql(parameterObject);
		ReflectUtil.setFieldValue(boundSql, "sql", logicallyDeleted(boundSql.getSql()));
		return boundSql;
	}
}
