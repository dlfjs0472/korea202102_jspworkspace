package com.koreait.shoppingmall.model.service.admin;

import java.util.List;

import com.koreait.shoppingmall.domain.Admin;

public interface AdminService {
	public Admin login(Admin admin);//로그인
	   public void regist(Admin admin);//관리자 등록
	   public Admin update(Admin admin);//관리자 수정
	   public void delete(Admin admin);//아이디, 패스워드가 모두 일치할떄 탈퇴시켜야 하기 때문
	   public List selectAll();

}
