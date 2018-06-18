package kr.maxerve.admin.adjust.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.adjust.service.SponsorService;
import kr.maxerve.admin.adjust.vo.ReqSponsorAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqSponsorVO;
import kr.maxerve.admin.adjust.vo.SponsorMVO;
import kr.maxerve.admin.utils.ResponseUtil;

/**
* SponsorController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 후원
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/adjust/sponsor")
public class SponsorController {

	@Resource(name="sponsorService")
	SponsorService sponsorService;

	@Value("#{propertiesService['pagination.countPerPage']}")
	private String paginationCountPerPage;

	@Value("#{propertiesService['pagination.pagePerBlock']}")
	private String paginationPagePerBlock;

	/**
	 * 후원현황화면
	 * @param vo
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value={"/list", "/list_excel"})
	public String selectList(ReqSponsorVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		List<SponsorMVO> list = new ArrayList<>();		// 후원현황목록

		if(jsp.indexOf("_excel") > 0){
			list = sponsorService.selectList(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=sponsor_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			PaginationInfo pageInfo;	// 페이징

			if (vo.getCountPerPage() < 1) {
				vo.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(vo.getPage()));
			pageInfo.setRecordCountPerPage(vo.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(sponsorService.selectCount(vo));

			if (pageInfo.getTotalRecordCount() > 0) {
				vo.setLimitIndex(pageInfo.getFirstRecordIndex());

				list = sponsorService.selectList(vo);
			}

			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);

		return jsp;
	}

	/**
	 * 후원금 정산화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/adjust_list", "/adjust_list_excel"})
	public String selectAdjustList(ReqSponsorAdjustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		List<SponsorMVO> list = new ArrayList<>();		// 후원금정산목록

		if(jsp.indexOf("_excel") > 0){
			// 후원금정산목록
			list = sponsorService.selectAdjustList(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=sponsor_adjust_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			PaginationInfo pageInfo;	// 페이징

			if (vo.getCountPerPage() < 1) {
				vo.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(vo.getPage()));
			pageInfo.setRecordCountPerPage(vo.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(sponsorService.selectAdjustCount(vo));

			if (pageInfo.getTotalRecordCount() > 0) {
				vo.setLimitIndex(pageInfo.getFirstRecordIndex());

				// 후원금정산목록
				list = sponsorService.selectAdjustList(vo);
			}

			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);

		return jsp;
	}

	/**
	 * 후원금 정산 상세 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/adjust_detail", "/adjust_detail_excel"})
	public String selectAdjustDetail(ReqSponsorAdjustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		List<SponsorMVO> list = new ArrayList<>();		// 후원 상세내역

		ReqSponsorVO param = new ReqSponsorVO();
		param.setPage(vo.getDetailPage());
		param.setYm(vo.getDt());
		param.setPjtIdx(vo.getPjtIdx());

		if(jsp.indexOf("_excel") > 0){
			// 후원 상세내역
			list = sponsorService.selectList(param);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=sponsor_adjust_detail_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			SponsorMVO summary;		// 정산요약
			PaginationInfo pageInfo;	// 페이징

			// 정산요약
			summary = sponsorService.selectAdjustList(vo).get(0);

			if (param.getCountPerPage() < 1) {
				param.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(param.getPage()));
			pageInfo.setRecordCountPerPage(param.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(sponsorService.selectCount(param));

			if (pageInfo.getTotalRecordCount() > 0) {
				param.setLimitIndex(pageInfo.getFirstRecordIndex());

				// 후원 상세내역
				list = sponsorService.selectList(param);
			}

			model.addAttribute(summary);
			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);

		return jsp;
	}
}
