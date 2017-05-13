package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import bean.GoodBean;
import bean.SortBean;
import util.DataSourceManager;

public class SortDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<SortBean> getSortImageList(String type) {
		String sql = "select * from sort where type = ?";
		try {
			List<SortBean> list = runner.query(sql,new BeanListHandler<SortBean>(SortBean.class),type);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GoodBean> getGoodsLlist(String type){
		String sql = "select * from goods where type = ?";
		try {
			List<GoodBean> list = runner.query(sql,new BeanListHandler<GoodBean>(GoodBean.class),type);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public GoodBean getGood(int goodid) {
		String sql = "select * from goods where id = ?";

		try {
			GoodBean good = runner.query(sql, new BeanHandler<GoodBean>(
					GoodBean.class), goodid);
			return good;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
