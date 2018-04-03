package co.kr.jcone.server.bean;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class DocumentBean{
	
	// TABLE : document
	
	private String DOCUMENT_ID;						// 문서아이디
	private String REGISTER_TYPE;					// 등록유형
	private String LIBRARY_POSITION;				// 서고위치
	private String REGISTER_DATE;					// 등록일
	private String DOCUMENT_DESCRIPTION;			// 문서요약		
	private String SECURITY_GRADE;					// 보안등급
	private String BIND_ID;							// 철아이디
	private String BIND_TITLE;						// 철제목
	private String DOCUMENT_TITLE;					// 문서제목
	private String ETC1;							// 기타1
	private String ETC2;							// 기타2
	private String ETC3;							// 기타3
	private String ETC4;							// 기타4
	private String ETC5;							// 기타5
	private String VERSION;							// 버전
	private String USER_ID;							// 사용자아이디
	private String USER_NAME;						// 사용자이름
	private String GROUP_ID;						// 그룹아이디
	private String GROUP_NAME;						// 그룹이름
	private String BATCH_ITEM_ID;					// 일괄등록용 철아이디
	private String BATCH_BIND_ID;					// 일괄등록용 건아이디
	private String DELETE_TYPE;						// 삭제유형
	private Date ROW_INPUT_DATE;					// 행 입력 일지
	private MultipartFile[] multiPartFiles;			// 업로드 요청 파일들
	
	// TABLE : document_file
	
	private String DOCUMENT_FILE_ID;
	private String ORIGINAL_FILE_NAME;
	private int FILE_SIZE;
	private int FILE_ORDER;
	
	// TABLE : document_version
	
	private String MODIFY_REASON;
	private String ORIGINAL_DOCUMENT_ID;
	
	// TABLE : document_folder
	
	private String FOLDER_ID;
	
	// TABLE : folder
	
	private String FOLDER_TYPE;
	private String FOLDER_NAME;
	private String FOLDER_DESCRIPTION;
	private String FOLDER_LEVEL;
	private String PARENT_FOLDER_ID;
	private String FOLDER_PATH;
	
	// TEST
	
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDOCUMENT_ID() {
		return DOCUMENT_ID;
	}
	public void setDOCUMENT_ID(String dOCUMENT_ID) {
		DOCUMENT_ID = dOCUMENT_ID;
	}
	public String getREGISTER_TYPE() {
		return REGISTER_TYPE;
	}
	public void setREGISTER_TYPE(String rEGISTER_TYPE) {
		REGISTER_TYPE = rEGISTER_TYPE;
	}
	public String getLIBRARY_POSITION() {
		return LIBRARY_POSITION;
	}
	public void setLIBRARY_POSITION(String lIBRARY_POSITION) {
		LIBRARY_POSITION = lIBRARY_POSITION;
	}
	public String getREGISTER_DATE() {
		return REGISTER_DATE;
	}
	public void setREGISTER_DATE(String rEGISTER_DATE) {
		REGISTER_DATE = rEGISTER_DATE;
	}
	public String getDOCUMENT_DESCRIPTION() {
		return DOCUMENT_DESCRIPTION;
	}
	public void setDOCUMENT_DESCRIPTION(String dOCUMENT_DESCRIPTION) {
		DOCUMENT_DESCRIPTION = dOCUMENT_DESCRIPTION;
	}
	public String getSECURITY_GRADE() {
		return SECURITY_GRADE;
	}
	public void setSECURITY_GRADE(String sECURITY_GRADE) {
		SECURITY_GRADE = sECURITY_GRADE;
	}
	public String getBIND_ID() {
		return BIND_ID;
	}
	public void setBIND_ID(String bIND_ID) {
		BIND_ID = bIND_ID;
	}
	public String getBIND_TITLE() {
		return BIND_TITLE;
	}
	public void setBIND_TITLE(String bIND_TITLE) {
		BIND_TITLE = bIND_TITLE;
	}
	public String getDOCUMENT_TITLE() {
		return DOCUMENT_TITLE;
	}
	public void setDOCUMENT_TITLE(String dOCUMENT_TITLE) {
		DOCUMENT_TITLE = dOCUMENT_TITLE;
	}
	public String getETC1() {
		return ETC1;
	}
	public void setETC1(String eTC1) {
		ETC1 = eTC1;
	}
	public String getETC2() {
		return ETC2;
	}
	public void setETC2(String eTC2) {
		ETC2 = eTC2;
	}
	public String getETC3() {
		return ETC3;
	}
	public void setETC3(String eTC3) {
		ETC3 = eTC3;
	}
	public String getETC4() {
		return ETC4;
	}
	public void setETC4(String eTC4) {
		ETC4 = eTC4;
	}
	public String getETC5() {
		return ETC5;
	}
	public void setETC5(String eTC5) {
		ETC5 = eTC5;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getGROUP_ID() {
		return GROUP_ID;
	}
	public void setGROUP_ID(String gROUP_ID) {
		GROUP_ID = gROUP_ID;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	public String getBATCH_ITEM_ID() {
		return BATCH_ITEM_ID;
	}
	public void setBATCH_ITEM_ID(String bATCH_ITEM_ID) {
		BATCH_ITEM_ID = bATCH_ITEM_ID;
	}
	public String getBATCH_BIND_ID() {
		return BATCH_BIND_ID;
	}
	public void setBATCH_BIND_ID(String bATCH_BIND_ID) {
		BATCH_BIND_ID = bATCH_BIND_ID;
	}
	public String getDELETE_TYPE() {
		return DELETE_TYPE;
	}
	public void setDELETE_TYPE(String dELETE_TYPE) {
		DELETE_TYPE = dELETE_TYPE;
	}
	public Date getROW_INPUT_DATE() {
		return ROW_INPUT_DATE;
	}
	public void setROW_INPUT_DATE(Date rOW_INPUT_DATE) {
		ROW_INPUT_DATE = rOW_INPUT_DATE;
	}
	public MultipartFile[] getMultiPartFiles() {
		return multiPartFiles;
	}
	public void setMultiPartFiles(MultipartFile[] multiPartFiles) {
		this.multiPartFiles = multiPartFiles;
	}
	public String getDOCUMENT_FILE_ID() {
		return DOCUMENT_FILE_ID;
	}
	public void setDOCUMENT_FILE_ID(String dOCUMENT_FILE_ID) {
		DOCUMENT_FILE_ID = dOCUMENT_FILE_ID;
	}
	public String getORIGINAL_FILE_NAME() {
		return ORIGINAL_FILE_NAME;
	}
	public void setORIGINAL_FILE_NAME(String oRIGINAL_FILE_NAME) {
		ORIGINAL_FILE_NAME = oRIGINAL_FILE_NAME;
	}
	public int getFILE_SIZE() {
		return FILE_SIZE;
	}
	public void setFILE_SIZE(int fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}
	public int getFILE_ORDER() {
		return FILE_ORDER;
	}
	public void setFILE_ORDER(int fILE_ORDER) {
		FILE_ORDER = fILE_ORDER;
	}
	public String getMODIFY_REASON() {
		return MODIFY_REASON;
	}
	public void setMODIFY_REASON(String mODIFY_REASON) {
		MODIFY_REASON = mODIFY_REASON;
	}
	public String getORIGINAL_DOCUMENT_ID() {
		return ORIGINAL_DOCUMENT_ID;
	}
	public void setORIGINAL_DOCUMENT_ID(String oRIGINAL_DOCUMENT_ID) {
		ORIGINAL_DOCUMENT_ID = oRIGINAL_DOCUMENT_ID;
	}
	public String getFOLDER_ID() {
		return FOLDER_ID;
	}
	public void setFOLDER_ID(String fOLDER_ID) {
		FOLDER_ID = fOLDER_ID;
	}
	public String getFOLDER_TYPE() {
		return FOLDER_TYPE;
	}
	public void setFOLDER_TYPE(String fOLDER_TYPE) {
		FOLDER_TYPE = fOLDER_TYPE;
	}
	public String getFOLDER_NAME() {
		return FOLDER_NAME;
	}
	public void setFOLDER_NAME(String fOLDER_NAME) {
		FOLDER_NAME = fOLDER_NAME;
	}
	public String getFOLDER_DESCRIPTION() {
		return FOLDER_DESCRIPTION;
	}
	public void setFOLDER_DESCRIPTION(String fOLDER_DESCRIPTION) {
		FOLDER_DESCRIPTION = fOLDER_DESCRIPTION;
	}
	public String getFOLDER_LEVEL() {
		return FOLDER_LEVEL;
	}
	public void setFOLDER_LEVEL(String fOLDER_LEVEL) {
		FOLDER_LEVEL = fOLDER_LEVEL;
	}
	public String getPARENT_FOLDER_ID() {
		return PARENT_FOLDER_ID;
	}
	public void setPARENT_FOLDER_ID(String pARENT_FOLDER_ID) {
		PARENT_FOLDER_ID = pARENT_FOLDER_ID;
	}
	public String getFOLDER_PATH() {
		return FOLDER_PATH;
	}
	public void setFOLDER_PATH(String fOLDER_PATH) {
		FOLDER_PATH = fOLDER_PATH;
	}		
	
}
