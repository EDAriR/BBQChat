<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.chat.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	Chat_GroupVO chat_GroupVO = (Chat_GroupVO) request.getAttribute("chat_GroupVO");
%>

<%-- 取出 對應的DeptVO物件--%>
<%
	Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
	List<Chat_Group_ItemVO> cgiVO_List = cgiSvc.getOneChat_Group_No(chat_GroupVO.getCg_no());
%>
<html>
<head>
<title>員工資料 - listOneCG.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>員工資料 - ListOneEmp.jsp</h3> <a
				href="<%=request.getContextPath()%>/select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>

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
			<th>其他罕見疾病s</th>
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
