package kr.co.hyewon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hyewon.beans.BoardInfoBean;

public interface TopMenuMapper {

	@Select("select board_info_idx, board_info_name " + 
			"from board_info_table " +
			"order by board_info_idx")
	List<BoardInfoBean> getTopMenuList();  //BoardInfoBean은 반환타입/getTopMenuList는 메서드 이.
}
