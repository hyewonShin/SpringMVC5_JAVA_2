package kr.co.hyewon.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
//	@Resource(name = "loginUserBean")
//	private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
//		System.out.println(loginUserBean);
		
		//이클립스에서 실행한 파일이 복사되어 배포되고 실행되는 실제 경로
		//System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main";
	}
}


