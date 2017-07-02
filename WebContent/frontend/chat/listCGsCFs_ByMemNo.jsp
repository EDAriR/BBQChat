<%@ page import="com.chat.model.Chat_FriendVO" %>
<%@ page import="com.chat.model.Chat_Group_ItemVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    List<Chat_Group_ItemVO> cglsit = (List<Chat_Group_ItemVO>) request.getAttribute("cglsit");
    pageContext.setAttribute("cglsit", cglsit);
    List<Chat_FriendVO> list = (List<Chat_FriendVO>) request.getAttribute("oneMemCF");
    pageContext.setAttribute("list", list);
%>

<jsp:useBean id="cgSvc" scope="page" class="com.chat.model.Chat_GroupService"/>
<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService"/>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>

<html>
<head>
    <title>部門員工 - listCGsCFs_ByMemNo.jsp</title>
    <!-- bootstrapcdn 3.37 Latest compiled and minified CSS  Optional theme-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <!-- bootstrapcdn 3.37 Latest compiled and minified CSS  Optional theme-->
    <!-- bootstrapcdn Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- bootstrapcdn Latest compiled and minified JavaScript -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../css/babeq.css">

    <link rel="stylesheet" href="chatlist.css">
</head>
<body bgcolor='white'>

<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>部門員工 - listCGs_ByMemNo.jsp</h3>
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

<h1>${memNo}</h1>

<table border='1' bordercolor='#CCCCFF' width='800'>
    <tr>
        <th>編號</th>
        <th>好友編號</th>
        <th>好友會員編號</th>
        <th>狀態碼</th>
        <th>狀態碼</th>
        <th>修改</th>
    </tr>

    <c:forEach var="cflsit" items="${list}">
        <tr align='center' valign='middle' ${(cflsit.cf_no==param.cf_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
            <td>${cflsit.cf_no}</td>
            <td>${cflsit.mem_no_s}</td>
            <td>${cflsit.mem_no_o}</td>

            <td>
                <c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${cflsit.mem_no_o==memVO.mem_no}">
                        ${memVO.mem_name}
                    </c:if>
                </c:forEach>
            </td>

            <td>
                <c:if test="${cflsit.cf_is_del==0}">
                    顯示
                </c:if>
                <c:if test="${cflsit.cf_is_del==1}">
                    隱藏
                </c:if>

            </td>
            <td>
                <c:if test="${cflsit.cf_is_del==0}">
                    <FORM METHOD="post"
                          ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
                        <input type="hidden" name="cf_no" value="${cflsit.cf_no}">
                        <input type="hidden" name="memNoS" value="${cflsit.mem_no_s}">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="cfdel" value="1">
                        <input type="submit" value="刪除好友">
                    </FORM>
                </c:if>
                <c:if test="${cflsit.cf_is_del==1}">
                    <FORM METHOD="post"
                          ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">
                        <input type="hidden" name="cf_no" value="${cflsit.cf_no}">
                        <input type="hidden" name="memNoS" value="${cflsit.mem_no_s}">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="cfdel" value="0">
                        <input type="submit" value="新增好友">
                    </FORM>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>


<table border='1' bordercolor='#CCCCFF' width='800'>
    <tr>
        <th>群組編號</th>
        <th>會員編號</th>
        <th>刪除</th>
    </tr>

    <c:forEach var="cgVO" items="${cglsit}">
        <tr align='center' valign='middle' ${(cgVO.mem_no==param.mem_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
            <td>${cgVO.cg_no}</td>
            <td>${cgVO.mem_no}</td>

            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="cgNo" value="${cgVO.cg_no}">
                    <input type="hidden" name="memNo" value="${cgVO.mem_no}">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <!--送出本網頁的路徑給Controller-->
                    <input type="hidden" name="action" value="cgmdelete"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>


<table border='1' bordercolor='#CCCCFF' width='800'>
    <h1>所有群組</h1>

    <tr>
        <th>群組編號</th>
        <th>會員編號</th>
        <th>刪除</th>
    </tr>

    <c:forEach var="cgVO" items="${cgSvc.all}">
        <tr align='center' valign='middle' ${(cgVO.cg_no==param.cg_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
            <td>${cgVO.cg_no}</td>
            <td>${cgVO.cg_name}</td>

            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do">
                    <input type="submit" value="新增">
                    <input type="hidden" name="cgNo" value="${cgVO.cg_no}">
                    <input type="hidden" name="memNo" value="${memNo}">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <!--送出本網頁的路徑給Controller-->
                    <input type="hidden" name="action" value="cgmInsert">
                </FORM>
            </td>
        </tr>
    </c:forEach>
</table>


<div class="container text-center">
    <div class="row">

        <div class="round OpenChat text-center">
            <a href="#" id="addClass"><span class="glyphicon glyphicon-comment"></span></a>
        </div>

    </div>
</div>


<div class="chat-box chat-popup" id="chatlist">
    <div class="popup-head">
        <div class="popup-head-left pull-left"><img
                src="http://bootsnipp.com/img/avatars/bcf1c0d13e5500875fdd5a7e8ad9752ee16e7462.jpg"
                alt="iamgurdeeposahan"> ${memSvc.getOneMember(memNo).mem_name}
        </div>
        <div class="popup-head-right pull-right">

            <button data-widget="remove" id="removeClass" class="chat-header-button pull-right" type="button">
                <i class="glyphicon glyphicon-off"></i>
            </button>
        </div>
    </div>
    <div class="chat-friend">


        <div class="direct-chat-messages">
            <%--顯示會員群組列表--%>
            <c:forEach var="cgVO" items="${cglsit}">
                <div class="chat-list doted-border">
                    <div class="chat-list-info clearfix">
                    <span class="direct-chat-name pull-left">
                <c:forEach var="cg1VO" items="${cgSvc.all}">
                        <c:if test="${cgVO.cg_no==cg1VO.cg_no}">
                            ${cg1VO.cg_name}
                        </c:if>
                </c:forEach>
                    </span>
                        <button>xxx</button>
                    </div>
                    <!-- /.chat-list-info -->
                    <img alt="message user image"
                         src="../pic/cat01.jpg"
                         class="direct-chat-img"><!-- /.direct-chat-img -->
                </div>
            </c:forEach>

            <%--顯示會員群組列表--%>

            <%--顯示會員好友列表--%>
            <c:forEach var="cflsit" items="${list}">

                <%--判斷好友是否顯示--%>
                <c:if test="${cflsit.cf_is_del==0}">

                    <div class="chat-list doted-border">
                        <div class="chat-list-info clearfix">
                        <span class="direct-chat-name pull-left">
                            <c:forEach var="memVO" items="${memSvc.all}">
                                <c:if test="${cflsit.mem_no_o==memVO.mem_no}">
                                    ${memVO.mem_name}
                                </c:if>
                            </c:forEach>
                        </span>
                            <button>xxx</button>
                        </div>
                        <!-- /.chat-list-info -->
                        <img alt="message user image"
                             src="http://bootsnipp.com/img/avatars/bcf1c0d13e5500875fdd5a7e8ad9752ee16e7462.jpg"
                             class="direct-chat-img"><!-- /.direct-chat-img -->
                    </div>

                </c:if>
                <%--end 判斷好友是否顯示--%>
            </c:forEach>
            <%--end 顯示會員好友列表--%>
        </div>

    </div>

</div>

</body>


<script>
    $(function () {
        $("#addClass").click(function () {
            $('#chatlist').addClass('chat-box-on');
        });

        $("#removeClass").click(function () {
            $('#chatlist').removeClass('chat-box-on');
        });
    })
</script>
<style>

    @import url(https://fonts.googleapis.com/css?family=Oswald:400,300);
    @import url(https://fonts.googleapis.com/css?family=Open+Sans);

    body {
        font-family: 'Open Sans', sans-serif;
    }

    .chat-box {
        background-color: #ffffff;
        border: 1px solid #b0b0b0;
        bottom: 0;
        display: none;
        height: 100%;
        position: fixed;
        right: 10px;
        width: 300px;
        font-family: 'Open Sans', sans-serif;
    }

    .round.OpenChat a {
        border: 2px solid #ff6701;
        border-radius: 35px;
        color: red;
        color: #ff6701;
        font-size: 1px;
        padding: 7px 10px;
        text-decoration: none;
        font-family: 'Open Sans', sans-serif;
        position: fixed;
        bottom: 5px;
        right: 10px;
    }

    .round.OpenChat a:hover {
        border: 2px solid #000;
        border-radius: 35px;
        color: red;
        color: #000;
        font-size: 23px;
        padding: 10px 21px;
        text-decoration: none;
    }

    .chat-box-on {
        display: block !important;
    }

    .chat-box .popup-head {
        background-color: #fff;
        clear: both;
        color: #7b7b7b;
        display: inline-table;
        font-size: 21px;
        padding: 7px 10px;
        width: 100%;
        font-family: Oswald;
    }

    .bg_none i {
        border: 1px solid #ff6701;
        border-radius: 25px;
        color: #ff6701;
        font-size: 17px;
        height: 33px;
        line-height: 30px;
        width: 33px;
    }

    .bg_none:hover i {
        border: 1px solid #000;
        border-radius: 25px;
        color: #000;
        font-size: 17px;
        height: 33px;
        line-height: 30px;
        width: 33px;
    }

    .chat-box .popup-head .popup-head-right {
        margin: 11px 7px 0;
    }

    .popup-head-left img {
        border: 1px solid #7b7b7b;
        border-radius: 50%;
        width: 44px;
    }

    .popup-messages-footer > textarea {
        border-bottom: 1px solid #b2b2b2 !important;
        height: 34px !important;
        margin: 7px;
        padding: 5px !important;
        border: medium none;
        width: 95% !important;
    }

    .chat-box .chat-friend {
        background: #3f9684 none repeat scroll 0 0;
        height: 90%;
        overflow: auto;
    }

    .direct-chat-messages {
        overflow: auto;
        padding: 10px;
        transform: translate(0px, 0px);

    }

    .chat-friend {
        border-bottom: 1px solid #a4c6b5;
        height: 12px;
        margin: 7px 0 20px;
        position: relative;
        text-align: center;
    }

    .chat-friend abbr.timestamp {
        background: #3f9684 none repeat scroll 0 0;
        color: #fff;
        padding: 0 11px;
    }

    .popup-head-right .btn-group {
        display: inline-flex;
        margin: 0 8px 0 0;
        vertical-align: top !important;
    }

    .chat-header-button {
        background: transparent none repeat scroll 0 0;
        border: 1px solid #636364;
        border-radius: 50%;
        font-size: 14px;
        height: 30px;
        width: 30px;
    }

    .popup-head-right .btn-group {
        border: medium none;
        min-width: 122px;
        padding: 0;
    }

    .popup-head-right .btn-group .dropdown-menu li a {
        font-size: 12px;
        padding: 3px 10px;
        color: #303030;
    }

    .chat-friend {
        background: #3f9684 none repeat scroll 0 0;
        border-bottom: 1px solid #a4c6b5;
        height: 12px;
        margin: 7px 0 20px;
        position: relative;
        text-align: center;
        color: #6957ff;
        margin: 10px 0 0 !important;
        padding: 0 11px;
    }

    .chat-friend .direct-chat-messages {
        height: auto;
    }

    .chat-friend {
        background: #dfece7 none repeat scroll 0 0;
        border: 1px solid #dfece7;
        border-radius: 2px;
        color: #1f2121;
    }

    .chat-friend .direct-chat-name {
        font-size: 15px;
        font-weight: 600;
        margin: 0 0 0 49px !important;
        color: #fff;
        opacity: 0.9;
    }

    .chat-friend .chat-list-info {
        display: block;
        font-size: 12px;
        margin-bottom: 0;
    }

    .chat-friend .direct-chat-img {
        border: 1px solid #fff;
        background: #3f9684 none repeat scroll 0 0;
        border-radius: 50%;
        float: left;
        height: 40px;
        margin: -31px 0 0; /*????矽 蝵桐葉*/
        width: 40px;
    }

    .chat-friend .chat-list {
        margin-bottom: 30px;
        position: relative;
    }

    .chat-friend .doted-border::after {
        background: transparent none repeat scroll 0 0 !important;
        bottom: 0;
        content: "";
        left: 17px;
        margin: 0;
        position: absolute;
        top: 0;
        width: 2px;
        display: inline;
        z-index: -2;
    }

    .chat-friend .chat-list::after {
        background: #fff none repeat scroll 0 0;
        border-right: medium none;
        bottom: 0;
        content: "";
        left: 17px;
        margin: 0;
        position: absolute;
        top: 0;
        width: 2px;
        display: inline;
        z-index: -2;
    }

    .chat-friend {
        background: #dfece7 none repeat scroll 0 0;
        border: 1px solid #dfece7;
        border-radius: 2px;
        color: #1f2121;
    }

</style>


<br>本網頁的路徑:<br><b>
    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%>
</b>
</body>
</html>
