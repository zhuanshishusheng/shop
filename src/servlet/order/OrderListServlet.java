package servlet.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import bean.GoodBean;
import bean.OrderBean;
import bean.OrderDetail;
import dao.CarDao;
import dao.OrderDao;
import dao.SortDao;
import util.CommonUtil;

public class OrderListServlet extends HttpServlet {
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
		
		OrderDao orderDao=new OrderDao();


			int type = Integer.parseInt(req.getParameter("type"));
			
			List<OrderBean> list = new OrderDao().getOrderList(userid, type);
			data.put("response", "orderList");
			data.put("orderList", list);
			CommonUtil.renderJson(resp, data);
	
		

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}


}
