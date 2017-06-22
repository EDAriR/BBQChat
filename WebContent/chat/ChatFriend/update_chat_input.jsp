<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<% //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
    Chat_FriendVO chat_FriendVO = (Chat_FriendVO) request.getAttribute("chat_FriendVO");
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="js/calendar.css">
    <title>員工資料修改 - update_chat_Frien_input.jsp</title>
</head>
<body>

<div id="popupcalendar" class="text"></div>
<table border='1' cellpadding='5' cellspacing='0' width='400'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>員工資料修改 - update_chat_Frien_input.jsp</h3>
            <a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">
                回首頁
            </a>
        </td>
    </tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="Chat_FrienServlet.do" name="form1">
    <table border="0">
        <tr>
            <td>好友編號:<font color=red><b>*</b></font></td>
            <td><%=chat_FriendVO.getCf_no()%></td>
        </tr>
        <tr>
            <td>會員編號Ｓ:</td>
            <td><input type="TEXT" name="mem_no_s" size="45" value="<%=chat_FriendVO.getMem_no_s()%>"/></td>
        </tr>
        <tr>
            <td>會員編號Ｏ:</td>
            <td><input type="TEXT" name="mem_no_o" size="45" value="<%=chat_FriendVO.getMem_no_o()%>"/></td>
        </tr>
        <tr>
            <td>狀態碼:</td>
            <td><input type="TEXT" name="cf_is_del" size="45" value="<%=chat_FriendVO.getCf_is_del()%>"/></td>
        </tr>

        <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
        <tr>
            <td>部門:<font color=red><b>*</b></font></td>
            <td>
                <select size="1" name="mem_no">
                    <c:forEach var="memberVO" items="${memberSvc.all}">
                    <option value="${memberVO.mem_no}" ${(chat_FriendVO.mem_no_o==memberVO.mem_no)?'selected':'' } >
                            ${memberVO.mem_name}
                        </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="cf_no" value="<%=chat_FriendVO.getCf_no()%>">
    <input type="submit" value="送出修改"></FORM>

</body>
<script language="JavaScript" src="js/calendarcode.js"></script>
</html>
