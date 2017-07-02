package com.chat.controller;

import com.chat.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/ChatServlet.do")
public class ChatServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOneMemCFCG".equals(action)) {

            System.out.println("getOneMemCFCG \"action\" in C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            HttpSession session = req.getSession();

            try {
                /****************************
                 * 1.接收請求參數 - 輸入格式的錯誤處理
                 **********************/
                String memNo = req.getParameter("memNo");
                session.setAttribute("memNo", memNo);

                /*************************** 2.開始查詢資料 *****************************************/

                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                List<Chat_Group_ItemVO> cglsit = cgiSvc.getOneChat_Group_Mem(memNo);
                if (cglsit == null) {
                    errorMsgs.add("查無資料");
                }
                Chat_FriendService cfSvc = new Chat_FriendService();
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(memNo);
                if (oneMemCF == null) {
                    errorMsgs.add("查無資料");
                }
                System.out.println("oneMemCF:" + oneMemCF.size());
                System.out.println("cglsit:" + cglsit.size());

                /***************************
                 * 3.查詢完成,準備轉交(Send the Success view)
                 *************/
                req.setAttribute("cglsit", cglsit);
                req.setAttribute("oneMemCF", oneMemCF);
                System.out.println("cglsit in listCGs_ByMemNo: " + cglsit.size());
                String url = "/frontend/chat/listCGsCFs_ByMemNo.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 *************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
                failureView.forward(req, res);
            }
        }
    }
}
