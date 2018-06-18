package kr.maxerve.admin.basic.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.account.vo.ReqManagerMemberIVO;
import kr.maxerve.admin.account.vo.ReqManagerMemberUVO;
import kr.maxerve.admin.account.vo.ReqManagerMemberVO;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.cmmn.vo.MultiCommonCodeVO;
import kr.maxerve.admin.login.service.LoginService;
import kr.maxerve.admin.login.vo.LoginVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.admin.utils.StringUtil;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ManagerMenuDTO;

/**
* AccountController
* @author LEEC.J
* @since 2018.06.19
* @version 1.0
* @see
*
* <pre>
* 관리자계정
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.19     LEEC.J        최초 생성
* 2017.01.10     정재훈        회원삭제 추가
* </pre>
*/
@Controller
@RequestMapping(value="/basic/account")
public class AccountController {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="loginService")
	private LoginService loginService;

	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	/**
	 * 관리자 계정 목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public void selectList(ReqManagerMemberVO vo, ModelMap model) throws Exception {
		List<ManagerMemberDTO> memberList = accountService.selectMemberList(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
		model.addAttribute("memberList", memberList);
	}

	/**
	 * 관리자 계정등록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert")
	public void insert(ReqManagerMemberVO vo, ModelMap model) throws Exception {
		CommonCodeDTO param = new CommonCodeDTO();
		param.setDep1(CommonCodeUtil._023.ROOT.getValue());

		List<MultiCommonCodeVO> menuList = commonCodeService.selectList2(param);

		ManagerMemberDTO memberInfo = null;
		// 회원정보가 있을시 회원정보 취득
		if (!StringUtils.isEmpty(vo.getMngrMbrIdx())) {
			memberInfo = accountService.selectMemberInfo(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
		model.addAttribute("menuList", menuList);
		model.addAttribute("memberInfo", memberInfo);
	}

	/**
	 * 관리자 계정 저장
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insertPost(@Valid @RequestBody ReqManagerMemberIVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			String mngrMbrId = vo.getMngrMbrId();

			// 아이디 중복체크
			boolean isExist = accountService.selectIsExistId(mngrMbrId);
			if (isExist) {
				resultCode = ResponseUtil.RESULT_CODE_ID_EXIST;
			} else {
				// 회원가입
				accountService.insertMember(vo);
			}
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 관리자 계정 수정
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void updatePost(@Valid @RequestBody ReqManagerMemberUVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			// 회원수정
			accountService.updateMember(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 아이디 중복체크
	 * @param mngrMbrId
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/check_id", method=RequestMethod.POST)
	public void checkId(@RequestParam(value="mngrMbrId") String mngrMbrId, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		boolean isExist = accountService.selectIsExistId(mngrMbrId);

		if (isExist) {
			resultCode = ResponseUtil.RESULT_CODE_ID_EXIST;
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 관리자 메뉴권한 목록
	 * @param mngrMbrIdx
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/menu_auth_list", method=RequestMethod.POST)
	public void selectMenuAuthList(@RequestParam(value="mngrMbrIdx") String mngrMbrIdx, ModelMap model) throws Exception {
		List<ManagerMenuDTO> menuAuthList = accountService.selectMenuAuthList(mngrMbrIdx);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
		model.addAttribute("menuAuthList", menuAuthList);
	}

	/**
	 * 마이페이지 화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/mypage")
	public void selectMypage(ModelMap model) throws Exception {
		ManagerMemberMVO info = accountService.selectMemberInfo();

		model.addAttribute("info", info);
	}

	/**
	 * 내정보 수정
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateMyinfo", method=RequestMethod.POST)
	public void updateMyinfo(@Valid ReqManagerMemberVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		ManagerMemberMVO loginInfo = null;


		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if (resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)) {
			ManagerMemberMVO info = accountService.selectMemberInfo();

			// 비밀번호 확인
			LoginVO loginVO = new LoginVO();
			loginVO.setUid(info.getMngrMbrId());
			loginVO.setPwd(StringUtil.getSha256Enc(vo.getMngrMbrPwd()));

			loginInfo = loginService.selectLoginInfo(loginVO);
			if (loginInfo == null) {
				resultCode = ResponseUtil.RESULT_CODE_INVALID_PASSWORD;
			} else {
				vo.setMngrMbrIdx(loginInfo.getMngrMbrIdx());
				accountService.updateMyinfo(vo);
			}
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 관리자계정 삭제
	 * @param mngrMbrIdx
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void deletePost(@RequestParam(value="mngrMbrIdx") String mngrMbrIdx, ModelMap model) throws Exception {
		accountService.deleteMember(mngrMbrIdx);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}
}
