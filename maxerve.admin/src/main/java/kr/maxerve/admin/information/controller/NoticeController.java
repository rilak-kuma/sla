package kr.maxerve.admin.information.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.information.service.NoticeService;
import kr.maxerve.admin.information.vo.NoticeIVO;
import kr.maxerve.admin.information.vo.NoticeMVO;
import kr.maxerve.admin.information.vo.NoticeUVO;
import kr.maxerve.admin.information.vo.ReqNoticeVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.tag.service.TagService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.NoticeDTO;
import kr.maxerve.dto.TagDTO;

/**
* NoticeController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 공지
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/information/notice")
public class NoticeController {

	@Resource(name="noticeService")
	NoticeService noticeService;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="tagService")
	private TagService tagService;

	@Resource(name="fileService")
	private FileService fileService;

	/**
	 * 센터공지 목록화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/noticeList","/noticeListExcel"})
	public String selectNoticelist(ModelMap model, ReqNoticeVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		Date date = new Date();
		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			vo.setCreDttmOne("");
			vo.setCreDttmTwo(new SimpleDateFormat("yyyy.MM.dd").format(date));
		}

		List<NoticeMVO> ntcList = noticeService.selectNoticelist(vo);

		model.addAttribute("searchVO",vo);
		model.addAttribute("ntcList",ntcList);
		model.addAttribute("paginationInfo",vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=noticeList_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 센터공지 등록
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @param attachTagDTO
	 * @param atcFileDTO
	 * @throws Exception
	 */
	@RequestMapping(value="/insertNotice", method=RequestMethod.POST)
	public void insertNotice(ModelMap model, @Valid NoticeIVO vo, BindingResult bindingResult, AttachTagDTO attachTagDTO, AttachFileDTO atcFileDTO) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			if(StringUtils.isEmpty(vo.getPcYn())){
				vo.setPcYn("N");
			}

			if(StringUtils.isEmpty(vo.getMblYn())){
				vo.setMblYn("N");
			}

			if(StringUtils.isEmpty(vo.getTopYn())){
				vo.setTopYn("N");
			}

			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setModMngrMbrIdx(memberInfo.getMngrMbrIdx());
			noticeService.insertNotice(vo, attachTagDTO, atcFileDTO);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 센터공지 상세화면
	 * @param model
	 * @param ntcIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/noticeInfo")
	public void selectNoticeInfo(ModelMap model, String ntcIdx) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		NoticeMVO noticeVO = new NoticeMVO();
		ManagerMemberDTO memberDTO = new ManagerMemberDTO();
		ManagerMemberDTO creMbInfo = new ManagerMemberDTO();
		ManagerMemberDTO modMbInfo = new ManagerMemberDTO();
		List<TagDTO> tagList = null;
		List<FileMVO> fileList = null;

		if(StringUtils.isNotEmpty(ntcIdx)){
			//공지사항 상세
			noticeVO = noticeService.selectNoticeInfo(ntcIdx);

			//등록자정보
			if(!"0".equals(noticeVO.getMngrMbrIdx()) && StringUtils.isNotEmpty(noticeVO.getMngrMbrIdx()) ) {
				memberDTO.setMngrMbrIdx(noticeVO.getMngrMbrIdx());
				creMbInfo = accountService.selectMemberInfo(memberDTO);
			}

			//수정자정보
			if(!"0".equals(noticeVO.getModMngrMbrIdx()) && StringUtils.isNotEmpty(noticeVO.getModMngrMbrIdx()) ) {
				memberDTO.setMngrMbrIdx(noticeVO.getModMngrMbrIdx());
				modMbInfo = accountService.selectMemberInfo(memberDTO);
			}

			//테그 리스트
			AttachTagDTO attachTagDTO = new AttachTagDTO();
			attachTagDTO.setTagLocCd(CommonCodeUtil._010._012.getValue());
			attachTagDTO.setTagLocIdx(noticeVO.getNtcIdx());
			tagList = tagService.selectAttachTagList(attachTagDTO);

			//첨부파일 리스트
			AttachFileDTO fileVO = new AttachFileDTO();
			fileVO.setAtcLocCd(CommonCodeUtil._010._012.getValue());
			fileVO.setAtcLocIdx(noticeVO.getNtcIdx());
			fileList = fileService.selectAttachList(fileVO);
		}

		model.addAttribute("creMngrMbrNm", creMbInfo.getMngrMbrNm());
		model.addAttribute("creMngrMbrDpt", creMbInfo.getMngrMbrDpt());
		model.addAttribute("modMngrMbrNm", modMbInfo.getMngrMbrNm());
		model.addAttribute("modMngrMbrDpt", modMbInfo.getMngrMbrDpt());

		model.addAttribute("fileList",fileList);
		model.addAttribute("tagList",tagList);
		model.addAttribute("noticeVO",noticeVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 센터공지 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @param attachTagDTO
	 * @param atcFileDTO
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNotice", method=RequestMethod.POST)
	public void updateNotice(ModelMap model, NoticeUVO vo , BindingResult bindingResult, AttachTagDTO attachTagDTO, AttachFileDTO atcFileDTO) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			if(StringUtils.isEmpty(vo.getPcYn())){
				vo.setPcYn("N");
			}

			if(StringUtils.isEmpty(vo.getMblYn())){
				vo.setMblYn("N");
			}

			if(StringUtils.isEmpty(vo.getTopYn())){
				vo.setTopYn("N");
			}

			vo.setModMngrMbrIdx(memberInfo.getMngrMbrIdx());
			noticeService.updateNotice(vo, attachTagDTO, atcFileDTO);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 센터공지 삭제
	 * @param model
	 * @param ntcIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteNotice", method=RequestMethod.POST)
	public void deleteNotice(ModelMap model, @RequestParam String ntcIdx) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			NoticeDTO vo = new NoticeDTO();
			vo.setNtcIdx(ntcIdx);
			vo.setModMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setDelYn("Y");
			noticeService.deleteNotice(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
