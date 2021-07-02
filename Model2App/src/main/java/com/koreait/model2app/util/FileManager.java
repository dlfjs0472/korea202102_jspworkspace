package com.koreait.model2app.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.koreait.model2app.model.domain.License;
import com.koreait.model2app.model.domain.Member;

public class FileManager {
	
	//확장자만 추출하기 , 경로를 넘겨받아 확장자만 추출
	public static String getExt(String path) {
		//test.kkk.jpg 
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	
	//웹기반의 파일 업로드
	public static Member saveFile(HttpServletRequest request) {
		//아파치 파일 업로드를 이용한 파일 저장 및 파라미터 처리
				DiskFileItemFactory factory= new DiskFileItemFactory();
				ServletContext context=request.getServletContext(); //어플리 케이션의 정보를 가진 객체를 얻기  jsp에서의 application 내장객체 //일반 클래스이므로 request에서 끄집어 내야한다
				String realPath=context.getRealPath("/data"); //실제 물리적 저장경로 얻기
				factory.setRepository(new File(""));
				factory.setSizeThreshold(2*1024*1024);
				
				ServletFileUpload upload= new ServletFileUpload(factory);
				Member member=null;
				
				try {
					List<FileItem> items=upload.parseRequest(request); //요청 분석하여 업로드 실행
					member= new Member();// empty vo
					
					for(FileItem item: items) {
						if(item.isFormField()) {//text필드라면
							if (item.getFieldName().equals("name")) {
								member.setName(item.getString("utf-8"));//이름대입
							}else if(item.getFieldName().equals("phone")) {
								member.setPhone(item.getString("utf-8"));//연락처대입
							}else if(item.getFieldName().equals("addr")) {
								member.setAddr(item.getString("utf-8"));//주소대입
							}else if(item.getFieldName().equals("title")) {
								License license = new License(); // empty vo
								license.setTitle(item.getString("utf-8"));//자격증 대입
								member.getList().add(license); //Member VO에 자격증 한개 추가
							}
						}else { //파일필드라면
							try {
								String ext=FileManager.getExt(item.getName()); //파일확장자
								String newName=System.currentTimeMillis()+"."+ext; //파일의 풀이름
								System.out.println(realPath+"/"+newName);
								item.write(new File(realPath+"/"+newName)); //파일저장!!
								member.setPhoto(newName);//Member VO에 생성된 파일명 저장
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
					}
					
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return member;
	}
	
	
	
	/*
	public static void main(String[] args) {
		System.out.println(getExt("test.kkk.jpg"));
	}
	*/
}