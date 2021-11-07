package kr.co.hyewon.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.hyewon.beans.UserBean;

public interface UserMapper {

	// 아이디 중복확인 (유효성 검사)
	@Select("select user_name " +
			"from user_table " + 
			"where user_id = #{user_id}")
	String checkUserIdExist(String user_id);
	
	// 회원가입 (서버에 저장_
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
			"values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(UserBean joinUserBean);
}
