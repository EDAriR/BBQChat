<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chat.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
Chat_GroupService cgSvc = new Chat_GroupService();
	List<Chat_GroupVO> list = cgSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="chat_Group_ItemSvc" scope="page" class="com.chat.model.Chat_Group_ItemService" />

<html>

<head>
<title>所有員工資料 - listAllEmp.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllEmp.jsp</h3>
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
		<th>群組名稱</th>
		<th>嬰兒所屬年份</th>
		<th>是否有過敏性鼻炎</th>
		<th>是否有氣喘</th>
		<th>是否有過敏性結膜炎</th>
		<th>是否有過敏性胃腸炎</th>
		<th>是否有異位性皮膚炎</th>
		<th>其他罕見疾病</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="chat_GroupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(chat_GroupVO.empno==param.empno) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${chat_GroupVO.cg_no}</td>
			<td>${chat_GroupVO.cg_name}</td>
			<td>${chat_GroupVO.cg_year}</td>
			<td>${chat_GroupVO.cg_is_ar}</td>
			<td>${chat_GroupVO.cg_is_ab}</td>
			<td>${chat_GroupVO.cg_is_ac}</td>
			<td>${chat_GroupVO.cg_is_sf}</td>
			<td>${chat_GroupVO.cg_is_ad}</td>
			<td>${chat_GroupVO.cg_baby_rd}</td>			
			<td><c:forEach var="chat_Group_ItemVO" items="${chat_Group_ItemSvc.all}">
                    <c:if test="${chat_GroupVO.deptno==chat_Group_ItemVO.deptno}">
	                    ${chat_Group_ItemVO.deptno}【${chat_Group_ItemVO.dname} - ${chat_Group_ItemVO.loc}】
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Chat_GroupServlet.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="empno" value="${chat_GroupVO.empno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Chat_GroupServlet.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="empno" value="${chat_GroupVO.empno}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
