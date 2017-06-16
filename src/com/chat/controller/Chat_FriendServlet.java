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


        if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

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
                            .getRequestDispatcher("/chat/select_page.jsp");
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
                            .getRequestDispatcher("/chat/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************2.�}�l�d�߸��*****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = chat_FriendSvc.getOneCF(cf_no);
                if (chat_FriendVO == null) {
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
                req.setAttribute("chat_FriendVO", chat_FriendVO); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/chat/listOneChat_Friend.jsp";
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

        if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ�****************************************/
                String cf_no = new String(req.getParameter("cf_no"));

                /***************************2.�}�l�d�߸��****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO chat_FriendVO = chat_FriendSvc.getOneCF(cf_no);

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
                req.setAttribute("chat_FriendVO", chat_FriendVO);         // ��Ʈw���X��chat_FriendVO����,�s�Jreq
                String url = "/emp/update_emp_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
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

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String cf_no = new String(req.getParameter("cf_no").trim());
                String mem_no_s = req.getParameter("mem_no_s").trim();
                String mem_no_o = req.getParameter("mem_no_o").trim();
                String cf_is_del = req.getParameter("cf_is_del").trim();

                Integer del = null;
                try {
                    del = new Integer(req.getParameter("cf_is_del").trim());
                } catch (NumberFormatException e) {
                    del = 0;
                    errorMsgs.add("�ж�Ʀr 0 or 1");
                }

                Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(cf_no);
                chat_FriendVO.setMem_no_s(mem_no_s);
                chat_FriendVO.setMem_no_o(mem_no_o);
                chat_FriendVO.setCf_is_del(cf_is_del);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("chat_FriendVO", chat_FriendVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/chat/update_chat_input.jsp"); /*QQ*/
                    failureView.forward(req, res);
                    return; //�{�����_
                }

                /***************************2.�}�l�ק���*****************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                chat_FriendVO = chat_FriendSvc.updateChat_Friend(cf_no,
                        mem_no_s, mem_no_o, cf_is_del);

                /***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
                req.setAttribute("chat_FriendVO", chat_FriendVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
                String url = "/chat/listOneChat_Friend.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�ק��ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/emp/update_emp_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);


            /***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
                req.setAttribute("chat_FriendVOpVO", chat_FriendVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/addEmp.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.�}�l�s�W���***************************************/
            Chat_FriendService chat_FriendSvc = new Chat_FriendService();
            chat_FriendVO = chat_FriendSvc.addChat_Friend(cf_no, mem_no_s, mem_no_o, cf_is_del);

            /***************************3.�s�W����,�ǳ����(Send the Success view)***********/
            String url = "/chat/listAllChat_Friend.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
            successView.forward(req, res);
            /***************************��L�i�઺���~�B�z**********************************/
        }


        if ("delete".equals(action)) { // �Ӧ�listAllChat_Friend.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.�����ШD�Ѽ�***************************************/
                String cf_no = new String(req.getParameter("cf_no"));

                /***************************2.�}�l�R�����***************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                chat_FriendSvc.deleteCF(cf_no);

                /***************************3.�R������,�ǳ����(Send the Success view)***********/
                String url = "/emp/listAllEmp.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add("�R����ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/chat/listAllChat_Friend.jsp");
                failureView.forward(req, res);
            }
        }
    }
}
