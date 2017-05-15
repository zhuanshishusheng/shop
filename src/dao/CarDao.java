package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.CarBean;
import bean.GoodBean;
import bean.SortBean;
import util.DataSourceManager;

public class CarDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<CarBean> getCarList(int userId) {
		String sql = "select * from car where userid=?";
		try {
			List<CarBean> carList = runner.query(sql,
					new BeanListHandler<CarBean>(CarBean.class), userId);
			return carList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    /*
     * 删除购物车中信息
     */
	public void deleteCar(String userid,String goodid) {
		String sql = "delete from car where userid=? and goodid=?";
		try {
			runner.update(sql, userid,goodid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 地址保存，可能是修改，也可能是新增
	 * 
	 * @throws SQLException
	 */
	public void saveCar(CarBean car, int userid) {
			String sql = "INSERT INTO car(userid,goodid,num) VALUES(?,?,?)";
			try {
				runner.update(sql, userid,car.getGoodid(),car.getNum());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

//	public GoodBean getSortImageList(String goodid) {
//		String sql = "select * from sort where type = ?";
//		try {
//			List<SortBean> list = runner.query(sql,new BeanListHandler<SortBean>(SortBean.class),go);
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}


}
