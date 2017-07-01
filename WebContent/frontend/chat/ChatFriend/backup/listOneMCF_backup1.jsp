<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<%@ page import="com.member.model.*" %>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
    List<Chat_FriendVO> list = (List<Chat_FriendVO>) request.getAttribute("oneMemCF");
   	pageContext.setAttribute("list",list); 
%>

<%-- 取出 對應的DeptVO物件--%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
    <title>群組資料 - listOneMCF.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>員工資料 - listOneMCF.jsp</h3> <a
                href="<%=request.getContextPath()%>/select_page.jsp"><img
                src="../images/back1.gif" width="100" height="32" border="0">回首頁</a>
        </td>
    </tr>
</table>


</tr>
<table border='1' bordercolor='#CCCCFF' width='800'>
    <tr>
        <th>好友編號</th>
        <th>好友會員編號</th>
        <th>狀態碼</th>
        <th>狀態碼</th>
        <th>修改</th>
    </tr>
 
<%@ include file="../page1.file" %>

    <c:forEach var="cflsit" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <tr align='center' valign='middle' ${(cflsit.cf_no==param.cf_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
            <td>${cflsit.cf_no}</td>
            <td>${cflsit.mem_no_o}</td>
            
            <td><%-- ${memSvc.all} --%>
            	<c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${cflsit.mem_no_o==memVO.mem_no}">
	                    ${memVO.mem_name}
                    </c:if>
            	</c:forEach>
            </td>
            
            <td>${cflsit.cf_is_del}</td>
            <td>
                <FORM METHOD="post"
                      ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">  <%--QQ--%>
                    <input type="submit" value="修改">
                    <input type="hidden" name="cf_no" value="${cflsit.cf_no}">
                    <input type="hidden" name="action" value="getOne_For_Update">
                </FORM>
            </td>
        </tr>
    </c:forEach>
</table>
 
 <%@ include file="../page2.file" %>

</body>
</html>
