package com.chat.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chat.model.Chat_GroupService;
import com.chat.model.Chat_GroupVO;
import com.chat.model.Chat_Group_ItemService;

@WebServlet("/Chat_GroupServlet.do")
public class Chat_GroupServlet extends HttpServlet {

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
				String str = req.getParameter("cg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�s�սs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/listtAllCG.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String cg_no = null;
				try {
					cg_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�s�սs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/listtAllCG.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Chat_GroupService cgSvc = new Chat_GroupService  ();
				Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);
				if (chat_GroupVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/listtAllCG.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("chat_GroupVO", chat_GroupVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/chat/Chat_Group/listOneCG.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/listtAllCG.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j		
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String cg_no = new String(req.getParameter("cg_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Chat_GroupService   cgSvc = new Chat_GroupService();
				Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("chat_GroupVO", chat_GroupVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/chat/update_CG_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j �� �i /emp/listEmps_ByCompositeQuery.jsp�j
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String cg_no = new String(req.getParameter("cg_no").trim());
				String ename = req.getParameter("ename").trim();
				String job = req.getParameter("job").trim();		
						
				String deptno = new String(req.getParameter("deptno").trim());

				Chat_GroupVO empVO = new Chat_GroupVO();
				empVO.setEmpno(empno);
				empVO.setEname(ename);
				empVO.setJob(job);
				empVO.setHiredate(hiredate);
				empVO.setSal(sal);
				empVO.setComm(comm);
				empVO.setDeptno(deptno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Chat_Group_ItemService cgSvc = new Chat_Group_ItemService();
				chat_GroupVO = cgSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/				
				Chat_Group_ItemService deptSvc = new Chat_Group_ItemService();
				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/chat/Chat_group/listtAllCG.jsp"))
					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(deptno)); // ��Ʈw���X��list����,�s�Jrequest
				
				if(requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<chat_GroupVO> list  = cgSvc.getAll(map);
					req.setAttribute("listEmps_ByCompositeQuery",list); //  �ƦX�d��, ��Ʈw���X��list����,�s�Jrequest
				}
                
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
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

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String ename = req.getParameter("ename").trim();
				String job = req.getParameter("job").trim();
				
				java.sql.Date hiredate = null;
								
				Double sal = null;
				try {
					sal = new Double(req.getParameter("sal").trim());
				} catch (NumberFormatException e) {
					sal = 0.0;
					errorMsgs.add("�п�J�s�զW��.");
				}
				
				Double comm = null;
				try {
					comm = new Double(req.getParameter("comm").trim());
				} catch (NumberFormatException e) {
					comm = 0.0;
					errorMsgs.add("�����ж�Ʀr.");
				}
				
				Integer deptno = new Integer(req.getParameter("deptno").trim());

				Chat_GroupVO chat_GroupVO = new Chat_GroupVO();
				chat_GroupVO.setEname(ename);
				chat_GroupVO.setJob(job);
				chat_GroupVO.setHiredate(hiredate);
				chat_GroupVO.setSal(sal);
				chat_GroupVO.setComm(comm);
				chat_GroupVO.setDeptno(deptno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chat_GroupVO", chat_GroupVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Chat_GroupService empSvc = new Chat_GroupService();
				chat_GroupVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j �� �i /emp/listEmps_ByCompositeQuery.jsp�j

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String cg_no = new String(req.getParameter("cg_no"));
				
				/***************************2.�}�l�R�����***************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
				empSvc.deleteEmp(empno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				DeptService deptSvc = new DeptService();
				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // ��Ʈw���X��list����,�s�Jrequest
				
				if(requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<EmpVO> list  = empSvc.getAll(map);
					req.setAttribute("listEmps_ByCompositeQuery",list); //  �ƦX�d��, ��Ʈw���X��list����,�s�Jrequest
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listEmps_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.�}�l�ƦX�d��***************************************/
				Chat_GroupService cgSvc = new Chat_GroupService ();
				List<Chat_GroupVO> list  = cgSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
