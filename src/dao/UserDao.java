package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.AddressBean;
import bean.UserBean;
import util.DataSourceManager;

/**
 * 鎿嶄綔User琛ㄤ腑鐨凞ao绫伙紝
 * 
 */
public class UserDao {

	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 鐧婚檰鏃堕獙璇佺敤鎴峰悕瀵嗙爜
	 * 
	 * @return
	 */
	public UserBean login(String username, String password) {

		if (username == null || username.trim().length() == 0
				|| password == null || password.trim().length() == 0) {

			return null;
		}

		String sql = "SELECT * FROM user where username=? and password=?";
		try {
			UserBean user = runner.query(sql, new BeanHandler<UserBean>(UserBean.class),
					username, password);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean register(UserBean user) {
		String sql1 = "select * from user where username = ?";
		String sql2 = "INSERT INTO user(username,password) VALUES(?,?)";
		try {
			UserBean registerUser = runner.query(sql1, new BeanHandler<UserBean>(
					UserBean.class), user.getUsername());
			if (registerUser != null) {
				return true;
			} else if (user.getPassword() != null) {
				runner.update(sql2, user.getUsername(), user.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateDefaultAddress(AddressBean address){
		String sql = "update user set area=? where userid=?";
		try {
			runner.update(sql,address.getId(), address.getUserid());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public UserBean getUserInfo(String userid) {

		String sql = "SELECT * FROM user WHERE userid=?";
		try {
			UserBean userinfo = runner.query(sql,
					new BeanHandler<UserBean>(UserBean.class), userid);
			return userinfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}