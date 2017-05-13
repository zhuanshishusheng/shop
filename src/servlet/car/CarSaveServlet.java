package servlet.car;

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

import bean.CarBean;
import bean.GoodBean;
import bean.SortBean;
import bean.UserBean;
import dao.CarDao;
import dao.SortDao;
import dao.UserDao;
import util.CommonUtil;


public class CarSaveServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("hello");
		Map<String, Object> data = new HashMap<String, Object>();

		String userId = req.getParameter("userid");

		if (userId == null) {

			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(resp, data);
		}

			CarDao dao = new CarDao();

			CarBean car=new CarBean();
			
			String parameterId = req.getParameter("userid");
			parameterId = new String(parameterId.getBytes("iso-8859-1"), "utf-8");
			car.setUserid(Integer.parseInt(parameterId));
			
			car.setGoodid(Integer.parseInt(req.getParameter("goodid")));
			
			car.setNum(Integer.parseInt(req.getParameter("num")));
			
			
			dao.saveCar(car,Integer.parseInt(userId));

			data.put("response", "save");
			data.put("data", "success");
			CommonUtil.renderJson(resp, data);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}


}
