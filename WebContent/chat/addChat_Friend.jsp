<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<%
    Chat_FriendVO chat_FriendVO = (Chat_FriendVO) request.getAttribute("cf_no");
%>

<html>
<head>
    <title>���u��Ʒs�W - addEmp.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>�n�͸�Ʒs�W - addEmp.jsp</h3>
        </td>
        <td>
            <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
        </td>
    </tr>
</table>

<h3>��ƭ��u:</h3>
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

<FORM METHOD="post" ACTION="Chat_FrienServlet.do" name="form1">
    <table border="0">

        <tr>
            <td>���u�m�W:</td>
            <td><input type="TEXT" name="mem_no_o" size="45"
                       value="<%= (chat_FriendVO==null)? "M0000007" : chat_FriendVO.getMem_no_o()%>"/></td>
        </tr>
        <tr>
            <td>¾��:</td>
            <td><input type="TEXT" name="mem_no_o" size="45"
                       value="<%= (chat_FriendVO==null)? "M0000007" : chat_FriendVO.getMem_no_o()%>"/></td>
        </tr>
        <tr>
            <td>����:</td>
            <td><input type="TEXT" name="cf_is_del" size="45"
                       value="<%= (chat_FriendVO==null)? "1" : chat_FriendVO.getCf_is_del()%>"/></td>
        </tr>

        <jsp:useBean id="deptSvc" scope="page" class="com.member.model.MemberService"/>
        <tr>
            <td>����:<font color=red><b>*</b></font></td>
            <td><select size="1" name="deptno">
                <c:forEach var="deptVO" items="${deptSvc.all}">
                <option value="${memberVO.mem_no}" ${(chat_FriendVO.deptno==memberVO.deptno)? 'selected':'' } >
                        ${chat_FriendVO.dname}
                    </c:forEach>
            </select></td>
        </tr>

    </table>
    <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>
