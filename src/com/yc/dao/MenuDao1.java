package com.yc.dao;

import java.io.IOException;
import java.security.Policy.Parameters;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;


public class MenuDao1 {
	
	DBHelper db = new DBHelper();
	//显示所有菜系
		public List<Map<String, Object>> findAllSort() throws SQLException,IOException{
			String sql = "select sid,sname from tb_sort";
			return db.findMultiObject(sql, null);
		}
		
		//显示所有菜品
		public List<Map<String, Object>> findAllMenu() throws SQLException,IOException{
			String sql = "select mid,mname,code,sname,unit_price,unit  from tb_menu,tb_sort where tb_menu.sid=tb_sort.sid ";
			return db.findMultiObject(sql, null);
		}
		
		public List<Map<String, Object>> findMenu(String sname) throws SQLException,IOException{
			String sql = "select mid,mname,code,sname,unit_price,unit  from tb_menu,tb_sort where tb_menu.sid=tb_sort.sid and sname = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(sname);
			return db.findMultiObject(sql, params);
		}

		//插入菜品
				public boolean registerMenu(List<Object>params)throws SQLException,IOException{
					String sql="insert into tb_menu values(seq_mid.nextval,?,?,?,?,?,1,'','')";
					int i=db.doUpdate(sql, params);
					if(i>0){
						return true;
					}else{
						return false;
					}
					 	
				}
		
		//删除菜品(status=0)
		public boolean deleteMenu(List<Object>params)throws SQLException,IOException{
			String sql="update tb_menu set status=? where mid = ?";
			int i=db.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
			 	
		}
		
		//根据id显示菜品
		public List<Map<String, Object>> findMenuByID(int mid) throws SQLException,IOException{
			String sql = "select mid,mname,code,sname,unit_price,unit from tb_menu,tb_sort where tb_menu.sid=tb_sort.sid and mid=?";
			List<Object> params = new ArrayList<Object>();
			params.add(mid);
			return db.findMultiObject(sql, params);
		}
		
		//根据id显示菜系
				public List<Map<String, Object>> findSortByID(int sid) throws SQLException,IOException{
					String sql = "select sid,sname from tb_sort where sid=?";
					List<Object> params = new ArrayList<Object>();
					params.add(sid);
					return db.findMultiObject(sql, params);
				}
				
				/**
				 * 根据菜名修改菜系
				 * @return
				 * @throws SQLException 
				 * @throws IOException 
				 */
				
				public int update(String mname, String code,int mid) throws IOException, SQLException{
					String sql = "update tb_menu set mname = ?,code=? where mid =? ";
					List<Object> params = new ArrayList<Object>();
					params.add(mname);
					params.add(code);
					params.add(mid);
					return db.doUpdate(sql, params);
				}
				
				
				
				/**
				 * 根据菜品名添加
				 * @return
				 * @throws SQLException 
				 * @throws IOException 
				 */
				
				public int addByName(String name) throws IOException, SQLException{
					String sql = "insert into tb_sort values(seq_sid.nextval,?,'','') ";
					List<Object> params = new ArrayList<Object>();
					params.add(name);
					return db.doUpdate(sql, params);
				}
				
				/**
				 * 热门菜品
				 * @return
				 * @throws SQLException 
				 * @throws IOException 
				 */
				
				public List<Map<String, Object>> popularMenu() throws SQLException,IOException{
					String sql = "select tm.mid,mname,code,sname,unit_price,sum(od_amount)od_amount from tb_menu tm inner join orderdetail od on od.mid = tm.mid inner join tb_sort ts on ts.sid = tm.sid  group by tm.mid,mname,code,sname,unit_price order by od_amount desc";
					return db.findMultiObject(sql, null);
				}
				
				
				//根据菜系显示所有菜品
				public List<Map<String, Object>> findAllMenuByCuisine(int sid) throws SQLException,IOException{
					String sql = "select mid,mname,code,sname,unit_price,unit  from tb_menu tm inner join tb_sort ts on tm.sid = ts.sid where ts.sid = ?";
					List<Object> params = new ArrayList<Object>();
					params.add(sid);
					return db.findMultiObject(sql, null);
				}
}
