<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.chat.model.*"%>
<%
Chat_FriendVO chat_FriendVO = (Chat_FriendVO) request.getAttribute("cf_no"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<html>
<head>
<title>���u��� - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��� - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>���u�s��</th>
		<th>���u�m�W</th>
		<th>¾��</th>
		<th>���Τ��</th>
		<th>�~��</th>
		<th>����</th>
		<th>����</th>
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