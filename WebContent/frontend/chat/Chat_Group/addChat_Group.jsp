<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chat.model.*"%>
<%
Chat_GroupVO chat_GroupVO = (Chat_GroupVO) request.getAttribute("chat_GroupVO");
%>

<html>
<head>
<title>員工資料新增 - addEmp.jsp</title></head>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料新增 - addChat_Group.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table border="0">

	<tr>
		<td>群組名稱:</td>
		<td><input type="TEXT" name="cg_name" size="45" 
			value="<%= (chat_GroupVO==null)? "吳永志" : chat_GroupVO.getCg_name()%>" /></td>
	</tr>
	<tr>
		<td>嬰兒所屬年份:</td>
		<td><input type="TEXT" name="cg_year" size="45"
			value="<%= (chat_GroupVO==null)? "2017-07-21" : chat_GroupVO.getCg_year()%>" /></td>
	</tr>	
	<tr>
		<td>是否有過敏性鼻炎:</td>
		<td><input type="TEXT" name="cg_is_ar" size="45"
			value="<%= (chat_GroupVO==null)? "0" : chat_GroupVO.getCg_is_ar()%>" /></td>
	</tr>
	<tr>
		<td>是否有氣喘:</td>
		<td><input type="TEXT" name="cg_is_ab" size="45"
			value="<%= (chat_GroupVO==null)? "0" : chat_GroupVO.getCg_is_ab()%>" /></td>
	</tr>
	<tr>
		<td>是否有過敏性結膜炎:</td>
		<td><input type="TEXT" name="cg_is_ac" size="45"
			value="<%= (chat_GroupVO==null)? "0" : chat_GroupVO.getCg_is_ac()%>" /></td>
	</tr>
	<tr>
		<td>是否有過敏性胃腸炎:</td>
		<td><input type="TEXT" name="cg_is_sf" size="45"
			value="<%= (chat_GroupVO==null)? "1" : chat_GroupVO.getCg_is_sf()%>" /></td>
	</tr>
	
	<tr>
		<td>是否有異位性皮膚炎:</td>
		<td><input type="TEXT" name="cg_is_ad" size="45"
			value="<%= (chat_GroupVO==null)? "1" : chat_GroupVO.getCg_is_ad()%>" /></td>
	</tr>
	<tr>
		<td>其他罕見疾病:</td>
		<td><input type="TEXT" name="cg_baby_rd" size="45"
			value="<%= (chat_GroupVO==null)? "123" : chat_GroupVO.getCg_baby_rd()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
