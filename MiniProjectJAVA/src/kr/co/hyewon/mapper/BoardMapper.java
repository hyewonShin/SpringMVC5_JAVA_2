package kr.co.hyewon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import kr.co.hyewon.beans.ContentBean;

public interface BoardMapper {
	
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class)
	// 오라클에서는 from 다음에 테이블 생략이 불가능 하기 때문에 dual이라는 가상의 테이블을 넣어준다.
	// statement의 결과값이  keyProperty = "content_idx" 에 담긴다.
	// 그리고  keyProperty = "content_idx"에 담긴 값은  writeContentBean에 담긴다.
	// boardController에 writeContentBean가 있기 떄문에 boardController에서도 사용 가능하다.
	// before = true는 아래의 메서드보다 먼저 실행한다는 뜻.  
	
	@Insert("insert into content_table(content_idx,content_subject,content_text, " +
			"content_file,content_writer_idx,content_board_idx,content_date) " +
			"values (#{content_idx}, #{content_subject},#{content_text},#{content_file, jdbcType=VARCHAR}, " +
			"#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
	
	
	@Select("select board_info_name " + 
			"from board_info_table " + 
			"where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
			

	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, " + 
			"       to_char(a1.content_date, 'YYYY-MM-DD') as content_date " + 
			"from content_table a1, user_table a2 " + 
			"where a1.content_writer_idx = a2.user_idx " + 
			"      and a1.content_board_idx = #{board_info_idx} " + 
			"order by a1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx);
	
	
	@Select("select a2.user_name as content_writer_name, " + 
			"    to_char(a1.content_date,'YYYY-MM-DD') as content_date, " + 
			"    a1.content_subject, a1.content_text, a1.content_file, a1.content_writer_idx " + 
			"from content_table a1, user_table a2 " + 
			"where a1.content_writer_idx = a2.user_idx " + 
			"   and content_idx=#{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
	@Update("update content_table " +
			"set content_subject = #{content_subject}, content_text = #{content_text}, " +
			"content_file = #{content_file, jdbcType=VARCHAR} " +
			"where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);

}
 

// NULL을 허용하는 컬럼은 #{content_file, jdbcType=VARCHAR}와 같이 타입을 명시해줘야 된다.