<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chat.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
Chat_FriendService chat_FriendSvc = new Chat_FriendService();
    List<Chat_FriendVO> list = chat_FriendSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有好友資料 - listAllChat_Friend.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - listAllChat_Friend.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>好友編號</th>
		<th>自己會員編號</th>
		<th>好友會員編號</th>
		<th>狀態碼</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="chat_FriendVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chat_FriendVO.cf_no}</td>
			<td>${chat_FriendVO.mem_no_s}</td>
			<td>${chat_FriendVO.mem_no_o}</td>
			<td>${chat_FriendVO.cf_is_del}</td>			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Chat_FrienServlet.do">  <%--QQ--%>
			     <input type="submit" value="修改">
			     <input type="hidden" name="cf_no" value="${chat_FriendVO.cf_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
