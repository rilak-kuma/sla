package kr.maxerve.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* MenuMainController
* @author LEEC.J
* @since 2018.06.19
* @version 1.0
* @see
*
* <pre>
* 메뉴메인
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.19     LEEC.J        최초 생성
* </pre>
*/
@Controller
public class MenuMainController {
	@RequestMapping(value="/{serivce}/proxy")
	public void selectProxy() throws Exception {

	}
}
