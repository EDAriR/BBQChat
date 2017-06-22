package com.chat.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chat.model.Chat_GroupService;
import com.chat.model.Chat_GroupVO;
import com.chat.model.Chat_Group_ItemService;

@WebServlet("/Chat_GroupServlet.do")
public class Chat_GroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("cg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("群組編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String cg_no = null;
				try {
					cg_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("群組編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Chat_GroupService cgSvc = new Chat_GroupService();
				Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);
				if (chat_GroupVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("chat_GroupVO", chat_GroupVO); // 資料庫取出的empVO物件,存入req
				String url = "/chat/Chat_Group/listOneCG.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或
													// /dept/listEmps_ByDeptno.jsp
													// 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String cg_no = new String(req.getParameter("cg_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Chat_GroupService cgSvc = new Chat_GroupService();
				Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("chat_GroupVO", chat_GroupVO); // 資料庫取出的empVO物件,存入req
				String url = "/chat/update_CG_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】
																// 或 【
																// /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String cg_no = req.getParameter("cg_no").trim();
				String cg_name = req.getParameter("cg_name").trim();
				Date cg_year = req.getParameter("cg_year").trim();
				String cg_is_ar = req.getParameter("cg_is_ar").trim();
				String cg_is_ab = req.getParameter("cg_is_ab").trim();
				String cg_is_ac = req.getParameter("cg_is_ac").trim();
				String cg_is_sf = req.getParameter("cg_is_sf").trim();
				String cg_is_ad = req.getParameter("cg_is_ad").trim();
				String cg_baby_rd = req.getParameter("cg_baby_rd").trim();

				String mem_no = new String(req.getParameter("mem_no").trim());

				Chat_GroupVO chat_GroupVO = new Chat_GroupVO();
				chat_GroupVO.setCg_no(cg_no);
				chat_GroupVO.setCg_name(cg_name);
				chat_GroupVO.setCg_year(cg_year);
				chat_GroupVO.setCg_is_ar(cg_is_ar);
				chat_GroupVO.setCg_is_ab(cg_is_ab);
				chat_GroupVO.setCg_is_ac(cg_is_ac);
				chat_GroupVO.setCg_is_sf(cg_is_sf);
				chat_GroupVO.setCg_is_ad(cg_is_ad);
				chat_GroupVO.setCg_baby_rd(cg_baby_rd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chat_GroupVO", chat_GroupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Chat_GroupService cgSvc = new Chat_GroupService();
				chat_GroupVO = cgSvc.updateCG(cg_no, cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad,
						cg_baby_rd);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
				if (requestURL.equals("/chat/Chat_Group/listCGs_Bycg_no.jsp")
						|| requestURL.equals("/chat/Chat_group/listtAllCG.jsp"))
					req.setAttribute("listEmps_ByDeptno", cgiSvc.getOneChat_Group_Mem(mem_no)); // 資料庫取出的list物件,存入request

				if (requestURL.equals("/chat/Chat_group/listEmps_ByCompositeQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<Chat_GroupVO> list = cgSvc.getAll(map);
					req.setAttribute("listEmps_ByCompositeQuery", list); // 複合查詢,
																			// 資料庫取出的list物件,存入request
				}

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/chat/Chat_group/update_CG_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String cg_name = req.getParameter("cg_name").trim();
				Date cg_year = req.getParameter("cg_year").trim();
				String cg_is_ar = req.getParameter("cg_is_ar").trim();
				String cg_is_ab = req.getParameter("cg_is_ab").trim();
				String cg_is_ac = req.getParameter("cg_is_ac").trim();
				String cg_is_sf = req.getParameter("cg_is_sf").trim();
				String cg_is_ad = req.getParameter("cg_is_ad").trim();
				String cg_baby_rd = req.getParameter("cg_baby_rd").trim();

				java.sql.Date hiredate = null;

				Double sal = null;
				try {
					sal = new Double(req.getParameter("cg_name").trim());
				} catch (NumberFormatException e) {
					sal = 0.0;
					errorMsgs.add("請輸入群組名稱.");
				}

				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("cg_year").trim());
				} catch (IllegalArgumentException e) {
					hiredate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Chat_GroupVO chat_GroupVO = new Chat_GroupVO();
				chat_GroupVO.setCg_name(cg_name);
				chat_GroupVO.setCg_year(cg_year);
				chat_GroupVO.setCg_is_ar(cg_is_ar);
				chat_GroupVO.setCg_is_ab(cg_is_ab);
				chat_GroupVO.setCg_is_ac(cg_is_ac);
				chat_GroupVO.setCg_is_sf(cg_is_sf);
				chat_GroupVO.setCg_is_ad(cg_is_ad);
				chat_GroupVO.setCg_baby_rd(cg_baby_rd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chat_GroupVO", chat_GroupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Chat_GroupService cgSvc = new Chat_GroupService();
				chat_GroupVO = cgSvc.addCG(cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad,
						cg_baby_rd);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或
										// /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】
																// 或 【
																// /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String cg_no = new String(req.getParameter("cg_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Chat_GroupService   cgSvc = new Chat_GroupService  ();
				Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);
				cgSvc.deleteCG(cg_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				Chat_Group_ItemService  cgiSvc = new Chat_Group_ItemService ();
				if (requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
					req.setAttribute("listEmps_ByDeptno", cgiSvc.getOneChat_Group_No(chat_GroupVO.getCg_no())); // 資料庫取出的list物件,存入request

				if (requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
					List<Chat_GroupVO> list = cgSvc.getAll(map);
					req.setAttribute("listEmps_ByCompositeQuery", list); // 複合查詢,
																			// 資料庫取出的list物件,存入request
				}

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
				}

				/*************************** 2.開始複合查詢 ***************************************/
				Chat_GroupService cgSvc = new Chat_GroupService();
				List<Chat_GroupVO> list = cgSvc.getAll(map);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
