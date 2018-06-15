package kr.maxerve.admin.cmmn.controller;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.tiles.Attribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.framework.CustomReloadableResourceBundleMessageSource;

@Controller
@RequestMapping(value="/cmmn")
public class CommonController {

	@Resource(name="fileService")
	private FileService fileService;

	@Resource(name="propertiesService")
	private Properties propertiesService;

	@RequestMapping(value="/messagesource")
	public void messagesource(HttpSession session, ModelMap model) throws Exception {
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		CustomReloadableResourceBundleMessageSource messageSource = (CustomReloadableResourceBundleMessageSource)wContext.getBean("messageSource");
		Properties messageMap = messageSource.getAllProperties(Locale.KOREA);
		Properties properties = new Properties();

		properties.put("upload.url.root", propertiesService.getProperty("upload.url.root"));


		model.addAttribute("messageSource", new Attribute(messageMap));
		model.addAttribute(properties);
	}

	@RequestMapping(value="/excel_view")
	public String excelView(@RequestParam(value="target")String target, ModelMap model) throws Exception {
		model.addAttribute("target", target);

		return "excelView";
	}

	/**
	 * 파일다운로드
	 * @param filePath
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/file_download")
	public String fileDownload(@RequestParam String filePath, ModelMap model) throws Exception {
		model.addAttribute("filePath", filePath);

		return "downloadView";
	}
}
