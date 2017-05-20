package servlet.user;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import bean.UserBean;
import dao.UserDao;
import util.CommonUtil;

public class RegisterServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, Object> data = new HashMap<String, Object>();
		req.setCharacterEncoding("utf-8");
		// Map<String, String> map = request.getParameterMap();

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

		UserBean bean = new UserBean();
		UserDao dao = new UserDao();
		try {
			bean.setUsername(username);
			bean.setPassword(password);
			
			boolean have = dao.register(bean);

			if (have) {
				data.put("response", "error");
				data.put(CommonUtil.ERROR_CODE,
						CommonUtil.ERROR_CODE_USERNAME_BEEN_REGISTERED);
				data.put("error", "该用户名已经被注册过了");
			} else {
				data.put("response", "register");
				data.put("userInfo", bean);
			}

			CommonUtil.renderJson(resp, data);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}



}
