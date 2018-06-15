package kr.maxerve.admin.cmmn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.cmmn.vo.MultiCommonCodeVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.CommonCodeDTO;
@Repository("commonCodeDAO")
public class CommonCodeDAO extends BaseDAOSupport {
	/**
	 * 공통코드 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CommonCodeDTO> selectList(CommonCodeDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.cmmn.commonCodeDAO.selectList", vo);
	}


	/**
	 * 2depth용 공통코드 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MultiCommonCodeVO> selectList2(CommonCodeDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.cmmn.commonCodeDAO.selectList2", vo);
	}
}
