<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.chat.model.*"%>
<%-- �����m�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%
	Chat_GroupVO chat_GroupVO = (Chat_GroupVO) request.getAttribute("chat_GroupVO");
%>

<%-- ���X ������DeptVO����--%>
<%
	Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
	List<Chat_Group_ItemVO> cgiVO_List = cgiSvc.getOneChat_Group_No(chat_GroupVO.getCg_no());
%>
<html>
<head>
<title>�s�ո�� - listOneCG.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>�����m�߱ĥ� Script ���g�k����:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���u��� - ListOneEmp.jsp</h3> <a
				href="<%=request.getContextPath()%>/select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>�s�սs��</th>
			<th>�s�զW��</th>
			<th>������ݦ~��</th>
			<th>�O�_���L�өʻ�</th>
			<th>�O�_�����</th>
			<th>�O�_���L�өʵ�����</th>
			<th>�O�_���L�өʭG�z��</th>
			<th>�O�_������ʥֽ���</th>
			<th>��L�u���e�fs</th>
		</tr>
		<tr align='center' valign='middle'>
			<td><%=chat_GroupVO.getCg_no()%></td>
			<td><%=chat_GroupVO.getCg_name()%></td>
			<td><%=chat_GroupVO.getCg_year()%></td>
			<td><%=chat_GroupVO.getCg_is_ar()%></td>
			<td><%=chat_GroupVO.getCg_is_ab()%></td>
			<td><%=chat_GroupVO.getCg_is_ac()%></td>
			<td><%=chat_GroupVO.getCg_is_sf()%></td>
			<td><%=chat_GroupVO.getCg_is_ad()%></td>
			<td><%=chat_GroupVO.getCg_baby_rd()%></td>
		</tr>
	</table>

</body>
</html>
