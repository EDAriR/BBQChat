<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chat.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%  
// EmpService empSvc = new EmpService();
// List<EmpVO> list = empSvc.getAll();
// pageContext.setAttribute("list",list);
%>
<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService" />

<html>
<head>
<title>所有部門 - listAllChat_Group_Item.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='orange' align='center' valign='middle' height='20'>
		<td>
		<h3>所有部門 - listAllChat_Group_Item.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
  <font color='red'>請修正以下錯誤:
  <ul>
  <c:forEach var="message" items="${errorMsgs}">
    <li>${message}</li>
  </c:forEach>
  </ul>
  </font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>群組編號</th>
		<th>會員編號</th>
		<th>刪除<font color=red>(關聯測試與交易-小心)</font></th>
		<th>查詢群組會員</th>
	</tr>
	
	<c:forEach var="cgiVO" items="${cgiSvc.all}">
		<tr align='center' valign='middle'>
			<td>${cgiVO.cg_no}</td>
			<td>${cgiVO.mem_no}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="cg_no" value="${cgiVO.cg_no}">
			    <input type="hidden" name="action" value="delete_cgi">
			</td></FORM>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="deptno" value="${cgiVO.cg_no}">
			    <input type="hidden" name="action" value="listCGs_ByCgno_B">
			</td></FORM>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listCGs_ByCgno")!=null){%>
       <jsp:include page="listCGs_ByCgno.jsp" />
<%} %>

</body>
</html>
