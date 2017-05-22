package servlet.address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.CarBean;
import bean.GoodBean;
import dao.AddressDao;
import dao.CarDao;
import dao.SortDao;
import util.CommonUtil;

public class AddressListServlet extends HttpServlet {
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

			AddressDao dao = new AddressDao();
			List<AddressBean> list = dao.getAddressList(userid);
			data.put("response", "addressList");
			data.put("addressList", list);
			CommonUtil.renderJson(resp, data);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
