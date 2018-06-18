package kr.maxerve.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.maxerve.admin.utils.ResponseUtil;

@Controller
@RequestMapping(value="/main")
public class MainController {
//	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value="/main")
	public void main(ModelMap model) throws Exception {
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	@RequestMapping(value="/static/{jsp}")
	public String staticjsp(@PathVariable("jsp") String jsp) throws Exception {
		return "main/" + jsp;
	}
}
