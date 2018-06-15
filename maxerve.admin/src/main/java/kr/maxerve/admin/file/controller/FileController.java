package kr.maxerve.admin.file.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.maxerve.admin.cmmn.vo.ReqFileVO;
import kr.maxerve.admin.editor.vo.EditorUploadVO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.FileDTO;

/**
* FileController
* @author 이창재
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
* 2018.06.15     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/file")
public class FileController {

	@Resource(name="fileService")
	private FileService fileService;

	@Resource(name="propertiesService")
	private Properties properties;


	/**
	 * 에디터 이미지 첨부
	 * @param editorUploadVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editor_upload")
	public String editorUpload(EditorUploadVO editorUploadVO, ModelMap model) throws Exception {
		FileDTO result = fileService.editorUpload(editorUploadVO);

		model.addAttribute(result);
		model.addAttribute("CKEditorFuncNum", editorUploadVO.getCKEditorFuncNum());

		return "empty/cmmn/editor_upload";
	}

	/**
	 * 첨부파일 업로드
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadFile")
	public void uploadFile(ReqFileVO vo, ModelMap model) throws Exception {
		List<FileDTO> uploadList = fileService.uploadFile(vo);
		model.addAttribute("files",uploadList);
	}

	@Deprecated
	@RequestMapping(value="/saveFile")
	public void saveFile(@RequestBody ReqFileVO vo, ModelMap model) throws Exception {

		if (StringUtils.isEmpty(vo.getFilePath())) {
			throw new FileNotFoundException();
		}
		String updateFilePath = fileService.moveFile(vo.getFilePath());
		vo.setFilePath(updateFilePath);
		if(vo.getTmnPath().isEmpty()) {
			String uploadThumbPrefix = properties.getProperty("upload.thumb.prefix");
			vo.setTmnPath(uploadThumbPrefix + updateFilePath);
		}
		model.addAttribute("file",vo);
		model.addAttribute("resultCode", ResponseUtil.RESULT_CODE_SUCESS);
	}

	@Deprecated
	@RequestMapping(value="/removeFile")
	public void removeFile(@RequestBody FileDTO vo, ModelMap model) throws Exception {

		if (StringUtils.isEmpty(vo.getFilePath())) {
			throw new FileNotFoundException();
		}
		List<FileDTO> uploadList = fileService.removeFile(vo);
		model.addAttribute("files",uploadList);
		model.addAttribute("resultCode", ResponseUtil.RESULT_CODE_SUCESS);
	}

	@Deprecated
	@RequestMapping(value="/downloadFile")
	public void downloadFile(FileDTO vo, HttpServletResponse response, @RequestHeader String referer) throws Exception {

		 if(referer != null && !referer.isEmpty()) {
			 //do nothing
	         //or send error
		 }

		 //If user is not authorized - he should be thrown out from here itself
		 FileDTO selectFileVO = fileService.selectFile(vo);

		 if(selectFileVO != null) {
			 String filePath = selectFileVO.getFilePath();
			 String OrigFileNm = selectFileVO.getOrigFileNm();
			 String cnttTyp = selectFileVO.getCnttTyp();
			 String uploadPathRoot = properties.getProperty("upload.path.root");

			 Path file = Paths.get(uploadPathRoot + filePath);
			 if (Files.exists(file)){
				 response.setContentType(cnttTyp);
				 response.addHeader("Content-Disposition", "attachment; filename="+OrigFileNm);
				 try{
					 Files.copy(file, response.getOutputStream());
					 response.getOutputStream().flush();
				 }catch (IOException ex) {
					 ex.printStackTrace();
				 }
			 }
		 }
	}

	@Deprecated
	@RequestMapping(value="/imageView")
	public void imageView(FileDTO vo, HttpServletResponse response, @RequestHeader String referer) throws Exception {

		if(referer != null && !referer.isEmpty()) {
			//do nothing
			//or send error
		}
		//If user is not authorized - he should be thrown out from here itself

		FileDTO selectFileVO = fileService.selectFile(vo);
		String directoryPath = properties.getProperty("editor.upload.imagepath");

		if(selectFileVO != null) {
			String filePath = selectFileVO.getFilePath();
			String OrigFileNm = selectFileVO.getOrigFileNm();
			String cnttTyp = selectFileVO.getCnttTyp();

			Path file = Paths.get(directoryPath + filePath);
			if (Files.exists(file)){
				response.setContentType(cnttTyp);
				response.addHeader("Content-Disposition", "attachment; filename="+OrigFileNm);
				try{
					Files.copy(file, response.getOutputStream());
					response.getOutputStream().flush();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
