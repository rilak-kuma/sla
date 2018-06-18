package kr.maxerve.admin.facilities.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.facilities.service.FacilitiesService;
import kr.maxerve.admin.facilities.vo.FloorRoomMVO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.member.service.MemberService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.FacilitiesFloorDTO;
import kr.maxerve.dto.FacilitiesInfoDTO;
import kr.maxerve.dto.FacilitiesMasterDTO;
import kr.maxerve.dto.FileDTO;

/**
* FacilitiesController
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 시설관리
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/facilities/future")
public class FacilitiesController {

	@Resource(name="facilitiesService")
	FacilitiesService facilitiesService;

	@Resource(name="fileService")
	FileService fileService;

	@Resource(name="commonCodeService")
	CommonCodeService commonCodeService;

	@Resource(name="memberService")
	MemberService memberService;

	/**
	 * 미래청 전체 호실 사용목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/floor")
	public void selectFloorList(ModelMap model, FacilitiesInfoDTO vo) throws Exception {

		List<FacilitiesInfoDTO> floorList = facilitiesService.selectFloorList(vo);

		model.addAttribute("floorList",floorList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 미래청 층별호실 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/floorRoomList")
	public void selectFloorRoomList(ModelMap model, FacilitiesInfoDTO vo) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		if(StringUtils.isEmpty(vo.getFlr())){
			vo.setFlr("2");
		}

		//층별 호실 정보 목록
		List<FacilitiesInfoDTO> floorRoomList = facilitiesService.selectFloorList(vo);

		//층별 안내정보
		FacilitiesFloorDTO floorInfo = facilitiesService.selectFloorInfo(vo);
		//첨부파일(안내이미지)
		FileDTO fileInfo = new FileDTO();
		if(floorInfo != null){
			if(StringUtils.isNotEmpty(floorInfo.getFilePath())){
				FileDTO param = new FileDTO();
				param.setFilePath(floorInfo.getFilePath());

				fileInfo =  fileService.selectFile(param);
			}
		}

		//시설구분 : 미래청(회의/세미나) 목록
		FacilitiesMasterDTO mvo = new FacilitiesMasterDTO();
		mvo.setRsvtPlcCd(CommonCodeUtil._002._001.getValue());
		mvo.setUseYn("Y");
		List<FacilitiesMasterDTO> seminaList = facilitiesService.selectFacilitiesList(mvo);

		//시설구분
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._031.ROOT.getValue());
		List<CommonCodeDTO> fctTypes = commonCodeService.selectList(voA);

		model.addAttribute("floorRoomList",floorRoomList);
		model.addAttribute("searchVO",vo);
		model.addAttribute("floorInfo",floorInfo);
		model.addAttribute("fileInfo",fileInfo);
		model.addAttribute("fctTypes",fctTypes);
		model.addAttribute("seminaList",seminaList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 미래청 층별호실 정보 저장
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/updateFloorRoom", method=RequestMethod.POST)
	public void updateFloorRoomList(ModelMap model, @RequestBody FloorRoomMVO vo) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		facilitiesService.updateFloorRoomList(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 미래청 조감도 화면
	 * @param flr
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/floorAirView")
	public String selectFloorAirView(@RequestParam String flr, ModelMap model) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		FacilitiesFloorDTO floorInfo;	// 시설층정보
		List<FacilitiesInfoDTO> floorInfoList;	// 시설층별정보

		FacilitiesInfoDTO param = new FacilitiesInfoDTO();
		param.setFlr(flr);

		floorInfo = facilitiesService.selectFloorInfo(param);
		floorInfoList = facilitiesService.selectFloorList(param);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("floorInfo", floorInfo);
		model.addAttribute("floorInfoList", floorInfoList);

		return "popup/facilities/future/floorAirView";
	}
}
