package kr.co.hyewon.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hyewon.beans.BoardInfoBean;
import kr.co.hyewon.mapper.TopMenuMapper;

// 기능상 @component와 동일하지만 역할을 명시한다.
// DAO 역할을 하는 클래스는 @Repository를 사용한다.
@Repository  
public class TopMenuDao {

	@Autowired
	private TopMenuMapper topMenuMapper;
	
	public List<BoardInfoBean> getTopMenuList(){
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		return topMenuList;
	}
}
