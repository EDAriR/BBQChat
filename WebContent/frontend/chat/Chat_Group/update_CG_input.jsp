<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chat.model.*"%>
<%
Chat_GroupVO chat_GroupVO = (Chat_GroupVO) request.getAttribute("chat_GroupVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<title>���u��ƭק� - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��ƭק� - update_CG_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="Chat_GroupServlet.do" name="form1">
<table border="0">
	<tr>
		<td>�s�սs��:<font color=red><b>*</b></font></td>
		<td><%=chat_GroupVO.getCg_no()%></td>
	</tr>
	<tr>
		<td>�s�զW��:</td>
		<td><input type="TEXT" name="cg_name" size="45" value="<%=chat_GroupVO.getCg_name()%>" /></td>
	</tr>
	<tr>
		<td>����~��:</td>
		<td><input type="TEXT" name="cg_year" size="45"	value="<%=chat_GroupVO.getCg_year()%>" /></td>
	</tr>
	<tr>
		<td>�O�_���L�өʻ�:</td>
		<td><input type="TEXT" name="cg_is_ar" size="45"	value="<%=chat_GroupVO.getCg_is_ar()%>" /></td>
	</tr>
	<tr>
		<td>�O�_�����:</td>
		<td><input type="TEXT" name="cg_is_ab" size="45"	value="<%=chat_GroupVO.getCg_is_ab()%>" /></td>
	</tr>
	<tr>
		<td>�O�_���L�өʵ�����:</td>
		<td><input type="TEXT" name="cg_is_ac" size="45" value="<%=chat_GroupVO.getCg_is_ac()%>" /></td>
	</tr>
	<tr>
		<td>�O�_���L�өʵ�����:</td>
		<td><input type="TEXT" name="cg_is_sf" size="45" value="<%=chat_GroupVO.getCg_is_sf()%>" /></td>
	</tr>
	<tr>
		<td>�O�_���L�өʵ�����:</td>
		<td><input type="TEXT" name="cg_is_ad" size="45" value="<%=chat_GroupVO.getCg_is_ad()%>" /></td>
	</tr>
	<tr>
		<td>��L�u���e�f:</td>
		<td><input type="TEXT" name="cg_baby_rd" size="45" value="<%=chat_GroupVO.getCg_baby_rd()%>" /></td>
	</tr>

	<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService" />
	<tr>
		<td>����:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${cgiSvc.all}">
				<option value="${chat_Group_ItemVO.deptno}" ${(chat_GroupVO.deptno==chat_Group_ItemVO.deptno)?'selected':'' } >${chat_Group_ItemVO.dname}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="chat_GroupVO" value="<%=chat_GroupVO.getCg_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�Ω�:istAllEmp.jsp �P �ƦX�d�� listEmps_ByCompositeQuery.jsp-->
<input type="submit" value="�e�X�ק�"></FORM>

<br>�e�X�ק諸�ӷ��������|:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (���d�ҥثe�Ω�:istAllEmp.jsp �P �ƦX�d�� listEmps_ByCompositeQuery.jsp)</b>
</body>
</html>
