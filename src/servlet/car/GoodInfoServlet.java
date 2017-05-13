package servlet.car;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import bean.GoodBean;
import dao.SortDao;
import util.CommonUtil;

public class GoodInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String goodid = request.getParameter("goodid");

	
		
		SortDao dao = new SortDao();
		GoodBean good=dao.getGood(Integer.parseInt(goodid));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "good");
		data.put("product", good);
		
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
