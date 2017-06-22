<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Emp: Home</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
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
  <li><a href='<%=request.getContextPath()%>/chat/Chat_Group/listAllCG.jsp'>List</a> all CGs. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/Chat_Group/Chat_GroupServlet.do" >
        <b>��J���u�s�� (�p7001):</b>
        <input type="text" name="cg_no">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="cgSvc" scope="page" class="com.chat.model.Chat_GroupService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
       <b>��ܭ��u�s��:</b>
       <select size="1" name="cg_no">
         <c:forEach var="chat_GroupVO" items="${cgSvc.all}" > 
          <option value="${chat_GroupVO.cg_no}">${chat_GroupVO.cg_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/Chat_Group/Chat_GroupServlet.do" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="cg_no">
         <c:forEach var="chat_GroupVO" items="${cgSvc.all}" > 
          <option value="${chat_GroupVO.cg_no}">${chat_GroupVO.cg_name}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
  
  
   <jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.chat_Group_ItemService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do" >
       <b><font color=orange>��ܳ���:</font></b>
       <select size="1" name="cg_no">
         <c:forEach var="deptVO" items="${cgiSvc.all}" > 
          <option value="${chat_Group_ItemVO.cg_no}">${chat_Group_ItemVO.mem_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="listCGs_Bycg_no_A">
     </FORM>
  </li>
</ul>

<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1">
        <b><font color=blue>�U�νƦX�d��:</font></b> <br>
        <b>��J���u�s��:</b>
        <input type="text" name="cg_no" value="7001"><br>
           
       <b>��J���u�m�W:</b>
       <input type="text" name="ename" value="KING"><br>
       
       <b>��J���u¾��:</b>
       <input type="text" name="job" value="PRESIDENT"><br>
    
       <b>��ܳ���:</b>
       <select size="1" name="deptno" >
          <option value="">
         <c:forEach var="deptVO" items="${deptSvc.all}" > 
          <option value="${deptVO.deptno}">${deptVO.dname}
         </c:forEach>   
       </select><br>
           
       <b>���Τ��:</b>
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="hiredate" value="1981-11-17">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
		        
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="listEmps_ByCompositeQuery">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/chat/Chat_Group/addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

<h3><font color=orange>�����޲z</font></h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a> all Depts. </li>
</ul>

</body>

</html>