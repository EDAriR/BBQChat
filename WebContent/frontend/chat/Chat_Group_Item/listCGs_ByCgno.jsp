<%@ page import="com.chat.model.Chat_Group_ItemVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    List<Chat_Group_ItemVO> cgilsit = (List<Chat_Group_ItemVO>) request.getAttribute("cgilsit");
    pageContext.setAttribute("cgilsit", cgilsit);
%>

<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService"/>
<html>
<head>
    <title>部門員工 - listCGs_ByCgno.jsp</title>
</head>
<body bgcolor='white'>

<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>部門員工 - listCGs_ByCgno.jsp</h3>
            <a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32"
                                                                         border="0">回首頁</a>
        </td>
    </tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
    <tr>
        <th>群組編號</th>
        <th>會員編號</th>
        <th>刪除</th>
    </tr>

    <c:forEach var="cgVO" items="${cgilsit}">
        <tr align='center' valign='middle' ${(cgVO.cg_no==param.cg_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
            <td>${cgVO.cg_no}</td>
            <td>${cgVO.mem_no}</td>

            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="chat_GroupVO" value="${cgVO.mem_no}">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <!--送出本網頁的路徑給Controller-->
                    <input type="hidden" name="action" value="delete"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>

<br>本網頁的路徑:<br><b>
    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%>
</b>
</body>
</html>
