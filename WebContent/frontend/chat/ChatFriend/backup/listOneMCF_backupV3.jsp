<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.model.*" %>
<%@ page import="com.member.model.*" %>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
    List<Chat_FriendVO> list = (List<Chat_FriendVO>) request.getAttribute("oneMemCF");
    pageContext.setAttribute("list", list);
%>

<%-- 取出 對應的DeptVO物件--%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>

<html>
<head>
    <title>群組資料 - listOneMCF.jsp</title>

    <!-- bootstrapcdn 3.37 Latest compiled and minified CSS  Optional theme-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <!-- bootstrapcdn 3.37 Latest compiled and minified CSS  Optional theme-->
    <!-- bootstrapcdn Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- bootstrapcdn Latest compiled and minified JavaScript -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../../../css/babeq.css">

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
                <FORM METHOD="post"
                      ACTION="<%=request.getContextPath()%>/chat/ChatFriend/Chat_FriendServlet.do">  <%--QQ--%>
                    <input type="hidden" name="cf_no" value="${cflsit.cf_no}">
                    <input type="hidden" name="memNoS" value="${cflsit.mem_no_s}">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="cfdel" value="1">
                    <input type="submit" value="刪除好友">
                </FORM>
            </td>
        </tr>
    </c:forEach>
</table>


<div class="container text-center">
    <div class="row">

        <div class="round hollow text-center">
            <a href="#" id="addClass"><span class="glyphicon glyphicon-comment"></span> Open in chat </a>
        </div>

    </div>
</div>


<div class="popup-box chat-popup" id="qnimate">
    <div class="popup-head">
        <div class="popup-head-left pull-left"><img
                src="http://bootsnipp.com/img/avatars/bcf1c0d13e5500875fdd5a7e8ad9752ee16e7462.jpg"
                alt="iamgurdeeposahan"> Gurdeep Osahan
        </div>
        <div class="popup-head-right pull-right">

            <button data-widget="remove" id="removeClass" class="chat-header-button pull-right" type="button">
                <i class="glyphicon glyphicon-off"></i>
            </button>
        </div>
    </div>
    <div class="popup-messages">


        <div class="direct-chat-messages">

            <c:forEach var="cflsit" items="${list}">

                <div class="direct-chat-msg doted-border">
                    <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">
                            <c:forEach var="memVO" items="${memSvc.all}">
                                <c:if test="${cflsit.mem_no_o==memVO.mem_no}">
                                    ${memVO.mem_name}
                                </c:if>
                            </c:forEach>
                        </span>
                        <button>
                            open
                        </button>
                    </div>
                    <!-- /.direct-chat-info -->
                    <img alt="message user image"
                         src="http://bootsnipp.com/img/avatars/bcf1c0d13e5500875fdd5a7e8ad9752ee16e7462.jpg"
                         class="direct-chat-img"><!-- /.direct-chat-img -->

                </div>
            </c:forEach>
        </div>

    </div>

</div>

</body>
<style>
    @import url(https://fonts.googleapis.com/css?family=Oswald:400,300);
    @import url(https://fonts.googleapis.com/css?family=Open+Sans);

    body {
        font-family: 'Open Sans', sans-serif;
    }

    .popup-box {
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

    .round.hollow a {
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

    .round.hollow a:hover {
        border: 2px solid #000;
        border-radius: 35px;
        color: red;
        color: #000;
        font-size: 23px;
        padding: 10px 21px;
        text-decoration: none;
    }

    .popup-box-on {
        display: block !important;
    }

    .popup-box .popup-head {
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

    .popup-box .popup-head .popup-head-right {
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

    .popup-box .popup-messages {
        background: #3f9684 none repeat scroll 0 0;
        height: 90%;
        overflow: auto;
    }

    .direct-chat-messages {
        overflow: auto;
        padding: 10px;
        transform: translate(0px, 0px);

    }

    .popup-messages {
        border-bottom: 1px solid #a4c6b5;
        height: 12px;
        margin: 7px 0 20px;
        position: relative;
        text-align: center;
    }

    .popup-messages abbr.timestamp {
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

    .popup-messages {
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

    .popup-messages .direct-chat-messages {
        height: auto;
    }

    .popup-messages {
        background: #dfece7 none repeat scroll 0 0;
        border: 1px solid #dfece7;
        border-radius: 2px;
        color: #1f2121;
    }

    .popup-messages .direct-chat-name {
        font-size: 15px;
        font-weight: 600;
        margin: 0 0 0 49px !important;
        color: #fff;
        opacity: 0.9;
    }

    .popup-messages .direct-chat-info {
        display: block;
        font-size: 12px;
        margin-bottom: 0;
    }

    .popup-messages .direct-chat-img {
        border: 1px solid #fff;
        background: #3f9684 none repeat scroll 0 0;
        border-radius: 50%;
        float: left;
        height: 40px;
        margin: -31px 0 0; /*這個調 置中*/
        width: 40px;
    }

    .popup-messages .direct-chat-msg {
        margin-bottom: 30px;
        position: relative;
    }

    .popup-messages .doted-border::after {
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

    .popup-messages .direct-chat-msg::after {
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

    .popup-messages {
        background: #dfece7 none repeat scroll 0 0;
        border: 1px solid #dfece7;
        border-radius: 2px;
        color: #1f2121;
    }

</style>

<script>
    $(function () {
        $("#addClass").click(function () {
            $('#qnimate').addClass('popup-box-on');
        });

        $("#removeClass").click(function () {
            $('#qnimate').removeClass('popup-box-on');
        });
    })
</script>
</html>
