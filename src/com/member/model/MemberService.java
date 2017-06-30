package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao = new MemberDAO();
	}
	
	public MemberVO addMember(String exp_no, String memg_gr, String mem_acct,
			String mem_pwd, Date mem_updated, String mem_name, String mem_aka, byte[] mem_photo,
			String mem_adds, String mem_addc, String mem_phone, String mem_mail,
			String mem_intro_b, String mem_is_sell, String mem_acct_s, String mem_intro_s,
			Integer mem_point_s, String mem_intro_e, Integer mem_point_b){
		
		MemberVO memberVO = new MemberVO();

		memberVO.setExp_no(exp_no);
		memberVO.setMemg_gr(memg_gr);
		memberVO.setMem_acct(mem_acct);
		memberVO.setMem_pwd(mem_pwd);
		memberVO.setMem_updated(mem_updated);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_aka(mem_aka);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_adds(mem_adds);
		memberVO.setMem_addc(mem_addc);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_mail(mem_mail);
		memberVO.setMem_intro_b(mem_intro_b);
		memberVO.setMem_is_sell(mem_is_sell);
		memberVO.setMem_acct_s(mem_acct_s);
		memberVO.setMem_intro_s(mem_intro_s);
		memberVO.setMem_point_s(mem_point_s);
		memberVO.setMem_intro_e(mem_intro_e);
		memberVO.setMem_point_b(mem_point_b);
		
		dao.insert(memberVO);
		return memberVO;
	}
	
	public MemberVO updateMember(String mem_no, String exp_no, String memg_gr, String mem_acct,
			String mem_pwd, Date mem_joind, Date mem_updated, String mem_name, String mem_aka, byte[] mem_photo,
			String mem_adds, String mem_addc, String mem_phone, String mem_mail,
			String mem_intro_b, String mem_is_sell, String mem_acct_s, String mem_intro_s,
			Integer mem_point_s, String mem_intro_e, String mem_is_stop, Integer mem_point_b){
		
		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setExp_no(exp_no);
		memberVO.setMemg_gr(memg_gr);
		memberVO.setMem_acct(mem_acct);
		memberVO.setMem_pwd(mem_pwd);
		memberVO.setMem_joind(mem_joind);
		memberVO.setMem_updated(mem_updated);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_aka(mem_aka);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_adds(mem_adds);
		memberVO.setMem_addc(mem_addc);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_mail(mem_mail);
		memberVO.setMem_intro_b(mem_intro_b);
		memberVO.setMem_is_sell(mem_is_sell);
		memberVO.setMem_acct_s(mem_acct_s);
		memberVO.setMem_intro_s(mem_intro_s);
		memberVO.setMem_point_s(mem_point_s);
		memberVO.setMem_intro_e(mem_intro_e);
		memberVO.setMem_is_stop(mem_is_stop);
		memberVO.setMem_point_b(mem_point_b);
		dao.update(memberVO);
		return memberVO;
	}

	public MemberVO getOneMember(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
}
