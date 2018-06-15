package kr.maxerve.admin.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.dto.FileDTO;

public class DownloadView extends AbstractView {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("#{propertiesService['upload.path.root']}")
	private String uploadPathRoot;

	@Resource(name="fileService")
	private FileService fileService;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String filePath = String.valueOf(model.get("filePath"));

		FileDTO param = new FileDTO();
		param.setFilePath(filePath);

		FileDTO fileInfo = fileService.selectFile(param);

		File file = new File(uploadPathRoot + filePath);

		response.setContentType("application/force-download; utf-8");
		response.setContentLength((int)file.length());

//		String userAgent = request.getHeader("User-Agent");

//		boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1;

		String fileName = null;

//		if (ie) {
//			fileName = URLEncoder.encode("a.png", "utf-8");
//
//			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
//			response.setHeader("Content-Transfer-Encoding", "binary");
//		} else {
//			fileName = new String(fileInfo.getOrigFileNm().getBytes("utf-8"));

		if (fileInfo == null) {
			fileName = URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/")+1, filePath.length()), "utf-8");
		} else {
			fileName = URLEncoder.encode(fileInfo.getOrigFileNm(), "utf-8");
		}
//			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
//			response.setHeader("Content-Transfer-Encoding", "binary");
//		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		OutputStream outputStream = response.getOutputStream();

		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(file);

			FileCopyUtils.copy(fileInputStream, outputStream);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}

			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}

		response.flushBuffer();
	}

}
