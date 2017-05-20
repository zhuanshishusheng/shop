package bean;

public class OrderBean {
    private String orderid;
    private int userid;
    private String time;
    private String type;
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private int price;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
