<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h2>�湮 ������Ȳ</h2>

<h5>�湮 ����(<c:out value="${paginationInfo.totalRecordCount}" />)</h5>
<table border="1">
	<colgroup>
		<col />				<!-- ��ü�� -->
		<col width="5%"/>	<!-- �ο� -->
		<col width="20%"/>	<!-- �����Ͻ� -->
		<col width="20%"/>	<!-- ����Ȯ���� -->
		<col width="10%"/>	<!-- ��û�ڸ� -->
		<col />				<!-- ��û�� ����ó -->
		<col />				<!-- ��û�� --> 
		<col />				<!-- ������� -->
	</colgroup>
	<thead>
		<tr>
			<th>��ü��</th>
			<th>�ο�</th>
			<th>�����Ͻ�</th>
			<th>����Ȯ����</th>
			<th>��û�ڸ�</th>
			<th>��û�� ����ó</th>
			<th>
				<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">��û�ϡ�</a>
				<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">��û�ϡ�</a>
			</th>
			<th>
				<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">������¡�</a>
				<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">������¡�</a>
			</th>
		</tr>
	</thead>
	<c:choose>
	<c:when test="${fn:length(reserveList) > 0 }">
	<tbody id="reserveTB">
		<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus">
			<tr data-tour-aply-idx='<c:out value="${reserveList.tourAplyIdx }" />' onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
				<td><c:out value="${reserveList.oztnNm }" /></td>
				<td><c:out value="${reserveList.psct }" /></td>
				<td>
					<fmt:parseDate value="${reserveList.tourDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourDttm"/>
					<fmt:parseDate value="${reserveList.tourDttmEnd }" pattern="yyyy.MM.dd HH:mm" var="fmtTourDttmEnd"/>
                    <fmt:formatDate value="${fmtTourDttm }" pattern="yyyy�� MM�� dd�� (E) HH:mm"/><br/>
                    <%-- <fmt:formatDate value="${fmtTourDttmEnd }" pattern="~ HH:mm"/><br/> --%>
                    
					<fmt:parseDate value="${reserveList.tourSecondDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourSecondDttm"/>
					<fmt:parseDate value="${reserveList.tourSecondDttmEnd }" pattern="yyyy.MM.dd HH:mm" var="fmtTourSecondDttmEnd"/>
                    <fmt:formatDate value="${fmtTourSecondDttm }" pattern="yyyy�� MM�� dd�� (E) HH:mm"/>
                    <%-- <fmt:formatDate value="${fmtTourSecondDttmEnd }" pattern="~ HH:mm"/> --%>
                    
					<%-- <c:out value="${tourDttm }"/><c:out value="${tourDttmEnd }"/><br/>
					<c:out value="${tourSecondDttm }"/><c:out value="${tourSecondDttmEnd }"/> --%>
				</td>
				<td>
					<fmt:parseDate value="${reserveList.tourDsdDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourDsdDttm"/>
                    <fmt:formatDate value="${fmtTourDsdDttm }" pattern="yyyy�� MM�� dd�� (E) HH:mm"/><br/>
				</td>
				<td><c:out value="${reserveList.appyNm }" /></td>
				<td><c:out value="${reserveList.appyPhnNmbr }" /></td>
				<td>
					<fmt:parseDate value="${reserveList.creDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtCreDttm"/>
					<fmt:formatDate value="${fmtCreDttm }" pattern="yyyy�� MM�� dd�� HH��mm��"/>
					<%-- <c:out value="${creDttm }" /> --%>
				</td>
				<td>
					<c:out value="${reserveList.tourPgrNm eq '����Ϸ�' ? '����Ȯ��': reserveList.tourPgrNm}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</c:when>
	<c:otherwise>
		<tbody>
			<tr>
				<td colspan="7" align="center">��ȸ����� �����ϴ�.</td>
			</tr>
		</tbody>
	</c:otherwise>
	</c:choose>
</table>