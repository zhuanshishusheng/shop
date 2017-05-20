package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.DataSourceManager;
import bean.OrderBean;
import bean.OrderDetail;

public class OrderDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 查询订单
	 * 
	 * @param bean
	 * @return
	 */
	public List<OrderBean> getOrderList(String userId, int type) {
		try {
			switch (type) {
			case 1:
				String queryOneMinuteSql = "select * from orderinfo where userid=? and type='待付款'";
				List<OrderBean> infos = runner.query(queryOneMinuteSql,
						new BeanListHandler<OrderBean>(OrderBean.class),
						userId
				);
				return infos;
			case 2:
				queryOneMinuteSql = "select * from orderinfo where userid=? and type='待发货'";
				infos = runner.query(queryOneMinuteSql,
						new BeanListHandler<OrderBean>(OrderBean.class),userId);
				return infos;
			case 3:
				String queryCancelSql = "select * from orderinfo where userid=? and type='待收货'";
				infos = runner.query(queryCancelSql,
						new BeanListHandler<OrderBean>(OrderBean.class),userId);
				return infos;
			case 4:
				String querySql = "select * from orderinfo where userid=? and type='待评价'";
				infos = runner.query(querySql,
						new BeanListHandler<OrderBean>(OrderBean.class),userId);
				return infos;
			default:
				queryOneMinuteSql = "select * from orderinfo where userid=?";
				infos = runner.query(queryOneMinuteSql,
						new BeanListHandler<OrderBean>(OrderBean.class),userId);
				return infos;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;

	}

//	/**
//	 * 查询单个订单
//	 * 
//	 * @param 根据订单
//	 *            id
//	 * @return
//	 */
//	public OrderInfo getOrder(String id) {
//
//		String sql = "select * from order_info where orderId= ?";
//		try {
//			OrderInfo order = runner.query(sql, new BeanHandler<OrderInfo>(
//					OrderInfo.class), id);
//			return order;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}
//
//	/*
//	 * 
//	 * 
//	 */
//	public int getOrderCount(String userid) {
//
//		String sql = "select count(*) from order_info where userid= ?";
//		try {
//			Object result = runner.query(sql, new ScalarHandler(), userid);
//			if (result != null) {
//				return Integer.parseInt(String.valueOf(result));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//
//	}
//
//	/**
//	 * 取消状态
//	 * 
//	 * @param orderid
//	 * @return
//	 */
//	public int cancelOrder(String orderid) {
//		String sql = "update order_info set type=0 WHERE orderid = ?";
//		try {
//			int update = runner.update(sql, orderid);
//			return update;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

	/**
	 * 添加一条订单数据
	 * 
	 * @param info
	 */
	public void addOrder(OrderBean info) {
		String sql = "insert into orderinfo (orderid,userid,time,type,price) values (?,?,?,?,?)";
		try {
			runner.update(sql,info.getOrderid(),info.getUserid(),info.getTime(),info.getType(),info.getPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交结算
	 * 
	 * @param orderProDetail
	 */
	public void submitOrder(OrderDetail orderProDetail) {
		String sql = "insert into orderdetail (pid,pNum,orderid,seller,addressid) values (?,?,?,?,?)";
		try {
			runner.update(sql, orderProDetail.getPid(),
					orderProDetail.getpNum(),
					orderProDetail.getAddressid(),
					orderProDetail.getSeller(),
					orderProDetail.getAddressid());
		} catch (Exception e) {
			System.out.println("插入订单详情异常");
			e.printStackTrace();
		}
	}

	/**
	 * 获取订单对应的所有的订单详情
	 * 
	 * @param orderId
	 * @return
	 */
//	public List<OrderProDetail> getOrderDetailByOrderId(String orderId) {
//		String sql = "select * from order_detail where order_id= ?";
//		try {
//			List<OrderProDetail> orderDetailList = runner.query(sql,
//					new BeanListHandler<OrderProDetail>(OrderProDetail.class),
//					orderId);
//			return orderDetailList;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
