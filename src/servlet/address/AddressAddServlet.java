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
import bean.UserBean;
import dao.AddressDao;
import dao.CarDao;
import dao.SortDao;
import dao.UserDao;
import util.CommonUtil;

public class AddressAddServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	
		this.doGet(arg0,arg1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, Object> data = new HashMap<String, Object>();

		String userId = req.getParameter("userid");

		if (userId == null) {

			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(resp, data);
		}

	
			AddressDao dao = new AddressDao();

			
			/**
			 * 0是新增，1是更新
			 */
			String parameterType=req.getParameter("type");
			int type=Integer.parseInt(parameterType);
			
			
			

			AddressBean address = new AddressBean();
			address.setUserid(Integer.parseInt(userId));
			
			String parameterName = req.getParameter("name");
			parameterName = new String(parameterName.getBytes("iso-8859-1"), "utf-8");
			//parameterName = URLDecoder.decode(parameterName, "utf-8");
			address.setName(parameterName);
			
			address.setPhone(req.getParameter("phone"));
			
			String parameterProvince = req.getParameter("province");
			parameterProvince = new String(parameterProvince.getBytes("iso-8859-1"), "utf-8");
			//parameterProvince = URLDecoder.decode(parameterProvince, "utf-8");
			address.setProvince(parameterProvince);
			
			String parameterCity = req.getParameter("city");
			parameterCity = new String(parameterCity.getBytes("iso-8859-1"), "utf-8");
			//parameterCity = URLDecoder.decode(parameterCity, "utf-8");
			address.setCity(parameterCity);
			
			String parameterAddressArea = req.getParameter("area");
			parameterAddressArea = new String(parameterAddressArea.getBytes("iso-8859-1"), "utf-8");
			//parameterAddressArea = URLDecoder.decode(parameterAddressArea, "utf-8");
			address.setArea(parameterAddressArea);
			
			String parameterAddressDetail = req.getParameter("detail");
			parameterAddressDetail = new String(parameterAddressDetail.getBytes("iso-8859-1"), "utf-8");
			//parameterAddressDetail = URLDecoder.decode(parameterAddressDetail, "utf-8");
			address.setDetail(parameterAddressDetail);
			
//			address.setZipCode(request.getParameter("zipCode"));
//			address.setIsDefault(Integer.parseInt(request
//					.getParameter("isDefault")));
			
			if(type==0){
				String orderIdStr = System.currentTimeMillis() + "";
				String order_id = orderIdStr.substring(7, orderIdStr.length());
				address.setId(Integer.parseInt(order_id));
				dao.addAddress(address);
			}else if(type==1){
				int id=Integer.parseInt(req.getParameter("id"));
				address.setId(id);
				dao.updateAddress(address);
				
			}
			
			UserDao userdao=new UserDao();
			if(Boolean.parseBoolean(req.getParameter("isDefault"))){
				userdao.updateDefaultAddress(address);
			}
		
            UserBean user=userdao.getUserInfo(userId);
            data.put("response", "addAddress");
			data.put("data",user);
			CommonUtil.renderJson(resp, data);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
