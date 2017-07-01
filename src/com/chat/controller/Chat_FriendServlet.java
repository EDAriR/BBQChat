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


        if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
System.out.println("getOne_For_Display \"action\" in chat_Friend C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String str = req.getParameter("cf_no");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("�п�J�n�ͽs��");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                String cf_no = null;
                try {
                    cf_no = new String(str);
                } catch (Exception e) {
                    errorMsgs.add("�s���榡�����T");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/�s���榡�����Tselect_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************2.�}�l�d�߸��*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = cfSvc.getOneChat_Friend(cf_no);
                if (chat_FriendVO == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/== nullselect_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("chat_FriendVO", chat_FriendVO); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/frontend/chat/ChatFriend/listOneChat_Friend.jsp";
                System.out.println("chat_FriendVO: " +chat_FriendVO +"\n" +"url: " + url);
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/chat/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getCF_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
System.out.println("getCF_For_Display \"action\" in chat_Friend C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String memNoS = req.getParameter("memNoS");

                /***************************2.�}�l�d�߸��*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(memNoS);
                System.out.println("cfSvc:" +cfSvc + "\n" + "oneMemCF:" +oneMemCF);
                if (oneMemCF == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("oneMemCF", oneMemCF); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/frontend/chat/ChatFriend/listOneMCF.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/chat/select_page.jsp");
                failureView.forward(req, res);
            }
        }

/*************************Chat_Group****************************************/
        if ("getOneCG_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String cgno = req.getParameter("cgno");

                /***************************2.�}�l�d�߸��*****************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                Chat_GroupVO chatGroupVO = cgSvc.getOneCG(cgno);
                if (chatGroupVO == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }
                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("chatGroupVO", chatGroupVO); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/chat/Chat_Group/listOneCG.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/select_page.jsp");
                failureView.forward(req, res);
            }
        }
/*************************Chat_Group****************************************/

        if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ�****************************************/
                String cf_no = new String(req.getParameter("cf_no"));
System.out.println("getOne_For_Update in C :" + cf_no);

                /***************************2.�}�l�d�߸��****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = chat_FriendSvc.getOneChat_Friend(cf_no);

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
                req.setAttribute("chat_FriendVO", chat_FriendVO);         // ��Ʈw���X��chat_FriendVO����,�s�Jreq
                String url = "/chat/ChatFriend/update_cf_input0403.jsp";
//                String url = "/chat/ChatFriend/update_chat_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
System.out.println("chat_FriendVO: "+chat_FriendVO+"\n"+"req:" + req +"\n"+"res: " + res );
System.out.println("successView: "+successView);
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/listAllC");
                failureView.forward(req, res);
            }
        }


        if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
System.out.println("action:" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
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
                    req.setAttribute("chat_FriendVO", chat_FriendVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/chat/update_chat_input.jsp"); /*QQ*/
                    failureView.forward(req, res);
                    return; //�{�����_
                }

                /***************************2.�}�l�ק���*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                cfSvc.updateChat_Friend(cf_no, cfdel);
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(memNoS);
                System.out.println("oneMemCF: " + oneMemCF + "\n" + "memNoS: " + memNoS);

                /***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
                req.setAttribute("oneMemCF", oneMemCF); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
                String url = "/frontend/chat/ChatFriend/listOneMCF.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�ק��ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/ChatFriend/update_chat_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
System.out.println("action in Chat_FrienServlet.do:" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);


            /***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
            String mem_no_s = req.getParameter("mem_no_s");
            String mem_no_o = req.getParameter("mem_no_o");
            String cfdel = req.getParameter("cfdel");

            Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
            chat_FriendVO.setMem_no_s(mem_no_s);
            chat_FriendVO.setMem_no_o(mem_no_o);
            chat_FriendVO.setCf_is_del(cfdel);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("chat_FriendVOpVO", chat_FriendVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/addEmp.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.�}�l�s�W���***************************************/
            Chat_FriendService chat_FriendSvc = new Chat_FriendService();
            chat_FriendVO = chat_FriendSvc.addChat_Friend(mem_no_s, mem_no_o, cfdel);

            /***************************3.�s�W����,�ǳ����(Send the Success view)***********/
            String url = "/frontend/chat/ChatFriend/listCF0403.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
            successView.forward(req, res);
            /***************************��L�i�઺���~�B�z**********************************/
        }


        if ("delete".equals(action)) { // �Ӧ�listAllChat_Friend.jsp

        }
    }
}
