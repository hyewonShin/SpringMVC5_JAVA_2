package kr.co.hyewon.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.hyewon.beans.BoardInfoBean;
import kr.co.hyewon.beans.UserBean;
import kr.co.hyewon.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{

	
//	@Autowired
//	private TopMenuService topMenuService;
	
// ** 주의할 점 **
// Interceptor에서는 위와 같이 @Autowired를  사용해서 자동주입을 받지 못한다!
//그래서 Interceptor을 등록하는 곳에서 bean을 주입받은 다음에 생성자로 넘겨서 작업해줘야 한다.
	
	private TopMenuService topMenuService;
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}
  