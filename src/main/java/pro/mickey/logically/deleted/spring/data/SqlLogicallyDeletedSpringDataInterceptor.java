package pro.mickey.logically.deleted.spring.data;

import org.hibernate.EmptyInterceptor;

import pro.mickey.logically.deleted.SqlLogicallyDeleted;

public class SqlLogicallyDeletedSpringDataInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	private static String variable;
	private static String variableDelete;
	private static String variableDeleteNot;
	private static String sqlType;

	@Override
	public String onPrepareStatement(String sql) {
		SqlLogicallyDeleted deleted = new SqlLogicallyDeleted(variable, variableDelete, variableDeleteNot, sqlType);
		return deleted.logicallyDeleted(sql);
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		SqlLogicallyDeletedSpringDataInterceptor.variable = variable;
	}

	public String getVariableDelete() {
		return variableDelete;
	}

	public void setVariableDelete(String variableDelete) {
		SqlLogicallyDeletedSpringDataInterceptor.variableDelete = variableDelete;
	}

	public String getVariableDeleteNot() {
		return variableDeleteNot;
	}

	public void setVariableDeleteNot(String variableDeleteNot) {
		SqlLogicallyDeletedSpringDataInterceptor.variableDeleteNot = variableDeleteNot;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		SqlLogicallyDeletedSpringDataInterceptor.sqlType = sqlType;
	}

}
