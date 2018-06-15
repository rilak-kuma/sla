package kr.maxerve.admin.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.maxerve.admin.login.service.LoginService;
import kr.maxerve.admin.utils.ResponseUtil;

/**
* LoginController.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/member")
public class LoginController {
	@Resource(name="loginService")
	private LoginService loginService;

	/**
	 * 로그인 화면
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, ModelMap model) throws Exception {
		if (request.isUserInRole("ROLE_USER")) {
			return "redirect:/main/main.do";
		} else {
			return "empty/account/login";
		}
	}

	/**
	 * 로그인 실패
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/loginFailure")
	public void loginFailure(HttpServletRequest request, ModelMap model) throws Exception {
		model.addAttribute("resultCode", ResponseUtil.RESULT_CODE_FAIL_LOGIN);
	}

	/**
	 * 로그인 성공
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/loginSuccess")
	public void loginSuccess(ModelMap model) throws Exception {
		model.addAttribute("resultCode", ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect: /member/login.do";
	}
}
