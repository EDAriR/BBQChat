<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Emp: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Emp: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllChat_Friend.jsp'>List</a>AllChat_Friend. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="emp.do" >
        <b>��J�n�ͽs�� (�p7001):</b>
        <input type="text" name="cf_no">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="cfSvc" scope="page" class="com.chat.model.Chat_FriendService" />
   
  <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>��ܭ��u�s��:</b>
       <select size="1" name="cf_no">
         <c:forEach var="chat_FriendVO" items="${cfSvc.all}" >
          <option value="${chat_FriendVO.cf_no}">${chat_FriendVO.cf_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="cf_no">
         <c:forEach var="chat_FriendVO" items="${cfSvc.all}" >
          <option value="${chat_FriendVO.cf_no}">${chat_FriendVO.mem_no_o}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addChat_Friend.jsp'>Add</a> a new Emp.</li>
</ul>

</body>

</html>