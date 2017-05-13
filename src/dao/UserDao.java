package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

	
	

}