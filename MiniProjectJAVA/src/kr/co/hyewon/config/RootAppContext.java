package kr.co.hyewon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hyewon.beans.UserBean;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
// 데이터를 저장,관리 목적으로 사용하는 빈들을 정의 
@Configuration
public class RootAppContext {

	//로그인 영역에 사용할 bean을 따로 정의해준다.
	//브라우저창이 닫히기 전까지만 로그인 유지하기 위해 session영역에 넣어준다.
	@Bean("loginUserBean")
	@SessionScope 
	public UserBean loginUserBean() {
		return new UserBean();
	}
}
