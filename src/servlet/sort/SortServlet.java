package servlet.sort;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SortBean;
import dao.SortDao;
import util.CommonUtil;

/**
 * http://localhost:8080//shop/sort?type=recommend
 * @author Administrator
 *
 */
public class SortServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type=req.getParameter("type");
		
		SortDao dao=new SortDao();
		List<SortBean> list=dao.getSortImageList(type);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "sort");
		data.put("data", list);
		CommonUtil.renderJson(resp, data);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
