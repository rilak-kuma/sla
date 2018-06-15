package kr.maxerve.admin.framework;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MessageSourcePrepare implements ViewPreparer {
	
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		CustomReloadableResourceBundleMessageSource messageSource = (CustomReloadableResourceBundleMessageSource)wContext.getBean("messageSource");

		Properties messageMap = messageSource.getAllProperties(LocaleContextHolder.getLocale());

		attributeContext.putAttribute("messageSource", new Attribute(messageMap));
	}

}
