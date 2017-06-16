package com.chat.controller;

import com.chat.model.Chat_FriendService;
import com.chat.model.Chat_FriendVO;

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
                            .getRequestDispatcher("/chat/select_page.jsp");
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
                            .getRequestDispatcher("/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = chat_FriendSvc.getOneCF(cf_no);
                if (chat_FriendVO == null) {
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
                req.setAttribute("chat_FriendVO", chat_FriendVO); // 資料庫取出的empVO物件,存入req
                String url = "/chat/listOneChat_Friend.jsp";
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

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數****************************************/
                String cf_no = new String(req.getParameter("cf_no"));

                /***************************2.開始查詢資料****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = chat_FriendSvc.getOneCF(cf_no);

                /***************************3.查詢完成,準備轉交(Send the Success view)************/
                req.setAttribute("chat_FriendVO", chat_FriendVO);         // 資料庫取出的chat_FriendVO物件,存入req
                String url = "/emp/update_emp_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
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

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String cf_no = new String(req.getParameter("cf_no").trim());
                String mem_no_s = req.getParameter("mem_no_s").trim();
                String mem_no_o = req.getParameter("mem_no_o").trim();
                String cf_is_del = req.getParameter("cf_is_del").trim();

                Integer del = null;
                try {
                    del = new Integer(req.getParameter("cf_is_del").trim());
                } catch (NumberFormatException e) {
                    del = 0;
                    errorMsgs.add("請填數字 0 or 1");
                }

                Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(cf_no);
                chat_FriendVO.setMem_no_s(mem_no_s);
                chat_FriendVO.setMem_no_o(mem_no_o);
                chat_FriendVO.setCf_is_del(cf_is_del);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("chat_FriendVO", chat_FriendVO); // 含有輸入格式錯誤的empVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/chat/update_chat_input.jsp"); /*QQ*/
                    failureView.forward(req, res);
                    return; //程式中斷
                }

                /***************************2.開始修改資料*****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                chat_FriendVO = chat_FriendSvc.updateChat_Friend(cf_no,
                        mem_no_s, mem_no_o, cf_is_del);

                /***************************3.修改完成,準備轉交(Send the Success view)*************/
                req.setAttribute("chat_FriendVO", chat_FriendVO); // 資料庫update成功後,正確的的empVO物件,存入req
                String url = "/chat/listOneChat_Friend.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("修改資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/emp/update_emp_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);


            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            String cf_no = req.getParameter("cf_no").trim();
            String mem_no_s = req.getParameter("mem_no_s").trim();
            String mem_no_o = req.getParameter("mem_no_o").trim();
            String cf_is_del = "0";

            Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
            chat_FriendVO.setCf_no(cf_no);
            chat_FriendVO.setMem_no_s(mem_no_s);
            chat_FriendVO.setMem_no_o(mem_no_o);
            chat_FriendVO.setCf_is_del(cf_is_del);

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
            chat_FriendVO = chat_FriendSvc.addChat_Friend(cf_no, mem_no_s, mem_no_o, cf_is_del);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/chat/listAllChat_Friend.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
            /***************************其他可能的錯誤處理**********************************/
        }


        if ("delete".equals(action)) { // 來自listAllChat_Friend.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數***************************************/
                String cf_no = new String(req.getParameter("cf_no"));

                /***************************2.開始刪除資料***************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                chat_FriendSvc.deleteCF(cf_no);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/
                String url = "/emp/listAllEmp.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
                successView.forward(req, res);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/listAllChat_Friend.jsp");
                failureView.forward(req, res);
            }
        }
    }
}
