package kr.maxerve.admin.basic.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.maxerve.admin.basic.service.SimpleResourceService;
import kr.maxerve.admin.basic.vo.ReqSimpleResourceIVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.SimpleResourceDTO;

/**
* SmsController
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* SMS메시지 관리
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/basic/sms")
public class SmsController {
	@Resource(name="simpleResourceService")
	private SimpleResourceService simpleResourceServer;

	@Resource(name="simpleResourceService")
	private SimpleResourceService simpleResourceService;

	/**
	 * SMS메시지 관리 화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public void selectList(ModelMap model) throws Exception {
		SimpleResourceDTO param = new SimpleResourceDTO();
		param.setSiplRscTypCd(CommonCodeUtil._024._002.getValue());

		List<SimpleResourceDTO> list002 = simpleResourceServer.selectList(param);

		param.setSiplRscTypCd(CommonCodeUtil._024._003.getValue());

		List<SimpleResourceDTO> list003 = simpleResourceServer.selectList(param);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
		model.addAttribute("list002", list002);
		model.addAttribute("list003", list003);
	}

	/**
	 * SMS메시지 등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody @Valid ReqSimpleResourceIVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			simpleResourceService.insert(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
