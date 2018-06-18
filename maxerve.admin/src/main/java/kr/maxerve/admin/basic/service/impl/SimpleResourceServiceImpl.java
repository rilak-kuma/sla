package kr.maxerve.admin.basic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.basic.service.SimpleResourceService;
import kr.maxerve.admin.basic.vo.ReqSimpleResourceIVO;
import kr.maxerve.admin.simpleResource.dao.SimpleResourceDAO;
import kr.maxerve.dto.SimpleResourceDTO;

/**
* SimpleResourceServiceImpl
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 단순리소스
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
@Service("simpleResourceService")
public class SimpleResourceServiceImpl implements SimpleResourceService {

	@Resource(name="simpleResourceDAO")
	private SimpleResourceDAO simpleResourceDAO;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.SimpleResourceService#selectList(kr.maxerve.dto.SimpleResourceDTO)
	 */
	@Override
	public List<SimpleResourceDTO> selectList(SimpleResourceDTO vo) throws Exception {
		return simpleResourceDAO.selectList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.SimpleResourceService#insert(kr.maxerve.admin.basic.vo.ReqSimpleResourceIVO)
	 */
	@Override
	public void insert(ReqSimpleResourceIVO vo) throws Exception {
		String siplRscTypCd = vo.getList().get(0).getSiplRscTypCd();

		SimpleResourceDTO param = new SimpleResourceDTO();
		param.setSiplRscTypCd(siplRscTypCd);

		simpleResourceDAO.delete(param);

		for (SimpleResourceDTO param1 : vo.getList()) {
			simpleResourceDAO.insert(param1);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.SimpleResourceService#selectInfo(kr.maxerve.dto.SimpleResourceDTO)
	 */
	@Override
	public String selectInfo(SimpleResourceDTO vo) throws Exception {
		return simpleResourceDAO.selectInfo(vo);
	}

}
