package co.kr.jcone.server.bean;

import java.util.Date;

public class GroupBean{
	
	// Group_information
	private String group_id;
	private String group_name;
	private String group_description;
	private String group_type;
	private String user_id;
	private String user_name;
	private Date row_input_date;
	
	// group_member
	private String member_id;
	private String member_type;
	
	//folder
	private String folder_id;
	private String folder_type;
	private String folder_name;
	private String folder_description;
	private String folder_level;
	private String parent_folder_id;
	private String folder_path;
	
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_description() {
		return group_description;
	}
	public void setGroup_description(String group_description) {
		this.group_description = group_description;
	}
	public String getGroup_type() {
		return group_type;
	}
	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}
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
	public Date getRow_input_date() {
		return row_input_date;
	}
	public void setRow_input_date(Date row_input_date) {
		this.row_input_date = row_input_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public String getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}
	public String getFolder_type() {
		return folder_type;
	}
	public void setFolder_type(String folder_type) {
		this.folder_type = folder_type;
	}
	public String getFolder_name() {
		return folder_name;
	}
	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}
	public String getFolder_description() {
		return folder_description;
	}
	public void setFolder_description(String folder_description) {
		this.folder_description = folder_description;
	}
	public String getFolder_level() {
		return folder_level;
	}
	public void setFolder_level(String folder_level) {
		this.folder_level = folder_level;
	}
	public String getParent_folder_id() {
		return parent_folder_id;
	}
	public void setParent_folder_id(String parent_folder_id) {
		this.parent_folder_id = parent_folder_id;
	}
	public String getFolder_path() {
		return folder_path;
	}
	public void setFolder_path(String folder_path) {
		this.folder_path = folder_path;
	}
	
	
	
	

}
