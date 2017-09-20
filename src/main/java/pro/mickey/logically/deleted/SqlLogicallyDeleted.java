package pro.mickey.logically.deleted;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;

public class SqlLogicallyDeleted {

	private String variable;
	private String variable_delete;
	private String variable_delete_not;
	private String sql_type;

	public SqlLogicallyDeleted(String variable, String variable_delete, String variable_delete_not, String sql_type) {
		this.variable = variable;
		this.variable_delete = variable_delete;
		this.variable_delete_not = variable_delete_not;
		this.sql_type = sql_type;
	}

	public String logicallyDeleted(String sql) {
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, sql_type);
		List<SQLStatement> stmtList = parser.parseStatementList();
		SQLStatement stmt = stmtList.get(0);

		// 是删除语句
		if (stmt instanceof SQLDeleteStatement) {
			SQLDeleteStatement sstmt = (SQLDeleteStatement) stmt;
			return deleteToUpdate(sstmt);
		}
		// 是查询语句
		else if (stmt instanceof SQLSelectStatement) {
			SQLSelectStatement sstmt = (SQLSelectStatement) stmt;
			return selectAddWhere(sstmt);
		}
		return sql;
	}

	/**
	 * 删除语句变更新字段语�?
	 * 
	 * @param buf
	 * @param deleteStatement
	 */
	private String deleteToUpdate(SQLDeleteStatement deleteStatement) {
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ");

		deleteStatement.getTableSource().output(buf);
		buf.append(" SET ");
		{
			List<String> list = getFrom(deleteStatement.getTableSource());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					if (i != 0)
						buf.append(", ");
					String as = list.get(i);
					StringBuffer whereSql = new StringBuffer();
					if (as != null && as.length() > 0) {
						whereSql.append(as);
						whereSql.append(".");
					}
					whereSql.append(variable);
					whereSql.append(" = ");
					whereSql.append(variable_delete);
					buf.append(whereSql);
				}
			}

		}

		if (deleteStatement.getWhere() != null) {
			buf.append(" WHERE ");
			SQLExpr opExpr = deleteStatement.getWhere();
			buf.append(SQLUtils.toSQLString(opExpr));
		}
		return buf.toString();
	}

	/**
	 * 给Select加上删除where
	 * 
	 * @param tableFrom
	 * @param queryBlock
	 */
	private String selectAddWhere(SQLSelectStatement sstmt) {

		SQLSelect sqlselect = sstmt.getSelect();
		SQLSelectQueryBlock query = (SQLSelectQueryBlock) sqlselect.getQuery();

		if (query != null && query.getFrom() != null) {
			List<String> list = getFrom(query.getFrom());
			if (list != null) {
				for (String as : list) {
					StringBuffer whereSql = new StringBuffer();
					if (as != null && as.length() > 0) {
						whereSql.append(as);
						whereSql.append(".");
					}
					whereSql.append(variable);
					whereSql.append(" = ");
					whereSql.append(variable_delete_not);
					SQLExpr expr = new SQLExprParser(whereSql.toString(), sql_type).expr();
					query.addWhere(expr);
				}
			}
		}
		return sstmt.toString();
	}

	/**
	 * 查询from的表 �? as值，没有返回NUll
	 * 
	 * @param from
	 * @return
	 */
	private List<String> getFrom(SQLTableSource tableFrom) {
		StringBuffer buffer = new StringBuffer();
		tableFrom.accept(new SQLASTOutputVisitor(buffer));
		String tables = buffer.toString();

		tables = tables.split("\n")[0];

		List<String> list = new ArrayList<String>();
		if (buffer == null || buffer.length() <= 0)
			return null;
		String[] tabs = tables.split(", ");
		for (String tab : tabs) {
			String[] names = tab.split(" ");
			if (names.length > 1)
				list.add(names[1]);
			else
				list.add(null);
		}
		return list;
	}
}
