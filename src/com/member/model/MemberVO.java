package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	private String mem_no; // Not Null (PK)
	private String exp_no; // (FK)
	private String memg_gr; // Not Null 0：一般會員 1：資深爸爸媽媽 2：專家
	private String mem_acct; // Not Null
	private String mem_pwd; // Not Null
	private Date mem_joind; // Not Null
	private Date mem_updated;
	private String mem_name; // Not Null
	private String mem_aka;
	private byte[] mem_photo; 
	private String mem_adds; // Not Null
	private String mem_addc;
	private String mem_phone;
	private String mem_mail;
	private String mem_intro_b;
	private String mem_is_sell; // Not Null 0：否 1：是
	private String mem_acct_s;
	private String mem_intro_s;
	private Integer mem_point_s;
	private String mem_intro_e;
	private String mem_is_stop; // Not Null 0：否 1：是
	private Integer mem_point_b;
	
	public MemberVO() {
		super();
	}

	
	public byte[] getMem_photo() {
		return mem_photo;
	}


	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}


	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getExp_no() {
		return exp_no;
	}

	public void setExp_no(String exp_no) {
		this.exp_no = exp_no;
	}

	public String getMemg_gr() {
		return memg_gr;
	}

	public void setMemg_gr(String memg_gr) {
		this.memg_gr = memg_gr;
	}

	public String getMem_acct() {
		return mem_acct;
	}

	public void setMem_acct(String mem_acct) {
		this.mem_acct = mem_acct;
	}

	public String getMem_pwd() {
		return mem_pwd;
	}

	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public Date getMem_joind() {
		return mem_joind;
	}

	public void setMem_joind(Date mem_joind) {
		this.mem_joind = mem_joind;
	}

	public Date getMem_updated() {
		return mem_updated;
	}

	public void setMem_updated(Date mem_updated) {
		this.mem_updated = mem_updated;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_aka() {
		return mem_aka;
	}

	public void setMem_aka(String mem_aka) {
		this.mem_aka = mem_aka;
	}

	public String getMem_adds() {
		return mem_adds;
	}

	public void setMem_adds(String mem_adds) {
		this.mem_adds = mem_adds;
	}

	public String getMem_addc() {
		return mem_addc;
	}

	public void setMem_addc(String mem_addc) {
		this.mem_addc = mem_addc;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_mail() {
		return mem_mail;
	}

	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public String getMem_intro_b() {
		return mem_intro_b;
	}

	public void setMem_intro_b(String mem_intro_b) {
		this.mem_intro_b = mem_intro_b;
	}

	public String getMem_is_sell() {
		return mem_is_sell;
	}

	public void setMem_is_sell(String mem_is_sell) {
		this.mem_is_sell = mem_is_sell;
	}

	public String getMem_acct_s() {
		return mem_acct_s;
	}

	public void setMem_acct_s(String mem_acct_s) {
		this.mem_acct_s = mem_acct_s;
	}

	public String getMem_intro_s() {
		return mem_intro_s;
	}

	public void setMem_intro_s(String mem_intro_s) {
		this.mem_intro_s = mem_intro_s;
	}

	public Integer getMem_point_s() {
		return mem_point_s;
	}

	public void setMem_point_s(Integer mem_point_s) {
		this.mem_point_s = mem_point_s;
	}

	public String getMem_intro_e() {
		return mem_intro_e;
	}

	public void setMem_intro_e(String mem_intro_e) {
		this.mem_intro_e = mem_intro_e;
	}

	public String getMem_is_stop() {
		return mem_is_stop;
	}

	public void setMem_is_stop(String mem_is_stop) {
		this.mem_is_stop = mem_is_stop;
	}

	public Integer getMem_point_b() {
		return mem_point_b;
	}

	public void setMem_point_b(Integer mem_point_b) {
		this.mem_point_b = mem_point_b;
	}
	
	

}
