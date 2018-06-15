/*******************************************************************************
*  Project      : BGF Retail Mobile Store Management
*  Program ID   : ServiceAOP.java
*  Description  : 모든 서비스 메소드의 인터셉터
*
********************************************************************************
*  Program History
*  Date        Author    Description
*  ----------  --------  --------------------------------------------------------
*  2018-06-15  LEEC.J    Created.
*******************************************************************************/

package kr.maxerve.admin.framework;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class ServiceAOP {
	private static final Logger logger = Logger.getLogger(ServiceAOP.class);

	public Object serviceAOP(ProceedingJoinPoint joinPoint) throws Throwable {
//		  HttpServletRequest request = null;
//		  HttpServletResponse response = null;
//
//	        for ( Object o : joinPoint.getArgs() ){
//	            if ( o instanceof HttpServletRequest ) {
//	                request = (HttpServletRequest)o;
//	            }
//	            if ( o instanceof HttpServletResponse ) {
//	                response = (HttpServletResponse)o;
//	            }
//	        }
	        // @todo
	        logger.debug("ServiceAOP..............................................");
	        logger.debug(joinPoint);
	        Object result = joinPoint.proceed();

		return result;

	}
}
