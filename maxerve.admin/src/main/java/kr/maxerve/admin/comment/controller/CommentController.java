package kr.maxerve.admin.comment.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.comment.service.CommentService;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CommentDTO;

/**
* CommentController
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 댓글
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/comment")
public class CommentController {

	@Resource(name="commentService")
	CommentService commentService;

	/**
	 * 댓글 삭제
	 * @param model
	 * @param cmmtIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteComment", method=RequestMethod.POST)
	public void deleteComment(ModelMap model, @RequestParam String cmmtIdx) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if(StringUtils.isEmpty(cmmtIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			CommentDTO vo = new CommentDTO();
			vo.setCmmtIdx(cmmtIdx);
			vo.setDelYn("Y");
			commentService.deleteComment(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
