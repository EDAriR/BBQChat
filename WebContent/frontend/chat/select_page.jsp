<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>IBM Emp: Home</title>

    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
    <tr bgcolor='#CCCCFF' valign='middle' height='20'>
        <td><h3>IBM Emp: Home</h3><font color=red>( MVC )</font></td>
    </tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>��Ƭd��:</h3>
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

<jsp:useBean id="cgSvc" scope="page" class="com.chat.model.Chat_GroupService"/>
<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService"/>
<jsp:useBean id="cfSvc" scope="page" class="com.chat.model.Chat_FriendService"/>

<ul>
    <li><a href='<%=request.getContextPath()%>/frontend/chat/Chat_Group/listAllCG.jsp'>List</a>listAllCG</li>
    <br><br>
    <li>
        <a href='<%=request.getContextPath()%>/frontend/chat/Chat_Group_Item/listAllChat_Group_Item.jsp'>listAllChat_Group_Item.jsp</a>
        listAllChat_Group_Item
    </li>
    <br><br>
    <li><a href='<%=request.getContextPath()%>/frontend/chat/ChatFriend/listCF0403.jsp'>List</a>listCF0403.jsp</li>
    <br><br>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do">
            <p>�n�ͦC���X</p>
            <p>��J�|���s�� (�p M0000001 ):</p>
            <input type="text" name="memNo">
            <input type="submit" value="�e�X">getOneMemCFCG Chat_Group
            <input type="hidden" name="action" value="getOneMemCFCG">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do">
            <p>��J�s�սs�� (�p CG000001 ):</p>
            <input type="text" name="cg_no">
            <input type="submit" value="�e�X">getOne_For_Display Chat_Group
            <input type="hidden" name="action" value="getOne_For_Display">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do">
            <p>��J�s�սs�� (�p CG000001 ):</p>
            <input type="text" name="cgNo" value="">
            <input type="submit" value="�e�X">listCGs_ByCgno Chat_Group
            <input type="hidden" name="action" value="listCGs_ByCgno">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do">
            <p>��J�ۤv�|���s�� (�p M0000001 ):</p>
            <input type="text" name="memNo" value="">
            <input type="submit" value="�e�X">listCGs_ByMemNo Chat_Group
            <input type="hidden" name="action" value="listCGs_ByMemNo">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
            <p>��J�n�ͽs�� (�p CF000001 ):</p>
            <input type="text" name="cf_no">
            <input type="submit" value="�e�X">getOne_For_Display ChatFriend
            <input type="hidden" name="action" value="getOne_For_Display">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
            <p>��J�ۤv�|���s�� (�p M0000001 ):</p>
            <input type="text" name="memNoS" value="">getCF_For_Display
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="getCF_For_Display">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/Chat_Group/Chat_GroupServlet.do">
            <p>��ܸs�զW��:</p>
            <select size="1" name="cg_no">
                <c:forEach var="cgVO" items="${cgSvc.all}">
                <option value="${cgVO.cg_no}">${cgVO.cg_name}
                    </c:forEach>
            </select>
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="getOneCG_For_Display">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/chat/ChatFriend/Chat_FriendServlet.do">

            <%--end dropdown--%>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ��ܸs�զW��:<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li>
                        <c:forEach var="cgVO" items="${cgSvc.all}">
                            <input type="hidden" name="action" value="getOne_For_Display">
                            <button class="dropdown-item" type="submit" value="${cgVO.cg_no}">${cgVO.cg_name}</button>
                        </c:forEach>
                    </li>

                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div>

            <%--end dropdown--%>
        </FORM>

        <!-- Single button -->

    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
            <b>��ܭ��u�m�W:</b>
            <select size="1" name="empno">
                <c:forEach var="empVO" items="${empSvc.all}">
                <option value="${empVO.empno}">${empVO.ename}
                    </c:forEach>
            </select>
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="getOne_For_Display">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do">
            <b><font color=orange>��ܳ���:</font></b>
            <select size="1" name="deptno">
                <c:forEach var="deptVO" items="${deptSvc.all}">
                <option value="${deptVO.deptno}">${deptVO.dname}
                    </c:forEach>
            </select>
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="listEmps_ByDeptno_A">
        </FORM>
    </li>
</ul>


<h3>���u�޲z</h3>

<ul>
    <li><a href='<%=request.getContextPath()%>/emp/addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

<h3><font color=orange>�����޲z</font></h3>

<ul>
    <li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a> all Depts.</li>
</ul>

</body>

</html>
