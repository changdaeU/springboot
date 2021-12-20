package com.ezen.spg02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JavaController {
	@RequestMapping("/")
	public @ResponseBody String root() {
		// 함수 이름에 @ResponseBody가 있으면 리턴되는 문자열이 이름없는 페이지에 출력구문으로 직접 쓰여지게 됩니다.
		return "JSP in Gradel~!!";
	}
	
	@RequestMapping("/test1") // http://localhost:8070/test1
	public String test1() {
		return "main"; // 실제 호출 될 webapp/WEB-INF/views/main.jsp
	}
}
