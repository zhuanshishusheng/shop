package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.AddressBean;
import util.DataSourceManager;

public class AddressDao {

	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<AddressBean> getAddressList(String userId) {
		String sql = "select * from address where userId=?";
		try {
			List<AddressBean> addressList = runner.query(sql,
					new BeanListHandler<AddressBean>(AddressBean.class), userId);
			return addressList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AddressBean getAddressInfoById(int addressId) {
		String sql = "select * from address where id=?";

		try {
			AddressBean address = runner.query(sql, new BeanHandler<AddressBean>(
					AddressBean.class), addressId);
			return address;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public Address getDefaultAddressInfo(String userId) {
//
//		String sql = "select * from address where userId=? and isDefault=1";
//		try {
//			Address address = runner.query(sql, new BeanHandler<Address>(
//					Address.class), userId);
//
//			if (address != null) {
//				address.setIsDefault(1);
//			}
//
//			return address;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	/**
	 * 设置默认收货地址
	 * 
	 * @param userid
	 * @param id
	 */
//	public void setDefault(String id) {
//
//		String sql1 = "update address set isDefault=0 where isDefault=1";
//		String sql2 = "update address set isDefault=1 WHERE id = ?";
//		try {
//			runner.update(sql1);
//			runner.update(sql2, id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	/**
	 * 地址保存，可能是修改，也可能是新增
	 * 
	 * @throws SQLException
	 */
//	public void saveAddress(AddressBean address, String userId) {
//		if (address.getId() == 0) {
//			String sql = "INSERT INTO address(name, phoneNumber,province,city,addressArea,addressDetail,userId,zipCode,isDefault) VALUES(?,?,?,?,?,?,?,?,?)";
//			try {
//				runner.update(sql, address.getName(), address.getPhoneNumber(),
//						address.getProvince(), address.getCity(), address
//								.getAddressArea(), address.getAddressDetail(),
//						userId, address.getZipCode(), address.getIsDefault());
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} else {
//			String sql = "update address set name=?, phoneNumber=?,province=?,city=?,addressArea=?,addressDetail=?,userId=?,zipCode=?,isDefault=? where id=?";
//			try {
//				runner.update(sql, address.getName(), address.getPhoneNumber(),
//						address.getProvince(), address.getCity(), address
//								.getAddressArea(), address.getAddressDetail(),
//						userId, address.getZipCode(), address.getIsDefault(),
//						address.getId());
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
//	}
	
	public void addAddress(AddressBean address){
		String sql = "INSERT INTO address(id, userid,phone,province,city,area,detail,name) VALUES(?,?,?,?,?,?,?,?)";
		try {
			runner.update(sql, address.getId(), address.getUserid(),
					address.getPhone(), address.getProvince(), address
							.getCity(), address.getArea(),address.getDetail(),address.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateAddress(AddressBean address){
		String sql = "update address set phone=?, province=?,city=?,area=?,detail=?,name=? where id=?";
		try {
			runner.update(sql, address.getPhone(), address.getProvince(),
					 address.getCity(), address
							.getArea(), address.getDetail(),
					address.getName(),address.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteAddress(String id) {
		String sql = "delete from address where id=?";
		try {
			runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
