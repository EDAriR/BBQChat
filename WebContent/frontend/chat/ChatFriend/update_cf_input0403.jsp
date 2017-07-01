<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<%
    Chat_FriendVO cfVO = (Chat_FriendVO) request.getAttribute("chat_FriendVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
    <title>員工資料修改 - update_cf_input.jsp</title></head>

<body bgcolor='white'>

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

    <table border="0">
        <tr>
            <td>cf編號:<font color=red><b>*</b></font></td>
            <td>
            	<%=cfVO.getCf_no()%>
            </td>
        </tr>
        <tr>
            <td>cfs姓名:</td>
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
    <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
    <!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
    <input type="submit" value="送出修改"></FORM>

<br>送出修改的來源網頁路徑:<br><b>
    <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
    <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%>
    (此範例目前用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp)</b>
</body>
</html>
