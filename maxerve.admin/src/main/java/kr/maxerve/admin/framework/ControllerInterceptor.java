/*******************************************************************************
*  Project      : BGF Retail Mobile Store Management
*  Program ID   : LoginInterceptor.java
*  Description  : 컨트롤러의 인터셉터로 로그인 체크
*
********************************************************************************
*  Program History
*  Date        Author    Description
*  ----------  --------  --------------------------------------------------------
*  2018-06-15  LEEC.J    Created.
*******************************************************************************/

package kr.maxerve.admin.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class ControllerInterceptor extends HandlerInterceptorAdapter {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");

		return super.preHandle(request, response, handler);

//		if(authFree == null){
//			if(memberService.isLogin()){
//				LoggingInterface loggingInterface = ((HandlerMethod) handler).getMethodAnnotation(LoggingInterface.class);
//
//				if(loggingInterface != null){
//					ObjectMapper mapper = new ObjectMapper();
//					String jsonStr = mapper.writeValueAsString(request.getParameterMap());
//
//					String msg = loggingInterface.value();
//
//					LoggingVO insertVO = new LoggingVO();
//					insertVO.setMsg(msg);
//					insertVO.setParams(jsonStr);
//					insertVO.setStoreCd(memberService.getStoreCd());
//					insertVO.setDeviceId(memberService.getDeviceId());
//
//					loggingDAO.insertLogging(insertVO);
//
//					request.setAttribute("logNo", insertVO.getLogNo());
//				}
//
//				return super.preHandle(request, response, handler);
//			}else{
//				response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                return false;
//			}
//		}else{
//			return super.preHandle(request, response, handler);
//		}
	}
}
