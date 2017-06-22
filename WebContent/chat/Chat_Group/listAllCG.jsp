<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chat.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
Chat_GroupService cgSvc = new Chat_GroupService();
	List<Chat_GroupVO> list = cgSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="chat_Group_ItemSvc" scope="page" class="com.chat.model.Chat_Group_ItemService" />

<html>

<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllEmp.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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
		<th>��L�u���e�f</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="chat_GroupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(chat_GroupVO.cg_no==param.cg_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${chat_GroupVO.cg_no}</td>
			<td>${chat_GroupVO.cg_name}</td>
			<td>${chat_GroupVO.cg_year}</td>
			<td>${chat_GroupVO.cg_is_ar}</td>
			<td>${chat_GroupVO.cg_is_ab}</td>
			<td>${chat_GroupVO.cg_is_ac}</td>
			<td>${chat_GroupVO.cg_is_sf}</td>
			<td>${chat_GroupVO.cg_is_ad}</td>
			<td>${chat_GroupVO.cg_baby_rd}</td>			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Chat_GroupServlet.do">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="empno" value="${chat_GroupVO.cg_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Chat_GroupServlet.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="empno" value="${chat_GroupVO.cg_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
