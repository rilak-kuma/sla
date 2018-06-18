package kr.maxerve.admin.reserve.controller;

import java.util.ArrayList;
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

import kr.maxerve.admin.facilities.service.FacilitiesService;
import kr.maxerve.admin.reserve.vo.ReqRentAreaIVO;
import kr.maxerve.admin.reserve.vo.ReqRentAreaVO;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.FacilitiesMasterDTO;
import kr.maxerve.dto.RentTheAreaDTO;

/**
* RentAreaController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 대관
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/reserve/rent_area")
public class RentAreaController {
	@Resource(name="facilitiesService")
	private FacilitiesService facilitiesService;

	/**
	 * 대관목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public void list(ReqRentAreaVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<RentTheAreaDTO> list = facilitiesService.selectRentAreaList(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);
	}

	/**
	 * 대관 등록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public void insert(ReqRentAreaVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		RentTheAreaDTO info = new RentTheAreaDTO();
		List<FacilitiesMasterDTO> facilitiesList = new ArrayList<>(); // 대관가능시설목록
		List<FacilitiesMasterDTO> rentAreaFacilitiesList = new ArrayList<>(); // 대관장소목록

		if (!StringUtils.isEmpty(vo.getRtaIdx())) {
			info = facilitiesService.selectRentAreaInfo(vo.getRtaIdx());

			rentAreaFacilitiesList = facilitiesService.selectRentAreaFacilitiesList(vo.getRtaIdx());
		}

		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setUseYn("Y");
		param.setRtaYn("Y");

		facilitiesList = facilitiesService.selectFacilitiesList(param);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(info);
		model.addAttribute(facilitiesList);
		model.addAttribute("rentAreaFacilitiesList", rentAreaFacilitiesList);
	}

	/**
	 * 대관 등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insertProc(@Valid @RequestBody ReqRentAreaIVO vo, BindingResult bindingResult, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		facilitiesService.insertRentArea(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
