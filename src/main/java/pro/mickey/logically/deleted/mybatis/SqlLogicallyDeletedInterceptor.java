package pro.mickey.logically.deleted.mybatis;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import pro.mickey.util.ReflectUtil;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }), })
public class SqlLogicallyDeletedInterceptor implements Interceptor {

	private String variable;
	private String variableDelete;
	private String variableDeleteNot;
	private String sqlType;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String methodName = invocation.getMethod().getName();
		{
			boolean isQuery = false;
			if (methodName.equalsIgnoreCase("query"))
				isQuery = true;
			SqlLogicallyDeletedDynamicSqlSource deletedDynamicSqlSource = new SqlLogicallyDeletedDynamicSqlSource(mappedStatement.getSqlSource(), isQuery, variable, variableDelete, variableDeleteNot, sqlType);
			ReflectUtil.setFieldValue(mappedStatement, "sqlSource", deletedDynamicSqlSource);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		variable = properties.getProperty("variable");
		variableDelete = properties.getProperty("variableDelete");
		variableDeleteNot = properties.getProperty("variableDeleteNot");
		sqlType = properties.getProperty("sqlType");
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getVariableDelete() {
		return variableDelete;
	}

	public void setVariableDelete(String variableDelete) {
		this.variableDelete = variableDelete;
	}

	public String getVariableDeleteNot() {
		return variableDeleteNot;
	}

	public void setVariableDeleteNot(String variableDeleteNot) {
		this.variableDeleteNot = variableDeleteNot;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

}
