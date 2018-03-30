package co.kr.jcone.server.bean;

import java.util.Date;

public class UserBean {
	
	private String user_id;
	private String user_name;
	private String user_password;
	private String position;
	private String responsibility;
	private String access_ip;
	private String user_initialize;
	private Date row_input_date;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getAccess_ip() {
		return access_ip;
	}
	public void setAccess_ip(String access_ip) {
		this.access_ip = access_ip;
	}
	public String getUser_initialize() {
		return user_initialize;
	}
	public void setUser_initialize(String user_initialize) {
		this.user_initialize = user_initialize;
	}
	public Date getRow_input_date() {
		return row_input_date;
	}
	public void setRow_input_date(Date row_input_date) {
		this.row_input_date = row_input_date;
	}
	
	

}
