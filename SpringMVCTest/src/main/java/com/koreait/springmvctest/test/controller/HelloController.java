package com.koreait.springmvctest.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//클라이언트의 테스트 요청을 처리하는 하위 컨트롤러
@Controller //저 동생컨트롤러에요~!!!
public class HelloController { //POJO를 추구!!
	
	@RequestMapping("/test")
	public String babo(Model model) {
		//3단계 : 알맞는 객체 일시키기(건너뛴다, 할게없음)
		System.out.println("babo메서드호출");
		
		//4단계 : 결과 저장
		//저장할 것이 있다면 결과를 저장하되, request 직접 저장하는게 아니라, 스프링이 제공하는 Model객체에
		//넣어두면, 자동적으로 request 담겨진 효과를 낸다(내부적으로 처리)
		model.addAttribute("msg","나의 첫 스프링MVC실행결과 성공");
		
		return "test/result";
	}

}
