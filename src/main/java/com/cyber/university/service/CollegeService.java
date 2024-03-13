import com.cyber.university.repository.interfaces.CollegeRepository;
import com.cyber.university.repository.interfaces.DepartmentRepository;
import com.cyber.university.repository.model.College;
import com.cyber.university.repository.model.Department;

@Service
public class CollegeService {
	
	
	@Autowired
	private CollegeRepository collegeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * @param college_id
	 * @return id로 해당 단과대 정보 가져옴
	 */
	public College readCollById(Integer id) {

		College collEntity = collegeRepository.selectCollegeDtoById(id);
		return collEntity;
	}

	/**
	 * @param dept_id
	 * @return id로 해당 학과 정보 가져옴
	 */
	public Department readDeptById(Integer id) {

		Department deptEntity = departmentRepository.selectById(id);
		return deptEntity;
	}

	/**
	 * @return 전체 학과 정보 조회
	 */
	public List<Department> readDeptAll() {

		List<Department> deptEntityList = departmentRepository.selectByDepartmentDto();
		return deptEntityList;
	}

  	
	@Transactional
	public String insert(College college) {
		System.out.println(college.toString());
		collegeRepository.insertById(college);
		return college.getName();
	}
	// 단과대학 목록 전체 불러오기
	public List<College> collegeList() {
		System.out.println("collegeList" + toString());
		return collegeRepository.findAll();
	}
/**
  * @FileName : CollegeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 김수현
  * @변경이력 :
  * @프로그램 설명 : 단과대학 삭제
  */
	
	// 단과대학 조회 수정 시에 쓰임 수정 누르면 기존 내용을 가지로 페이지 이동
	public College findById(Integer id) {
		log.info("id");
		College college = collegeRepository.findById(id);
		log.info("where");
		return college;
	}
	//단과대학 수정
	@Transactional
	public void updateById(College college) {
		log.info("update");
		System.out.println("update" + college);
		log.info("complete");
		int result = collegeRepository.updateById(college);
	}
		
	}
}

