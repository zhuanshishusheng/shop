package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import bean.SortBean;
import util.DataSourceManager;

public class SortDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<Object> getSortImageList(String type) {
		String sql = "select * from sort where type = ?";
		try {
			List<Object> list = runner.query(sql,new ColumnListHandler("image"),type);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
