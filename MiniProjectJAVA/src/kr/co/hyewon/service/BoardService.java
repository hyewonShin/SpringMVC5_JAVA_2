package kr.co.hyewon.service;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.hyewon.beans.ContentBean;
import kr.co.hyewon.beans.UserBean;
import kr.co.hyewon.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDao boardDao;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	//파일을 저장하는 메서드
	//파일 이름이 같으면 덮어씌워지는걸 방지하기 위해 현재 시각을 파일 이름 앞으로 붙여줌.
	private String saveUploadFile(MultipartFile upload_file) {

		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();

		try {
			// transferTo() 메서드는 업로드한 파일 데이터를 지정한 파일에 저장한다
			// path_upload(properites) + / + file_name(현재시각+파일이름)을 upload_file에 저장한다.
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file_name;
	}

	// 위 메서드 호출
	public void addContentInfo(ContentBean writeContentBean) {

		MultipartFile upload_file = writeContentBean.getUpload_file();

		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
	
		boardDao.addContentInfo(writeContentBean);
	}
}
