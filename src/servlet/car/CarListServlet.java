package servlet.car;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CarBean;
import bean.GoodBean;
import bean.SortBean;
import dao.CarDao;
import dao.SortDao;
import util.CommonUtil;

public class CarListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userid=req.getParameter("userid");
		
		CarDao dao=new CarDao();
		SortDao sortDao=new SortDao();
		List<CarBean> carList=dao.getCarList(Integer.parseInt(userid));
		
		List<GoodBean> goodList=new ArrayList();
		
		for(CarBean bean:carList){
			goodList.add(sortDao.getGood(bean.getGoodid()));
		}
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "carlist");
		data.put("data", goodList);
		CommonUtil.renderJson(resp, data);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}


}
