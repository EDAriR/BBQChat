<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<%
    Chat_FriendVO cfVO = (Chat_FriendVO) request.getAttribute("chat_FriendVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
    <title>���u��ƭק� - update_cf_input.jsp</title></head>

<body bgcolor='white'>

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

    <table border="0">
        <tr>
            <td>cf�s��:<font color=red><b>*</b></font></td>
            <td>
            	<%=cfVO.getCf_no()%>
            </td>
        </tr>
        <tr>
            <td>cfs�m�W:</td>
            <td>
            	<%=cfVO.getMem_no_s()%>
            </td>
        </tr>
        <tr>
            <td>cfo:</td>
            <td>
            	<%=cfVO.getMem_no_o()%>
            </td>
        </tr>
        <tr>
            <td>cfdel:</td>
            <td>            
            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do" name="form1">
            		<input type="TEXT" name="cfdel" size="45" value="<%=cfVO.getCf_is_del()%>"/>
            </td>
                        
        </tr>


    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="cf_no" value="<%=cfVO.getCf_no()%>"><%=cfVO.getCf_no()%>
    <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><%=request.getParameter("requestURL")%>
    <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
    <!--�Ω�:istAllEmp.jsp �P �ƦX�d�� listEmps_ByCompositeQuery.jsp-->
    <input type="submit" value="�e�X�ק�"></FORM>

<br>�e�X�ק諸�ӷ��������|:<br><b>
    <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
    <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%>
    (���d�ҥثe�Ω�:istAllEmp.jsp �P �ƦX�d�� listEmps_ByCompositeQuery.jsp)</b>
</body>
</html>
