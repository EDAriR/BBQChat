<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.chat.model.*"%>
<%
Chat_FriendVO chat_FriendVO = (Chat_FriendVO) request.getAttribute("chat_FriendVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<html>
<head>
<title>���u��� - listOneChat_Friend.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��� - listOneChat_Friend.jsp</h3>
		<a href="../listCF0403.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>�n�ͽs��</th>
		<th>�|���s��S</th>
		<th>�|���s��O</th>
		<th>���A</th>

	</tr>
	<tr align='center' valign='middle'>
		<td><%=chat_FriendVO.getCf_no()%></td>
		<td><%=chat_FriendVO.getMem_no_s()%></td>
		<td><%=chat_FriendVO.getMem_no_o()%></td>
		<td><%=chat_FriendVO.getCf_is_del()%></td>

	</tr>
</table>

</body>
</html>
