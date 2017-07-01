package com.chat.controller;

import com.chat.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Chat_FrienServlet.do")
public class Chat_FriendServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
System.out.println("getOne_For_Display \"action\" in chat_Friend C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String str = req.getParameter("cf_no");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("請輸入好友編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                String cf_no = null;
                try {
                    cf_no = new String(str);
                } catch (Exception e) {
                    errorMsgs.add("編號格式不正確");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/編號格式不正確select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = cfSvc.getOneChat_Friend(cf_no);
                if (chat_FriendVO == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/== nullselect_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("chat_FriendVO", chat_FriendVO); // 資料庫取出的empVO物件,存入req
                String url = "/frontend/chat/ChatFriend/listOneChat_Friend.jsp";
                System.out.println("chat_FriendVO: " +chat_FriendVO +"\n" +"url: " + url);
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/chat/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getCF_For_Display".equals(action)) { // 來自select_page.jsp的請求
System.out.println("getCF_For_Display \"action\" in chat_Friend C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String memNoS = req.getParameter("memNoS");

                /***************************2.開始查詢資料*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(memNoS);
                System.out.println("cfSvc:" +cfSvc + "\n" + "oneMemCF:" +oneMemCF);
                if (oneMemCF == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("oneMemCF", oneMemCF); // 資料庫取出的empVO物件,存入req
                String url = "/frontend/chat/ChatFriend/listOneMCF.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/chat/select_page.jsp");
                failureView.forward(req, res);
            }
        }

/*************************Chat_Group****************************************/
        if ("getOneCG_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String cgno = req.getParameter("cgno");

                /***************************2.開始查詢資料*****************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                Chat_GroupVO chatGroupVO = cgSvc.getOneCG(cgno);
                if (chatGroupVO == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("chatGroupVO", chatGroupVO); // 資料庫取出的empVO物件,存入req
                String url = "/chat/Chat_Group/listOneCG.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/select_page.jsp");
                failureView.forward(req, res);
            }
        }
/*************************Chat_Group****************************************/

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數****************************************/
                String cf_no = new String(req.getParameter("cf_no"));
System.out.println("getOne_For_Update in C :" + cf_no);

                /***************************2.開始查詢資料****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = chat_FriendSvc.getOneChat_Friend(cf_no);

                /***************************3.查詢完成,準備轉交(Send the Success view)************/
                req.setAttribute("chat_FriendVO", chat_FriendVO);         // 資料庫取出的chat_FriendVO物件,存入req
                String url = "/chat/ChatFriend/update_cf_input0403.jsp";
//                String url = "/chat/ChatFriend/update_chat_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
System.out.println("chat_FriendVO: "+chat_FriendVO+"\n"+"req:" + req +"\n"+"res: " + res );
System.out.println("successView: "+successView);
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/listAllC");
                failureView.forward(req, res);
            }
        }


        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
System.out.println("action:" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String cf_no = req.getParameter("cf_no");
                String cfdel = req.getParameter("cfdel");
                String memNoS = req.getParameter("memNoS");

System.out.println("cf_no:" + cf_no);
System.out.println("cfdel:" + cfdel);

                Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(cf_no);
                chat_FriendVO.setCf_is_del(cfdel);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("chat_FriendVO", chat_FriendVO); // 含有輸入格式錯誤的empVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/update_chat_input.jsp"); /*QQ*/
                    failureView.forward(req, res);
                    return; //程式中斷
                }

                /***************************2.開始修改資料*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                cfSvc.updateChat_Friend(cf_no, cfdel);
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(memNoS);
                System.out.println("oneMemCF: " + oneMemCF + "\n" + "memNoS: " + memNoS);

                /***************************3.修改完成,準備轉交(Send the Success view)*************/
                req.setAttribute("oneMemCF", oneMemCF); // 資料庫update成功後,正確的的empVO物件,存入req
                String url = "/frontend/chat/ChatFriend/listOneMCF.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("修改資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/ChatFriend/update_chat_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
System.out.println("action in Chat_FrienServlet.do:" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);


            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            String mem_no_s = req.getParameter("mem_no_s");
            String mem_no_o = req.getParameter("mem_no_o");
            String cfdel = req.getParameter("cfdel");

            Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
            chat_FriendVO.setMem_no_s(mem_no_s);
            chat_FriendVO.setMem_no_o(mem_no_o);
            chat_FriendVO.setCf_is_del(cfdel);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("chat_FriendVOpVO", chat_FriendVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/addEmp.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.開始新增資料***************************************/
            Chat_FriendService chat_FriendSvc = new Chat_FriendService();
            chat_FriendVO = chat_FriendSvc.addChat_Friend(mem_no_s, mem_no_o, cfdel);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/frontend/chat/ChatFriend/listCF0403.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
            /***************************其他可能的錯誤處理**********************************/
        }


        if ("delete".equals(action)) { // 來自listAllChat_Friend.jsp

        }
    }
}
