<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<%
    Chat_FriendVO chat_FriendVO = (Chat_FriendVO) request.getAttribute("cf_no");
%>

<html>
<head>
    <title>���u��Ʒs�W - addEmp.jsp</title></head>

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

<FORM METHOD="post"
      name="form1"
      ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
    <table border="0">

        <tr>
            <td>�|���s�� (M0000007):</td>
            <td>
                <input type="TEXT" name="mem_no_s" size="45"
                       value="<%= (chat_FriendVO==null)? "M0000007" : chat_FriendVO.getMem_no_s()%>"/></td>
        </tr>
        <tr>
            <td>�n�ͷ|���s�� ( M0000026 ):</td>
            <td><input type="TEXT" name="mem_no_o" size="45"
                       value="<%= (chat_FriendVO==null)? "M0000007" : chat_FriendVO.getMem_no_o()%>"/></td>
        </tr>
        <tr>
            <td>���A�X:</td>
            <td><input type="TEXT" name="cfdel" size="45"
                       value="<%= (chat_FriendVO==null)? "1" : chat_FriendVO.getCf_is_del()%>"/></td>
        </tr>

        <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
        <tr>
            <td>����:<font color=red><b>*</b></font></td>
            <td><select size="1" name="mem_no">
                <c:forEach var="memberVO" items="${memberSvc.all}">
                <option value="${memberVO.mem_no}" ${(chat_FriendVO.mem_no_o==memberVO.mem_no)? 'selected':'' } >
                        ${memberVO.mem_name}
                    </c:forEach>
            </select></td>
        </tr>


    </table>
    <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="�e�X�s�W">
</FORM>

<FORM METHOD="post"
      name="form2"
      ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
    <input type="text" name="mem_no_s" value="M0000027">

<c:forEach var="memberVO" items="${memberSvc.all}">
    <label for="${memberVO.mem_no}">${memberVO.mem_name}</label>
    <input type="hidden" name="action" value="insert">
    <input type="hidden" name="mem_no_o" value="${memberVO.mem_no}"> <%--����n�諾���qC��session--%>
    <input type="hidden" name="cfdel" value="0">
    <button class="btn btn-danger btn-large"
            name="${memberVO.mem_no}" id="${memberVO.mem_no}">
        <i class="icon-ok-sign"></i>�e�X�s�W
    </button>
</c:forEach>
</FORM>
</body>

</html>
