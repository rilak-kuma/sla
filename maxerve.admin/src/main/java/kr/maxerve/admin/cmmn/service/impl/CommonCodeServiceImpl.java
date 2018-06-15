package kr.maxerve.admin.cmmn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.cmmn.dao.CommonCodeDAO;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.cmmn.vo.MultiCommonCodeVO;
import kr.maxerve.dto.CommonCodeDTO;

/**
* CommonCodeService
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 공통코드
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.05.15      LEEC.J         최초 생성
* </pre>
*/
@Service("commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService {

	@Resource(name="commonCodeDAO")
	private CommonCodeDAO commonCodeDAO;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.cmmn.service.CommonCodeService#selectList(kr.maxerve.dto.CommonCodeDTO)
	 */
	public List<CommonCodeDTO> selectList(CommonCodeDTO vo) throws Exception {
		return commonCodeDAO.selectList(vo);
	}

	
	public List<CommonCodeDTO> selectList(String dep1) throws Exception {
		CommonCodeDTO param = new CommonCodeDTO();
		param.setDep1(dep1);

		return selectList(param);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.cmmn.service.CommonCodeService#selectList2(kr.maxerve.dto.CommonCodeDTO)
	 */
	public List<MultiCommonCodeVO> selectList2(CommonCodeDTO vo) throws Exception {
		return commonCodeDAO.selectList2(vo);
	}
}
