package kr.co.hyewon.validator;


import org.springframework.validation.Errors;

import kr.co.hyewon.beans.UserBean;


public class UserValidator implements org.springframework.validation.Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// UserBean 객체의 값들을 유효성 검사 해준다는 뜻.
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 유효성 검사를 위한 BEAN 객체들이 target에 담겨져 넘어온다.
		// target을 userBean으로 형변환 해준다.
		UserBean userBean = (UserBean)target;

		// 에러가 난 Bean의 이름 알아오기
		String beanName = errors.getObjectName();
		//System.out.println(beanName);

		// 비밀번호 재확인 유효성 검사(회원가입)
		// 회원가입과 로그인 부분이 같은 UserBean을 사용하기 때문에 
		// 로그인 부분에서 이 유효성 검사를 거치면서 오류가 나게 된다.
		// 해결방안 : 회원가입(joinUserBean)과 로그인(temploginUserBean)의 이름을 다르게 설정해주어서(분기)  
		// UserBean이 회원가입(joinUserBean)에 쓰일 경우에만 유효성 검사를 하게 한다. 
			if(beanName.equals("joinUserBean")) {
				if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
					errors.rejectValue("user_pw", "NotEquals");
					errors.rejectValue("user_pw2", "NotEquals");
					// user_pw,user_pw2 에 대한 오류는 NotEquals로 설정해준다.
				}

			// 아이디 중복검사 유효성 검사
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
	}
}