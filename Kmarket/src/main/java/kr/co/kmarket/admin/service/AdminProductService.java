package kr.co.kmarket.admin.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kmarket.admin.dao.AdminProductDao;
import kr.co.kmarket.vo.ProductCate1Vo;
import kr.co.kmarket.vo.ProductCate2Vo;
import kr.co.kmarket.vo.ProductVo;

@Service
public class AdminProductService {

	//private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminProductDao dao;
	
	public void insertProduct(ProductVo vo) {
			dao.insertProduct(vo);
	}
	public void selectProduct(){}
	public void selectProducts(){}
	public void updateProduct(){}
	public void deleteProduct(){}
	
	public List<ProductCate1Vo> selectCate1(){
		
			return dao.selectCate1();
	}
	public List<ProductCate2Vo> selectCate2(int cate1){
		
			return dao.selectCate2(cate1);
	}
	
	
/////////////////////비즈니스 처리 로직 구현 메서드////////////////////////
	
	//파일 업로드 처리
	public ProductVo fileUpload(ProductVo vo) {
	//파일 객체 생성 3줄은 자동화시켜라
	File file = new File("src/main/resources/static/thumb/"); //이때 thumb 폴더 만들어주기
	String path = file.getAbsolutePath(); //경로위치탐색함수 
	
	//총 썸네일 파일 4개니까
	MultipartFile[] files = {vo.getThumbFile1(),
							vo.getThumbFile2(),
							vo.getThumbFile3(),
							vo.getDetailFile4()};
	
	int i = 0;
	
	//썸네일 파일 1개만 추가할수도있고 4개 다할수도 있으니
	for(MultipartFile mf : files) {
		
		if(!mf.isEmpty()) { //썸네일파일을 첨부했으면
			//중복 되지 않게 파일 이름 구하기 (oriName)
			String name = mf.getOriginalFilename();
			//이름에서 확장자 따로 분리하기
			String ext = name.substring(name.lastIndexOf("."));
			//이번엔 날짜와 시간이 아닌 유니버셜 네임(16자리정도)을 만들자 (newName)
			String uName = UUID.randomUUID().toString()+ext;
			String fullPath = path+"/"+vo.getCate1()+"/"+vo.getCate2()+"/";
			
			try {
				
				//logger.info("fileUpload try...");
				
				
				
				//디렉터리 생성
				Path root = Paths.get(fullPath);
				Files.createDirectories(root);

				//logger.info("fileUpload try2...");

				
				//첨부파일 저장
				mf.transferTo(new File(fullPath+uName));
				
				//logger.info("fileUpload try3...");
				
				//유니버셜이름으로 바뀐게 저장되어야하므로
				if(i==0) vo.setThumb1(uName);
				if(i==1) vo.setThumb2(uName);
				if(i==2) vo.setThumb3(uName);
				if(i==3) vo.setDetail(uName);
							
				} catch (Exception e) {
					
				e.printStackTrace();
				
				
				//logger.error("fileUpload error..." + e.getMessage());
				
				
				}		
			}//if end	
			i++;	
		}//for end
		return vo;
	}//fileUpload end
	
	
}
