package kr.co.hyewon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.hyewon.beans.BoardInfoBean;
import kr.co.hyewon.beans.ContentBean;
import kr.co.hyewon.service.MainService;
import kr.co.hyewon.service.TopMenuService;

@Controller
public class MainController {

	@Autowired
	private MainService MainService;
	
	@Autowired
	private TopMenuService topMenuService;

	@GetMapping("/main")
	public String main(Model model) {

		// 각 게시판별로 상위 5개의 글을 가져오기.
		ArrayList<List<ContentBean>> list = new ArrayList<List<ContentBean>>();

		for(int i = 1; i <=4 ; i++) {
			List<ContentBean> list1 = MainService.getMainList(i);
			list.add(list1);
		}

		model.addAttribute("list",list);

		//게시판 이름 가져오기
		List<BoardInfoBean> board_list = topMenuService.getTopMenuList();
		model.addAttribute("board_list",board_list);
		
		return "main";
	}
}
