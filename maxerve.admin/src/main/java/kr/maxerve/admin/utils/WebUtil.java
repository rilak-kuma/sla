package kr.maxerve.admin.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.maxerve.admin.file.vo.FileVO;

public class WebUtil {
	/**
     * get request
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
    	ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest hsr = sra.getRequest();
    	return hsr;
    }

	/**
     * 서블릿 파라미터 리스트를 이름순으로 정렬하여 반환한다.
     *
     * @param request
     *            서블릿 요청
     * @return 파라미터 이름을 담고 있는 <code>java.util.List</code>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static List getOrderedEnumeration(Enumeration names) {
        /* J2SE 1.4에서부터 지원함 */
        return Collections.list(names);
    }

    /**
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static String getRequestHeaderMessage(HttpServletRequest request)
    {
        StringBuffer message = new StringBuffer();
        String name;
        Object value;
        for(Iterator it = getOrderedEnumeration(request.getHeaderNames()).iterator(); it.hasNext(); message.append(name + "=" + value + "\n"))
        {
            name = (String)it.next();
            value = request.getHeader(name);
        }

        return message.toString();
    }

    /**
     * request에서 mode명과 해당하는 값을 얻어 String 객체로 반환한다.
     *
     * @param request
     * @return String
     */
    @SuppressWarnings("rawtypes")
	public static String getRequestMessage(ServletRequest request) {
        StringBuffer message = new StringBuffer();
        String mode = request.getParameter("mode");

        if ((mode != null) && (mode.trim().length() > 0)) {
            message.append("mode=" + mode + "\n");
        }
        for (Iterator it = getOrderedEnumeration(request.getParameterNames())
                .iterator(); it.hasNext();) {
            String name = (String) it.next();
            if ("mode".equals(name)) {
                continue;
            }
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                if ("j_password".equals(name)) {
                    message.append(name + "=xxxx\n");
                } else {
                    message.append(name + "=" + values[i] + "\n");
                }
            }
        }
        return message.toString();
    }

    /**
     * HttpSession에 주어진 키 값으로 세션 정보를 생성하는 기능
     *
     * @param request
     * @param keyStr - 세션 키
     * @param valStr - 세션 값
     * @throws Exception
     */
    public static void setSessionAttribute(HttpServletRequest request, String keyStr, String valStr) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute(keyStr, valStr);
    }

    public static void setSessionAttribute(String keyStr, String valStr) throws Exception {
    	setSessionAttribute(getCurrentRequest(), keyStr, valStr);
    }

    /**
     * HttpSession에 주어진 키 값으로 세션 객체를 생성하는 기능
     *
     * @param request
     * @param keyStr - 세션 키
     * @param valStr - 세션 값
     * @throws Exception
     */
    public static void setSessionAttribute(HttpServletRequest request, String keyStr, Object obj) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute(keyStr, obj);
    }

    public static void setSessionAttribute(String keyStr, Object obj) throws Exception {
		setSessionAttribute(getCurrentRequest(), keyStr, obj);
    }

    /**
     * HttpSession에 존재하는 주어진 키 값에 해당하는 세션 값을 얻어오는 기능
     *
     * @param request
     * @param keyStr - 세션 키
     * @return
     * @throws Exception
     */
    public static Object getSessionAttribute(HttpServletRequest request, String keyStr) throws Exception {
		HttpSession session = request.getSession();
		return session.getAttribute(keyStr);
    }

    public static Object getSessionAttribute(String keyStr) throws Exception {
		return getSessionAttribute(getCurrentRequest(), keyStr);
    }

    /**
     * HttpSession 객체내의 모든 값을 호출하는 기능
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static String getSessionValuesString(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String returnVal = "";

		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
		    String sessionKey = (String)e.nextElement();
		    returnVal = returnVal + "[" + sessionKey + " : " + session.getAttribute(sessionKey) + "]";
		}

		return returnVal;
    }

    public static String getSessionValuesString() throws Exception {
		return getSessionValuesString(getCurrentRequest());
    }

    /**
     * HttpSession에 존재하는 세션을 주어진 키 값으로 삭제하는 기능
     *
     * @param request
     * @param keyStr - 세션 키
     * @throws Exception
     */
    public static void removeSessionAttribute(HttpServletRequest request, String keyStr) throws Exception {

	HttpSession session = request.getSession();
	session.removeAttribute(keyStr);
    }

    /**
     * 쿠키생성 - 쿠키의 유효시간을 설정하지 않을 경우 쿠키의 생명주기는 브라우저가 종료될 때까지
     *
     * @param response - Response
     * @param cookieNm - 쿠키명
     * @param cookieValue - 쿠키값
     * @return
     * @exception
     * @see
     */

    public static void setCookie(HttpServletResponse response, String cookieNm, String cookieVal, String domain) throws UnsupportedEncodingException {

		// 특정의 encode 방식을 사용해 캐릭터 라인을 application/x-www-form-urlencoded 형식으로 변환
		// 일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할
		String cookieValue = URLEncoder.encode(cookieVal, "utf-8");

		// 쿠키생성
		Cookie cookie = new Cookie(cookieNm, cookieValue);
		cookie.setPath(domain);

		// response 내장 객체를 이용해 쿠키를 전송
		response.addCookie(cookie);
    }

    /**
     * 쿠키생성 - 입력받은 분만큼 쿠키를 유지되도록 세팅한다.
     * 쿠키의 유효시간을 5분으로 설정 =>(cookie.setMaxAge(60 * 5)
     * 쿠키의 유효시간을 10일로 설정 =>(cookie.setMaxAge(60 * 60 * 24 * 10)
     *
     * @param response - Response
     * @param cookieNm - 쿠키명
     * @param cookieValue - 쿠키값
     * @param minute - 지속시킬 시간(분)
     * @return
     * @exception
     * @see
     */
    public static void setCookie(HttpServletResponse response, String cookieNm, String cookieVal, String domain, int minute) throws UnsupportedEncodingException {

		// 특정의 encode 방식을 사용해 캐릭터 라인을 application/x-www-form-urlencoded 형식으로 변환
		// 일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할
		String cookieValue = URLEncoder.encode(cookieVal, "utf-8");

		// 쿠키생성 - 쿠키의 이름, 쿠키의 값
		Cookie cookie = new Cookie(cookieNm, cookieValue);

		// 쿠키의 유효시간 설정
		cookie.setMaxAge(60 * minute);
		cookie.setPath(domain);

		// response 내장 객체를 이용해 쿠키를 전송
		response.addCookie(cookie);
    }

    /**
     * 쿠키값 사용 - 쿠키값을 읽어들인다.
     *
     * @param request - Request
     * @param name - 쿠키명
     * @return 쿠키값
     * @exception
     * @see
     */
    public static String getCookie(HttpServletRequest request, String cookieNm) throws Exception {

		// 한 도메인에서 여러 개의 쿠키를 사용할 수 있기 때문에 Cookie[] 배열이 반환
		// Cookie를 읽어서 Cookie 배열로 반환
		Cookie[] cookies = request.getCookies();

		if (cookies == null)
		    return "";

		String cookieValue = null;

		// 입력받은 쿠키명으로 비교해서 쿠키값을 얻어낸다.
		for (int i = 0; i < cookies.length; i++) {

		    if (cookieNm.equals(cookies[i].getName())) {

				// 특별한 encode 방식을 사용해 application/x-www-form-urlencoded 캐릭터 라인을 디코드
				// URLEncoder로 인코딩된 결과를 디코딩하는 클래스
				cookieValue = URLDecoder.decode(cookies[i].getValue(), "utf-8");

				break;

		    }
		}

	return cookieValue;
    }

    /**
     * 쿠키값 삭제 - cookie.setMaxAge(0) - 쿠키의 유효시간을 0으로 설정해 줌으로써 쿠키를 삭제하는 것과 동일한 효과
     *
     * @param request - Request
     * @param name - 쿠키명
     * @return 쿠키값
     * @exception
     * @see
     */
    public static void removeCookie(HttpServletResponse response, String cookieNm) throws UnsupportedEncodingException {
    	setCookie(response, cookieNm, "", "/", 0);

    }

    /**
     * HTTP 요청
     * @param strUrl
     * @return
     * @throws Exception
     */
    public static String getHttpURLRequest(String strUrl) throws Exception {
		return getHttpURLRequest(strUrl, "UTF-8");
	}

	public static String getHttpURLRequest(String strUrl, String enc) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStreamReader is = null;
		HttpURLConnection conn = null;
		String error = "";

		try{

			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(3000);

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			error = "1";
			conn.connect();
			error = "2";

			is = new InputStreamReader(conn.getInputStream(), enc);
			error = "3";
			char[] buff = new char[4086];
			int index = -1;
			while (is != null && (index = is.read(buff)) != -1) {
				sb.append(new String(buff, 0, index));
			}
		}catch(Exception e){
			return error;
		}finally {
			if (is != null)
				is.close();

			if(conn != null) conn.disconnect();
		}
		return sb.toString();
	}

	/**
     * 서버 파일에 대하여 다운로드를 처리한다.
     *
     * @param response
     * @param streFileNm
     *            : 파일저장 경로가 포함된 형태
     * @param orignFileNm
     * @throws Exception
     */
    public static void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
		String downFileName = streFileNm;
		String orgFileName = orignFileNm;

		File file = new File(downFileName);
		//log.debug(this.getClass().getName()+" downFile downFileName "+downFileName);
		//log.debug(this.getClass().getName()+" downFile orgFileName "+orgFileName);

		if (!file.exists()) {
		    throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
		    throw new FileNotFoundException(downFileName);
		}

		//byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
		int fSize = (int)file.length();
		if (fSize > 0) {
			// 보안점검사항 적용
			BufferedInputStream in = null;
			try{
		    in = new BufferedInputStream(new FileInputStream(file));

		    String mimetype = "text/html"; //"application/x-msdownload"
		    response.setBufferSize(fSize);
		    response.setContentType(mimetype);
		    response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
		    response.setContentLength(fSize);
		    //response.setHeader("Content-Transfer-Encoding","binary");
		    //response.setHeader("Pragma","no-cache");
		    //response.setHeader("Expires","0");
		    FileCopyUtils.copy(in, response.getOutputStream());
		    in.close();
		    response.getOutputStream().flush();
		    response.getOutputStream().close();
			} finally {
				if (in !=null) {
					try{
				          in.close();
				        }catch(Exception e){   }
				}
			}
		}
    }

    /**
     * 첨부파일을 서버에 저장한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static HashMap<String, String> writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;

		HashMap<String, String> map = new HashMap<String, String>();

		try {
			String orginFileName = file.getOriginalFilename();
			int index = orginFileName.lastIndexOf(".");
			String fileExt = orginFileName.substring(index + 1);
			long size = file.getSize();
			if(newName.equals("")) {
				newName = DateTimeUtil.date2String(new Date(), "yyyyMMdd") + "." + fileExt;
			}


		    stream = file.getInputStream();
		    File cFile = new File(stordFilePath);

		    if (!cFile.isDirectory()) {
		    	boolean _flag = cFile.mkdir();
				if (!_flag) {
				    throw new IOException("Directory creation Failed ");
				}
		    }

		    bos = new FileOutputStream(stordFilePath + "/" + newName);

		    int bytesRead = 0;
		    byte[] buffer = new byte[FileUtil.BUFFER_SIZE];

		    while ((bytesRead = stream.read(buffer, 0, FileUtil.BUFFER_SIZE)) != -1) {
		    	bos.write(buffer, 0, bytesRead);
		    }

		    map.put("originalFileName", orginFileName);
			map.put("uploadFileName", newName);
			map.put("fileExtension", fileExt);
			map.put("FILE_PATH", stordFilePath);
			map.put("fileSize", String.valueOf(size));
		} catch (FileNotFoundException fnfe) {
		    fnfe.printStackTrace();
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if (bos != null) {
				try {
				    bos.close();
				} catch (Exception ignore) {
				    //log.debug("IGNORED: " + ignore.getMessage());
				}
		    }
		    if (stream != null) {
				try {
				    stream.close();
				} catch (Exception ignore) {
				    //log.debug("IGNORED: " + ignore.getMessage());
				}
		    }
		}

		return map;
    }

    /**
     * 파일을 Upload 처리한다.
     *
     * @param request
     * @param where
     * @param maxFileSize
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static List<FileVO> uploadFiles(HttpServletRequest request, String where, long maxFileSize) throws Exception {
		List<FileVO> list = new ArrayList<FileVO>();

		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
		Iterator fileIter = mptRequest.getFileNames();

		while (fileIter.hasNext()) {
		    MultipartFile mFile = mptRequest.getFile((String)fileIter.next());

		    FileVO vo = new FileVO();

		    String tmp = mFile.getOriginalFilename();

	        if (tmp.lastIndexOf("\\") >= 0) {
	        	tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
	        }
	        vo.setOrigFileNm(tmp);
	        vo.setCnttTyp(mFile.getContentType());
	        vo.setServerSubPath(DateTimeUtil.date2String(new Date(), "yyyyMMdd"));
	        vo.setFilePath(DateTimeUtil.date2String(new Date(), "yyyyMMdd"));
	        vo.setFileSize(String.valueOf(mFile.getSize()));

	        if (tmp.lastIndexOf(".") >= 0) {
	   	 		vo.setFilePath(vo.getFilePath() + tmp.substring(tmp.lastIndexOf(".")));
	        }

	        if (mFile.getSize() > 0) {
	        	// 보안관련 조치 사항 적용
	            String vaildation = StringUtil.getPatternString("\\.\\.\\/", where + "/" + vo.getServerSubPath() + "/" + vo.getFilePath(),0);
	            if(!vaildation.equals("")) throw new Exception(".. charactor is not allowed file handling");

	        	FileUtil.saveFile(mFile.getInputStream(), new File(where + "/" + vo.getServerSubPath() + "/" + vo.getFilePath()));
	            list.add(vo);
	        }
		}

		return list;
    }

    /**
     * @param request
     * @return ip
     * @throws Exception
     */
    public static String remoteAddr(HttpServletRequest request) throws Exception
    {
    	HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip)) {
            ip = req.getRemoteAddr();
        }

        return ip;
    }

    public static String remoteAddr() throws Exception {
    	return remoteAddr(getCurrentRequest());
    }
}
