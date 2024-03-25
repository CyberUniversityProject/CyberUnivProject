package com.cyber.university.utils;

/**
 * packageName    : com.cyber.university.utils
 * fileName       : Define
 * author         : 이준혁
 * date           : 3/10/24
 * description    : 상수 정의
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/10/24        이준혁       최초 생성
 */
public class Define {


    public final static String PRINCIPAL = "principal";

    public final static String CREATE_FAIL = "생성에 실패하였습니다.";

    public final static String UPDATE_FAIL = "수정에 실패하였습니다.";

    public final static String NOT_FOUND_ID = "아이디를 찾을 수 없습니다.";

    public final static String WRONG_PASSWORD = "비밀번호가 틀렸습니다.";
    
    public final static String WRONG_CHECK_PASSWORD = "비밀번호가 일치하지 않습니다.";

    public final static String SUBMIT_CHECK_ID = "본인만 신청할 수 있습니다.";
    
    public final static String NOT_FOUND_INFO = "입력되지 않은 정보가 있습니다.";
    
    public final static String GRADUATED_STATUS = "졸업";
    
    public final static String WITHDRAWN_STATUS = "자퇴";
    
    public final static String REGISTRATION_ERROR_MESSAGE = "등록금 납부 대상이 아닙니다.";

    public final static  String ENROLLMENT_ERROR_MESSAGE = "수강 신청 대상이 아닙니다.";
    
    public final static String ALREADY_EVALUATION = "이미 등록하신 강의평가입니다.";
    
    // Integer로 했더니 ==로 비교하려면 .intValue()를 붙여줘야해서 int로 변경함
    public final static int CURRENT_YEAR = 2024;

    public final static int CURRENT_SEMESTER = 1;

    // 이미지 처리 관련
    // 1KB = 1024byte
    // 1MB = 1024*1024 = 1,048,476 byte
    public final static String UPLOAD_DIRECTORY = "C:\\spring_upload\\cyberUniversity\\upload";
    //public final static String UPLOAD_DIRECTORY = "/Users/junhyuk/Documents/upload";

    public final static int MAX_FILE_SIZE = 1024 * 1024 * 20;

    /**
     * 로그인 해야 접속 가능한 페이지 목록
     *
     * @author 이준혁
     */
    public final static String[] PATHS = { "/update", "/password", "/info/**", "/guide", "/myInfo/**", "/community", "/community/**", "/subject/**", 
    		"/student/**", "/sugang/**", "/professor/**", "/user/**", "/tuition/**", "/applySubject/**", "/break/**", "/college/**", "/department/**", "/room/**", "/staff/**"};
    public final static String[] PROFESSOR_PATHS = { "/professor/**" };
    public final static String[] STUDENT_PATHS = {"/grade/**"};

    public final static String[] STAFF_PATHS = { "/user/**", "/tuition/**","/applySubject/**", "/break/**", "/college/**", "/department/**", "/room/**", "/staff/**"};
    


    // 수강 가능한 최대 학점
    public final static int MAX_GRADES = 18;
}
