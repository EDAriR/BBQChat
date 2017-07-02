<%@ page import="com.chat.model.Chat_Group_ItemVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%
    List<Chat_Group_ItemVO> cgilsit = (List<Chat_Group_ItemVO>) request.getAttribute("cgilsit");
    pageContext.setAttribute("cgilsit", cgilsit);
%>

<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService"/>
<html>
<head>
    <title>�������u - listCGs_ByCgno.jsp</title>
</head>
<body bgcolor='white'>

<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>�������u - listCGs_ByCgno.jsp</h3>
            <a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32"
                                                                         border="0">�^����</a>
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
        <th>�|���s��</th>
        <th>�R��</th>
    </tr>

    <c:forEach var="cgVO" items="${cgilsit}">
        <tr align='center' valign='middle' ${(cgVO.cg_no==param.cg_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
            <td>${cgVO.cg_no}</td>
            <td>${cgVO.mem_no}</td>

            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
                    <input type="submit" value="�R��">
                    <input type="hidden" name="chat_GroupVO" value="${cgVO.mem_no}">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <!--�e�X�����������|��Controller-->
                    <input type="hidden" name="action" value="delete"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>

<br>�����������|:<br><b>
    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%>
</b>
</body>
</html>
