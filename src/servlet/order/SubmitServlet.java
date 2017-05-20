package servlet.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodBean;
import bean.OrderBean;
import bean.OrderDetail;
import dao.CarDao;
import dao.OrderDao;

import dao.SortDao;
import util.CommonUtil;
/*
 *  1.7.2 提交结算
 发送方式	发送URL	备注
 POST	/ordersumbit	需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录
 参数名称	描述	样例
 sku	商品ID:数量:属性ID|商品ID:数量:属性ID	1:3:1,2,3,4|2:2:2,3
 addressId	地址簿ID	139
 paymentType	支付方式	1
 deliveryType	送货时间	1
 invoiceType	发票类型	1=>个人 2=>单位
 invoiceTitle	发票抬头	传智慧播客教育科技有限公司
 invoiceContent	发票内容	1

 *  需要请求头
 RequestParams params = new RequestParams();
 params.addHeader("userid", "20428");
 params.addBodyParameter("sku", "1:3:1,2,3,4|2:2:2,3");
 params.addBodyParameter("addressId", "139");
 params.addBodyParameter("paymentType", "1");
 params.addBodyParameter("deliveryType", "1");
 params.addBodyParameter("invoiceType", "2");
 params.addBodyParameter("invoiceTitle", "传智慧播客教育科技有限公司");
 params.addBodyParameter("invoiceContent", "传智慧播客教育科技有限公司");
 mHttpUtils.send(HttpMethod.POST, HOST + "/ordersumbit", params,
 new RequestCallBack<String>() {});
 * 
 */

public class SubmitServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, Object> data = new HashMap<String, Object>();

		String userid = req.getParameter("userid");
		if (userid == null) {
			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(resp, data);
		}

		ServletContext context = getServletContext();

		Object user = context.getAttribute(userid);


			String sku = req.getParameter("sku");
			String[] results = sku.split("\\|");
			double totalPrice = 0;
			String orderIdStr = System.currentTimeMillis() + "";
			String order_id = orderIdStr.substring(7, orderIdStr.length());
			SortDao sortDao=new SortDao();
			OrderDao orderDao=new OrderDao();
			CarDao carDao=new CarDao();
			for (String result : results) {

				String[] productsStr = result.split(":");
				OrderDetail orderProDetail = new OrderDetail();

				String pid = productsStr[0];
				orderProDetail.setPid(Integer.parseInt(pid));

				int pNum = Integer.parseInt(productsStr[1]);
				orderProDetail.setpNum(pNum);

				GoodBean product = sortDao.getGood(Integer.parseInt(pid));
				totalPrice += product.getPrice();

		
				orderProDetail.setOrderid(order_id);
				
				
				String seller = new String(req.getParameter(
						"seller").getBytes("iso-8859-1"), "utf-8");
				orderProDetail.setSeller(seller);
				orderProDetail.setAddressid(Integer.parseInt(req
					.getParameter("addressid")));
			
				orderDao.submitOrder(orderProDetail);
				//删除购物车信息
				carDao.deleteCar(userid, pid);
				
			}

			data.put("response", "orderSubmit");
			
			OrderBean info=new OrderBean();
			info.setOrderid(order_id);
			info.setUserid(Integer.parseInt(userid));
			info.setPrice((int)totalPrice);
			info.setTime(System.currentTimeMillis() + "");
			info.setType("待付款");
			orderDao.addOrder(info);

			Map<String, Object> orderInfo = new HashMap<String, Object>();
			orderInfo.put("orderId", info.getOrderid());
			orderInfo.put("price", info.getPrice());
			data.put("orderInfo", orderInfo);

			CommonUtil.renderJson(resp, data);
		

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
