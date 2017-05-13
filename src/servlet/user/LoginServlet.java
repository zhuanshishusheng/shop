package servlet.user;

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
import bean.SortBean;
import bean.UserBean;
import dao.SortDao;
import dao.UserDao;
import util.CommonUtil;


public class LoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, Object> data = new HashMap<String, Object>();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (username == null || username == "" || password == null
				|| password == "") {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_REQUEST_PARAMETER_ERROR);
			data.put("error", "请求参数错误或缺失");
			CommonUtil.renderJson(resp, data);
		}

		UserDao dao = new UserDao();

		UserBean user = dao.login(username, password);

		if (user == null) {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_NOT_EXIST_USER);
			data.put("error", "用户名不存在或密码错误");
		} else {
			
			
			data.put("response", "login");
			data.put("data",user);
		
		}


		CommonUtil.renderJson(resp, data);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}


}
