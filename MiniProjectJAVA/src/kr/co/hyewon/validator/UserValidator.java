package kr.co.hyewon.validator;

import javax.validation.Validator;

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
		
		if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
			errors.rejectValue("user_pw", "NotEquals");
			errors.rejectValue("user_pw2", "NotEquals");
			// user_pw,user_pw2 에 대한 오류는 NotEquals로 설정해준다.
			
		}
		
		if(userBean.isUserIdExist() == false) {
			errors.rejectValue("user_id", "DontCheckUserIdExist");
		}
	}
}