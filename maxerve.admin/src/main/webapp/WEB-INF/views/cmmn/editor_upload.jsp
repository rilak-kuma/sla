<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type='text/javascript'>
<c:choose>
	<c:when test="${empty msg}">
		window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}', '<spring:eval expression="@propertiesService['upload.url.root']"/>${fileDTO.filePath}', '파일 전송 완료.');
	</c:when>
	<c:otherwise>
		alert("${msg}");
		window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}', '', '');
	</c:otherwise>
</c:choose>
</script>