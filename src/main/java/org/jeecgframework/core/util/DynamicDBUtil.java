package org.jeecgframework.core.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.online.def.CgReportConstant;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.alibaba.fastjson.JSONObject;

import oracle.jdbc.driver.OracleTypes;

/**
 * Spring JDBC 实时数据库访问
 * 
 * @author chenguobin
 * @date 2014-09-05
 * @version 1.0
 */
public class DynamicDBUtil {
	private static final Logger logger = Logger.getLogger(DynamicDBUtil.class);
	/**
	 * 多数据连接池
	 */
	private static Map<String, BasicDataSource> dbSources = new HashMap<String, BasicDataSource>();

	/**
	 * 获取数据源【最底层方法，不要随便调用】
	 * 
	 * @param dynamicSourceEntity
	 * @return
	 */
	@Deprecated
	private static BasicDataSource getJdbcDataSource(final DynamicDataSourceEntity dynamicSourceEntity) {
		BasicDataSource dataSource = new BasicDataSource();

		String driverClassName = dynamicSourceEntity.getDriverClass();
		String url = dynamicSourceEntity.getUrl();
		String dbUser = dynamicSourceEntity.getDbUser();

		// 设置数据源的时候，要重新解密
		// String dbPassword = dynamicSourceEntity.getDbPassword();
		String dbPassword = PasswordUtil.decrypt(dynamicSourceEntity.getDbPassword(), dynamicSourceEntity.getDbUser(),
				PasswordUtil.getStaticSalt());// 解密字符串；

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	/**
	 * 通过dbkey,获取数据源
	 * 
	 * @param dbKey
	 * @return
	 */
	public static BasicDataSource getDbSourceBydbKey(final String dbKey) {
		// 获取多数据源配置
		DynamicDataSourceEntity dynamicSourceEntity = ResourceUtil.dynamicDataSourceMap.get(dbKey);
		// 先判断缓存中是否存在数据库链接
		BasicDataSource cacheDbSource = dbSources.get(dbKey);
		if (cacheDbSource != null && !cacheDbSource.isClosed()) {
			return cacheDbSource;
		} else {
			BasicDataSource dataSource = getJdbcDataSource(dynamicSourceEntity);
			dbSources.put(dbKey, dataSource);
			return dataSource;
		}
	}

	/**
	 * 关闭数据库连接池
	 * 
	 * @param dbKey
	 * @return
	 * @return
	 */
	public static void closeDBkey(final String dbKey) {
		BasicDataSource dataSource = getDbSourceBydbKey(dbKey);
		try {
			if (dataSource != null && !dataSource.isClosed()) {
				dataSource.getConnection().setAutoCommit(false);
				dataSource.getConnection().commit();
				dataSource.getConnection().close();
				dataSource.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将mysql的自动提交关闭 --2018-11-14
	 */
	public static void closeDBkey2(final String dbKey) {
		BasicDataSource dataSource = getDbSourceBydbKey(dbKey);
		try {
			if (dataSource != null && !dataSource.isClosed()) {
				dataSource.getConnection().setAutoCommit(false);//20181114
				//dataSource.getConnection().commit();
				dataSource.getConnection().close();
				dataSource.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static JdbcTemplate getJdbcTemplate(String dbKey) {
		BasicDataSource dataSource = getDbSourceBydbKey(dbKey);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	/**
	 * Executes the SQL statement in this <code>PreparedStatement</code> object,
	 * which must be an SQL Data Manipulation Language (DML) statement, such as
	 * <code>INSERT</code>, <code>UPDATE</code> or <code>DELETE</code>; or an
	 * SQL statement that returns nothing, such as a DDL statement.
	 */
	public static int update(final String dbKey, String sql, Object... param) {
		int effectCount = 0;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			effectCount = jdbcTemplate.update(sql);
		} else {
			effectCount = jdbcTemplate.update(sql, param);
		}
		return effectCount;
	}

	/**
	 * 支持miniDao语法操作的Update
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param data
	 *            sql语法中需要判断的数据及sql拼接注入中需要的数据
	 * @return
	 */
	public static int updateByHash(final String dbKey, String sql, HashMap<String, Object> data) {
		int effectCount = 0;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
		// 根据模板获取sql
		sql = FreemarkerParseFactory.parseTemplateContent(sql, data);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		effectCount = namedParameterJdbcTemplate.update(sql, data);
		return effectCount;
	}

	public static Object findOne(final String dbKey, String sql, Object... param) {
		List<Map<String, Object>> list;

		list = findList(dbKey, sql, param);

		if (ListUtils.isNullOrEmpty(list)) {
			logger.error("Except one, but not find actually");
		}

		if (list.size() > 1) {
			logger.error("Except one, but more than one actually");
		}

		return list.get(0);
	}

	/**
	 * 支持miniDao语法操作的查询 返回HashMap
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param data
	 *            sql语法中需要判断的数据及sql拼接注入中需要的数据
	 * @return
	 */
	public static Object findOneByHash(final String dbKey, String sql, HashMap<String, Object> data) {
		List<Map<String, Object>> list;
		list = findListByHash(dbKey, sql, data);
		if (ListUtils.isNullOrEmpty(list)) {
			logger.error("Except one, but not find actually");
		}
		if (list.size() > 1) {
			logger.error("Except one, but more than one actually");
		}
		return list.get(0);
	}

	/**
	 * 直接sql查询 根据clazz返回单个实例
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句
	 * @param clazz
	 *            返回实例的Class
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Object findOne(final String dbKey, String sql, Class<T> clazz, Object... param) {
		Map<String, Object> map = (Map<String, Object>) findOne(dbKey, sql, param);
		return ReflectHelper.setAll(clazz, map);
	}

	/**
	 * 支持miniDao语法操作的查询 返回单个实例
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param clazz
	 *            返回实例的Class
	 * @param data
	 *            sql语法中需要判断的数据及sql拼接注入中需要的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Object findOneByHash(final String dbKey, String sql, Class<T> clazz,
			HashMap<String, Object> data) {
		Map<String, Object> map = (Map<String, Object>) findOneByHash(dbKey, sql, data);
		return ReflectHelper.setAll(clazz, map);
	}

	/**
	 * @param querySql
	 * @return
	 */
	public static boolean isProcedure(String querySql) {
		return querySql.indexOf("{") >= 0 && querySql.indexOf("}") > 0 && querySql.indexOf("call") > 0;
	}

	public static List<Map<String, Object>> findList(final String dbKey, String sql, Object... param) {
		List<Map<String, Object>> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			list = jdbcTemplate.queryForList(sql);
		} else {
			list = jdbcTemplate.queryForList(sql, param);
		}
		return list;
	}

	public static List<Map<String, Object>> findListProcedureOld(final String dbKey, String sql, JSONObject varObj,
			Object... param) {
		final List<Map<String, Object>> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		// if (varObj.keySet().size() == 0) {
		// list = jdbcTemplate.queryForList(sql);
		// } else {

		// Object[] typeDef = makeJdbcArgs(sql, varObj);
		Object[] typeDef = new Object[3];

		ArrayList args = new ArrayList();
		ArrayList colum = new ArrayList();

		try {
			// colum = (ArrayList)
			// getProcedureMetaData("pckg_tar_lg_fundmanager.proc_tar_lg_fundmanager_query",
			// jdbcTemplate);
			colum = (ArrayList) getProcedureMetaData(sql.toUpperCase(), jdbcTemplate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// list =
		// jdbcTemplate.queryForList((String)typeDef[0],(Object[])typeDef[1],(int[])typeDef[2]);
		int[] types = new int[1];
		// args.add("");
		// types[0]=java.sql.Types.VARCHAR;
		typeDef[0] = "{call RainTest002()}";
		typeDef[1] = args.toArray();
		typeDef[2] = types;

		list = new ArrayList<Map<String, Object>>();

		jdbcTemplate.query((String) typeDef[0], (Object[]) typeDef[1], (int[]) typeDef[2], new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				ResultSetMetaData rsM = rs.getMetaData();

				while (rs.next()) {// 转换每行的返回值到Map中
					Map rowMap = new LinkedHashMap();

					for (int i = 1; i <= rsM.getColumnCount(); i++) {
						String colName = rsM.getColumnName(i);

						if (colName.equalsIgnoreCase("(expression)"))
							colName = "field";

						colName = getColumnName(rowMap, colName);

						rowMap.put(colName, rs.getObject(i));
					}

					list.add(rowMap);
				}

			}

		});

		// }
		try {
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Map<String, Object>> findListProcedureNew(final String dbKey, String sql, JSONObject varObj,
			Object... param) {
		List<Map<String, Object>> list = new ArrayList();
		Connection conn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
		try {
			sql = "{call pckg_tar_lg_fundmanager.proc_tar_lg_fundmanager_query()}";
			String procedureName = sql.replace("call", "").replace("{", "").replaceAll("}", "").replaceAll("[(,)]", "")
					.trim().toUpperCase();
			DataSource basicDS = getDbSourceBydbKey(dbKey);
			conn = basicDS.getConnection();
			ArrayList colum = (ArrayList) getProcedureMetaData(procedureName, jdbcTemplate);

			cstm = conn.prepareCall(
					"{call pckg_tar_lg_fundmanager.proc_tar_lg_fundmanager_query(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < 29; i++) {
				cstm.setString(i + 1, "");
			}
			cstm.registerOutParameter(30, OracleTypes.VARCHAR);
			cstm.registerOutParameter(31, OracleTypes.VARCHAR);
			cstm.registerOutParameter(32, OracleTypes.CURSOR);
			cstm.execute();
			rs = (ResultSet) cstm.getObject(32);
			ResultSetMetaData rsM = rs.getMetaData();

			while (rs.next()) {// 转换每行的返回值到Map中
				Map rowMap = new LinkedHashMap();

				for (int i = 1; i <= rsM.getColumnCount(); i++) {
					String colName = rsM.getColumnName(i);

					if (colName.equalsIgnoreCase("(expression)"))
						colName = "field";

					colName = getColumnName(rowMap, colName);

					rowMap.put(colName, rs.getObject(i));
				}

				list.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					conn.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (cstm != null) {
					cstm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 * @param dbKey
	 * @param sql
	 * @param varObj
	 * @param param
	 * @return
	 */
	public static List<Map<String, Object>> findListProcedure(final String dbKey, String sql, Map params, int p,
			int row) {
		List<Map<String, Object>> list = new ArrayList();
		Connection conn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
		try {
			String procedureName = sql.replace("call", "").replace("{", "").replaceAll("}", "").replaceAll("[(,)]", "")
					.trim().toUpperCase();
			DataSource basicDS = getDbSourceBydbKey(dbKey);
			conn = basicDS.getConnection();
			//
			// Map proPram = new LinkedHashMap();
			// ArrayList colum1 = (ArrayList)
			// getProcedureMetaData(procedureName.toUpperCase(), jdbcTemplate);
			// for (int i = 0; i < colum1.size(); i++) {
			// proPram.put(colum1.get(i), "");
			// }
			// 直接通过入参传入存储过程
			list = executeProcedure(procedureName, params, conn, p, row);
			//

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					conn.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (cstm != null) {
					cstm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static String getColumnName(Map rowMap, String colName) {
		if (!rowMap.containsKey(colName))
			return colName;
		else {
			int i = 0;
			do {
				i++;
			} while (rowMap.containsKey(colName + i));
			return colName + i;
		}

	}

	private static List<String> getProcedureMetaData(String procedureName, JdbcTemplate jdbcTemplate)
			throws SQLException {
		String[] procedure = procedureName.split("\\.");
		DataSource dS = jdbcTemplate.getDataSource();
		Connection conn = dS.getConnection();
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet rs = metaData.getProcedureColumns(procedure[0] == null ? null : procedure[0], null,
				procedure[1] == null ? null : procedure[1], null);
		List<String> cols = getProcedureColumns(rs);
		rs.close();
		return cols;
	}

	public static List<String> getProcedureColumns(ResultSet rs) throws SQLException {
		// 获得表头 结果固定(这里用不上,注释掉):PROCEDURE_CAT PROCEDURE_SCHEM PROCEDURE_NAME
		// COLUMN_NAME COLUMN_TYPE DATA_TYPE TYPE_NAME PRECISION LENGTH SCALE
		// RADIX NULLABLE REMARKS COLUMN_DEF SQL_DATA_TYPE SQL_DATETIME_SUB
		// CHAR_OCTET_LENGTH ORDINAL_POSITION IS_NULLABLE SPECIFIC_NAME SEQUENCE
		// OVERLOAD DEFAULT_VALUE
		// ResultSetMetaData rsmd=rs.getMetaData();
		/*
		 * for (int i = 0; i < rsmd.getColumnCount(); i++) {
		 * System.out.print(rsmd.getColumnName(i+1)+"\t"); }
		 */
		// 正文 开始 创建参数数组
		List<String> columnArrays = new ArrayList<String>();
		while (rs.next()) {
			/*
			 * for (int i = 0; i < rsmd.getColumnCount(); i++) {
			 * System.out.print(rs.getString(i+1)+"\t"); } System.out.println();
			 */
			String COLUMN_TYPE = rs.getString(5);// 字段类型 根据观察 1代表输入 4代表输出
			if (COLUMN_TYPE.equals("1")) {
				// 寻找 ColumnName
				columnArrays.add(rs.getString(4));
			}
		}
		return columnArrays;
	}

	public static Object[] makeJdbcArgs(String sql, JSONObject varObj) {
		Map<Integer, String> mapParam = new HashMap<Integer, String>();
		// 1.记录查询变量在sql中出现的位置
		for (String paramName : varObj.keySet()) {
			int fromIndex = 0;
			fromIndex = sql.indexOf(":" + paramName, fromIndex);
			while (fromIndex >= 0) {
				mapParam.put(fromIndex, paramName);
				fromIndex += paramName.length();
				fromIndex = sql.indexOf(":" + paramName, fromIndex);
			}

			sql = sql.replaceAll(":" + paramName, "?");
		}

		// 2.按照位置排序
		int[] paramPosi = new int[mapParam.size()];
		int j = 0;
		for (Integer posiVal : mapParam.keySet()) {
			paramPosi[j] = posiVal.intValue();
			j++;
		}

		Arrays.sort(paramPosi);

		// 3.购置按照占位符位置需要的查询参数数组
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Integer> argTypes = new ArrayList<>();

		for (Object posi : paramPosi) {
			String argName = mapParam.get(posi);
			String argTypeStr = varObj.getJSONObject(argName).getString("type");
			String argValueStr = varObj.getJSONObject(argName).getString("value");
			int argType = 0;

			switch (argTypeStr) {
			case "String":
				argType = java.sql.Types.VARCHAR;
				break;
			case "Date":
				argType = java.sql.Types.DATE;
				break;
			case "Integer":
				argType = java.sql.Types.INTEGER;
				break;

			default:
				break;
			}

			argTypes.add(argType);
			args.add(argValueStr);
		}

		int[] types = new int[argTypes.size()];
		for (int i = 0; i < types.length; i++) {
			types[i] = argTypes.get(i).intValue();
		}

		Object[] result = new Object[3];
		result[0] = sql;
		result[1] = args.toArray();
		result[2] = types;

		return result;
	}

	/**
	 * 支持miniDao语法操作的查询
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param data
	 *            sql语法中需要判断的数据及sql拼接注入中需要的数据
	 * @return
	 */
	public static List<Map<String, Object>> findListByHash(final String dbKey, String sql,
			HashMap<String, Object> data) {
		List<Map<String, Object>> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
		// 根据模板获取sql
		sql = FreemarkerParseFactory.parseTemplateContent(sql, data);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		list = namedParameterJdbcTemplate.queryForList(sql, data);
		return list;
	}

	// 此方法只能返回单列，不能返回实体类
	public static <T> List<T> findList(final String dbKey, String sql, Class<T> clazz, Object... param) {
		List<T> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

		if (ArrayUtils.isEmpty(param)) {
			list = jdbcTemplate.queryForList(sql, clazz);
		} else {
			list = jdbcTemplate.queryForList(sql, clazz, param);
		}
		return list;
	}

	/**
	 * 支持miniDao语法操作的查询 返回单列数据list
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param clazz
	 *            类型Long、String等
	 * @param data
	 *            sql语法中需要判断的数据及sql拼接注入中需要的数据
	 * @return
	 */
	public static <T> List<T> findListByHash(final String dbKey, String sql, Class<T> clazz,
			HashMap<String, Object> data) {
		List<T> list;
		JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
		// 根据模板获取sql
		sql = FreemarkerParseFactory.parseTemplateContent(sql, data);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		list = namedParameterJdbcTemplate.queryForList(sql, data, clazz);
		return list;
	}

	/**
	 * 直接sql查询 返回实体类列表
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param clazz
	 *            返回实体类列表的class
	 * @param param
	 *            sql拼接注入中需要的数据
	 * @return
	 */
	public static <T> List<T> findListEntrys(final String dbKey, String sql, Class<T> clazz, Object... param) {
		List<Map<String, Object>> queryList = findList(dbKey, sql, param);
		return ReflectHelper.transList2Entrys(queryList, clazz);
	}

	/**
	 * 支持miniDao语法操作的查询 返回实体类列表
	 * 
	 * @param dbKey
	 *            数据源标识
	 * @param sql
	 *            执行sql语句，sql支持minidao语法逻辑
	 * @param clazz
	 *            返回实体类列表的class
	 * @param data
	 *            sql语法中需要判断的数据及sql拼接注入中需要的数据
	 * @return
	 */
	public static <T> List<T> findListEntrysByHash(final String dbKey, String sql, Class<T> clazz,
			HashMap<String, Object> data) {
		List<Map<String, Object>> queryList = findListByHash(dbKey, sql, data);
		return ReflectHelper.transList2Entrys(queryList, clazz);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DynamicDataSourceEntity dynamicSourceEntity = new DynamicDataSourceEntity();

		String dbKey = "ORACLE";
		String driverClassName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@172.16.104.22:1521:cctads";
		String dbUser = "oatest";
		String dbPassword = "d5630883b79c7a98bc0c5c69ec40f843";

		dynamicSourceEntity.setDbKey(dbKey);
		dynamicSourceEntity.setDriverClass(driverClassName);
		dynamicSourceEntity.setUrl(url);
		dynamicSourceEntity.setDbUser(dbUser);
		dynamicSourceEntity.setDbPassword(dbPassword);

		// ResourceUtil.dynamicDataSourceMap.put(dbKey, dynamicSourceEntity);
		//
		String sql = "call raintest002(?)";
		// List l = DynamicDBUtil.findListProcedure(dbKey, sql, null);
		// for (int i = 0; i < l.size(); i++) {
		// System.out.println(l.get(i));
		// }
		// String sql = "<#if nlevel gt 2> insert into GWYUTEST003(id, sname,
		// nlevel) values ((select maxid from (select ifnull(max(id)+1,1) maxid
		// from GWYUTEST003) a),"
		// + " :sname, :nlevel)</#if>";
		// HashMap<String, Object> data = new HashMap<String, Object>();
		// data.put("sname", "aaa");
		// data.put("nlevel", 3);
		// DynamicDBUtil.updateByHash(dbKey, sql, data);
		//
		// sql = "SELECT * FROM GWYUTEST003 WHERE id = :id";data = new
		// HashMap<String, Object>();
		// data.put("id", 1);
		// Map<String, Object> aaa = (Map<String, Object>)
		// DynamicDBUtil.findOneByHash(dbKey, sql, data);
		// System.out.println(aaa.get("sname"));
		//
		// sql = "SELECT * FROM GWYUTEST003 WHERE id >= '${id}'";data = new
		// HashMap<String, Object>();
		// data.put("id", 2);
		// List<GwyuTest> bbb = DynamicDBUtil.findListEntrysByHash(dbKey, sql,
		// GwyuTest.class, data);
		// System.out.println(bbb);

		// List<Map<String, Object>> list = DynamicDBUtil.getList(jdbcTemplate,
		// sql);
		// System.out.println(list.size());
	}

	/**
	 * 执行存储过程（和参数顺序无关）
	 * 
	 * @param procName
	 *            存储过程名称
	 * @param procParam
	 *            参数（key需大写）
	 * @return
	 */
	public static List<Map<String, Object>> executeProcedure(String procName, Map<String, Object> procParam,
			Connection conn, int page, int row) {
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCall = "";
		Map<String, List<Integer>> outParaIdxMap = new LinkedHashMap<String, List<Integer>>();
		List<Map<String, Object>> list = new ArrayList();
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			sqlCall = "{call " + procName + "(";
			String pckgName = procName.lastIndexOf(".") > 0
					? procName.substring(0, procName.lastIndexOf(".")).toUpperCase() : null;// 有包体时
			String proc = procName.substring(procName.lastIndexOf(".") + 1, procName.length()).toUpperCase();
			rs = dbmd.getProcedureColumns(pckgName, "", proc, null);
			int i = 1;

			while (rs.next()) {// 拼装参数
				String columnName = rs.getString("COLUMN_NAME");
				Short columnType = rs.getShort("COLUMN_TYPE");
				int dataType = rs.getInt("DATA_TYPE") == 1111 ? -10 : rs.getInt("DATA_TYPE");
				List<Integer> outParaList = new ArrayList<Integer>();
				if (DatabaseMetaData.procedureColumnIn == columnType) {
					if (columnName.equalsIgnoreCase("begnum")) {
						sqlCall += columnName + "=>'" +(row*(page-1)+1) + "',";
					} else if (columnName.equalsIgnoreCase("fetchnum")) {
						sqlCall += columnName + "=>'" + row + "',";
					}else{
						String paramValue = "";
						if (null != procParam) {
							paramValue = (String) procParam.get(columnName.toUpperCase().replace("I_", ""));
							if (paramValue == null) {
								paramValue = "";
							} else {
								paramValue = paramValue.replace(">=", "").replace("<=", "").replace("LIKE", "")
										.replace(" = ", "").replace("'", "");
							}
						}
						sqlCall += columnName + "=>'" + paramValue + "',";// upper
																			// case
																			// default
					}
				} else if (DatabaseMetaData.procedureColumnOut == columnType) {// 输出参数
					sqlCall += columnName + "=>?,";
					outParaList.add(i);
					outParaList.add(dataType);
					outParaIdxMap.put(columnName, outParaList);

					i++;
				}

			}
			sqlCall = sqlCall.substring(0, sqlCall.lastIndexOf(",")) + ")}";// 去除参数最后一个逗号以及后面的部分
			System.out.println(" sql --> " + sqlCall);
			cs = conn.prepareCall(sqlCall);
			for (String key : outParaIdxMap.keySet()) {
				int idx = outParaIdxMap.get(key).get(0);// 顺序
				int dataType = outParaIdxMap.get(key).get(1);// dataType
				cs.registerOutParameter(idx, dataType);
			}
			cs.execute();
			for (String key : outParaIdxMap.keySet()) {// 组装输出参数值（保留输入参数值）
				int idx = outParaIdxMap.get(key).get(0);// 顺序
				procParam.put(key, cs.getObject(idx));
			}
			conn.commit();
			//
			for (Iterator<String> i1 = procParam.keySet().iterator(); i1.hasNext();) {
				String key = i1.next();
				System.out.println(" key = " + key + "; value = " + procParam.get(key));
				if (procParam.get(key) instanceof ResultSet) {
					rs = (ResultSet) procParam.get(key);
					ResultSetMetaData rsM = rs.getMetaData();

					while (rs.next()) {// 转换每行的返回值到Map中
						Map rowMap = new LinkedHashMap();

						for (int i11 = 1; i11 <= rsM.getColumnCount(); i11++) {
							String colName = rsM.getColumnName(i11);

							if (colName.equalsIgnoreCase("(expression)"))
								colName = "field";

							colName = getColumnName(rowMap, colName);

							rowMap.put(colName, rs.getObject(i11));
						}

						list.add(rowMap);
					}
				}
			}

			//
		} catch (Exception e) {
			System.err.println("SQLException proc=[" + sqlCall + "] ");
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			closeRsPsConn(rs, ps, conn);
		}
		return list;
	}

	private static void closeRsPsConn(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把key装换为大写便于后台处理
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> convertUpperCaseMap(Map<String, ?> map) {
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		for (String key : map.keySet()) {
			returnMap.put(key.toUpperCase(), map.get(key));
		}
		return returnMap;
	}

	public static class GwyuTest {
		public GwyuTest() {
		}

		private long id;
		private String sname;
		private long nlevel;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getSname() {
			return sname;
		}

		public void setSname(String sname) {
			this.sname = sname;
		}

		public long getNlevel() {
			return nlevel;
		}

		public void setNlevel(long nlevel) {
			this.nlevel = nlevel;
		}

	}

}
