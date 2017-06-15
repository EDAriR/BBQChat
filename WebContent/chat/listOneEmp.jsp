<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.chat.model.*"%>
<%
Chat_FriendVO chat_FriendVO = (Chat_FriendVO) request.getAttribute("cf_no"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>職位</th>
		<th>雇用日期</th>
		<th>薪水</th>
		<th>獎金</th>
		<th>部門</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=chat_FriendVO.getEmpno()%></td>
		<td><%=chat_FriendVO.getEname()%></td>
		<td><%=chat_FriendVO.getJob()%></td>
		<td><%=chat_FriendVO.getHiredate()%></td>		
	</tr>
</table>

</body>
</html>
