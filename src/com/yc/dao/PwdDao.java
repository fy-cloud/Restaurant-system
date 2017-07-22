package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.internal.handlers.WizardHandler.New;

import com.yc.commons.DBHelper;
import com.yc.commons.LoginUserInfo;

public class PwdDao {

	DBHelper db = new DBHelper();
	
	//修改密码
	public boolean changePwd(List<Object>params)throws SQLException,IOException{
		String sql="update tb_user set password= ? where userid=?";
		int i=db.doUpdate(sql, params);
		if(i>0){
			return true;
		}else{
			return false;
		}
		 	
	}
	
	public boolean isPwd(String pwd){
		String sql = "select * from tb_user where password = ? and userid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(pwd);
		params.add(new OrderDao().findUserId(LoginUserInfo.username));
		Map<String, String> map = null;
		try {
			map = db.findSingleObject(sql, params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map!=null&&map.size()>0){
			return true;
		}else {
			return false;
		}
	}
}
