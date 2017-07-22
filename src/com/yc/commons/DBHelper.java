package com.yc.commons;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	static {
		// 加载驱动，整个系统中只需要加载一次到JVM中即可
		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(Myproperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取连接对象
	public Connection getConn() {

		Connection conn = null;
		try {
			// conn
			// =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
			conn = DriverManager.getConnection(Myproperties.getInstance()
					.getProperty("url"), Myproperties.getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连续对象
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		// 关闭结果对象
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭语句对象
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 关闭连接对象
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeAll1(ResultSet rs, Statement stmt, Connection conn) {
		// 关闭结果对象
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭语句对象
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 关闭连接对象
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置参数的方法 pstmt 预编译对象 params 传入的设置值的集合 SQLException
	 * 
	 * @throws SQLException
	 * 
	 */
	private void setParams(PreparedStatement pstmt, List<Object> params)
			throws SQLException {
		if (null != params && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				if(params.get(i) instanceof Date){
					pstmt.setDate(i + 1, (Date)params.get(i));
				}else {
					pstmt.setString(i + 1, params.get(i).toString());
				}                                                   // params
																	// 中存储值的顺序与？顺序一致
			}
		}
	}

	/**
	 * 增 删 改单条sql语句 sql params
	 * 
	 * 
	 */

	public int doUpdate(String sql, List<Object> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 1;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);// 参数设置
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(null, pstmt, conn);
		}
		return result;
	}

	/**
	 * 批量处理 多条sql语句的执行，多条sql语句的结果要么一起成功，要么一起失败 手动设置事务提交
	 * 
	 * @param sqls
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int doUpdate(List<String> sqls, List<List<Object>> params)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			conn = this.getConn();
			conn.setAutoCommit(false);// 关闭隐式事务，即执行sql语句后需要手动提交
			if (null != sqls && sqls.size() > 0) {
				for (int i = 0; i < sqls.size(); i++) {
					String sql = sqls.get(i);
					pstmt = conn.prepareStatement(sql);
					// 根据当前sql语句设置
					this.setParams(pstmt, params.get(i));
					result = pstmt.executeUpdate();
				}
			}
			// 提交事务
			conn.commit();
		} catch (Exception e) {
			// 回滚事务
			conn.rollback();
			throw e;
		} finally {
			// 恢复现场
			conn.setAutoCommit(true);
			this.closeAll(null, pstmt, conn);
		}
		return result;
	}

	/**
	 * 查询多条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findMultiObject(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = this.getAllColumnNames(rs);// 获取结果集中所有列表
			while (rs.next()) {
				map = new HashMap<String, Object>();
				for (String cn : columnNames) {// 循环列名，将列表作为Map的键，根据列表获取到每个列的值
					map.put(cn, rs.getObject(cn));
				}
				list.add(map);
			}
		} finally {
			this.closeAll(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 查询单条记录 select * from 表名 where id=1
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, String> findSingleObject(String sql, List<Object> params)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> map = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = this.getAllColumnNames(rs);// 获取结果集中所有列表
			if (rs.next()) {
				map = new HashMap<String, String>();
				for (String cn : columnNames) {// 循环列名，将列表作为Map的键，根据列表获取到每个列的值
					map.put(cn, rs.getString(cn));
				}
			}
		} finally {
			this.closeAll(rs, pstmt, conn);
		}

		return map;
	}

	/**
	 * 根据结果集对象获取所有列名 存在一个list集合中，jdbc2.0取元数据
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<String> getAllColumnNames(ResultSet rs) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		if (null != rs) {
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				columnNames.add(rs.getMetaData().getColumnName(i + 1));
			}
		}
		return columnNames;

	}

}
