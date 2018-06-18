package kr.maxerve.admin.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/basic/statistics")
public class StatisticsController {
	/**
	 * PV목록
	 * @throws Exception
	 */
	@RequestMapping(value="/pv_list")
	public void selectPvList() throws Exception {

	}
}
