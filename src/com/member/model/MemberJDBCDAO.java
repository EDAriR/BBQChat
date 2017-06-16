package com.member.model;

import java.util.*;
import java.sql.*;


public class MemberJDBCDAO implements MemberDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO member (mem_no, exp_no, memg_gr, mem_acct, mem_pwd, mem_joind, mem_updated, mem_name, mem_aka, mem_adds, mem_addc, mem_phone, mem_mail, mem_intro_b, mem_is_sell, mem_acct_s, mem_intro_s, mem_point_s, mem_intro_e, mem_is_stop, mem_point_b) VALUES ('M'||LPAD(to_char(mem_no_seq.NEXTVAL), 7, '0'), ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM member";
	private static final String GET_ONE_STMT = "SELECT * FROM member where mem_no = ?";
	// 刪除資料
//	private static final String DELETE_MEM = "DELETE FROM member where mem_no = ?";	
	// 修改資料
	private static final String UPDATE = "UPDATE member set exp_no=?, memg_gr=?, mem_acct=?, mem_pwd=?, mem_joind=?, mem_updated=?, mem_name=?, mem_aka=?, mem_adds=?, mem_addc=?, mem_phone=?, mem_mail=?, mem_intro_b=?, mem_is_sell=?, mem_acct_s=?, mem_intro_s=?, mem_point_s=?, mem_intro_e=?, mem_is_stop=?, mem_point_b=? where mem_no = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "mem_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, memberVO.getExp_no());
			pstmt.setString(2, memberVO.getMemg_gr());
			pstmt.setString(3, memberVO.getMem_acct());
			pstmt.setString(4, memberVO.getMem_pwd());
			pstmt.setDate(5, memberVO.getMem_updated());
			pstmt.setString(6, memberVO.getMem_name());
			pstmt.setString(7, memberVO.getMem_aka());
			pstmt.setString(8, memberVO.getMem_adds());
			pstmt.setString(9, memberVO.getMem_addc());
			pstmt.setString(10, memberVO.getMem_phone());
			pstmt.setString(11, memberVO.getMem_mail());
			pstmt.setString(12, memberVO.getMem_intro_b());
			pstmt.setString(13, memberVO.getMem_is_sell());
			pstmt.setString(14, memberVO.getMem_acct_s());
			pstmt.setString(15, memberVO.getMem_intro_s());
			pstmt.setInt(16, memberVO.getMem_point_s());
			pstmt.setString(17, memberVO.getMem_intro_e());
			pstmt.setInt(18, memberVO.getMem_point_b());

			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getExp_no());
			pstmt.setString(2, memberVO.getMemg_gr());
			pstmt.setString(3, memberVO.getMem_acct());
			pstmt.setString(4, memberVO.getMem_pwd());
			pstmt.setDate(5, memberVO.getMem_joind());
			pstmt.setDate(6, memberVO.getMem_updated());
			pstmt.setString(7, memberVO.getMem_name());
			pstmt.setString(8, memberVO.getMem_aka());
			pstmt.setString(9, memberVO.getMem_adds());
			pstmt.setString(10, memberVO.getMem_addc());
			pstmt.setString(11, memberVO.getMem_phone());
			pstmt.setString(12, memberVO.getMem_mail());
			pstmt.setString(13, memberVO.getMem_intro_b());
			pstmt.setString(14, memberVO.getMem_is_sell());
			pstmt.setString(15, memberVO.getMem_acct_s());
			pstmt.setString(16, memberVO.getMem_intro_s());
			pstmt.setInt(17, memberVO.getMem_point_s());
			pstmt.setString(18, memberVO.getMem_intro_e());
			pstmt.setString(19, memberVO.getMem_is_stop());
			pstmt.setInt(20, memberVO.getMem_point_b());
			pstmt.setString(21, memberVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//	@Override
//	public void delete(String mem_no) {
//		int updateCount_PRODUCTs = 0;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			// 1●設定於 pstm.executeUpdate()之前
//			con.setAutoCommit(false);
//
//			// 先刪除商品(多) --->尚未建product，因此先註解
////			pstmt = con.prepareStatement(DELETE_PRODUCTs);
////			pstmt.setString(1, proc_no);
////			updateCount_PRODUCTs = pstmt.executeUpdate();
//			// 再刪除會員(一)
//			pstmt = con.prepareStatement(DELETE_MEM);
//			pstmt.setString(1, mem_no);
//			pstmt.executeUpdate();
//
//			// 2●設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除會員" + mem_no + "時,共有會員" + updateCount_PRODUCTs
//					+ "個同時被刪除");
//			
//			// Handle any DRIVER errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database DRIVER. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public MemberVO findByPrimaryKey(String mem_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setExp_no(rs.getString("exp_no"));
				memberVO.setMemg_gr(rs.getString("memg_gr"));
				memberVO.setMem_acct(rs.getString("mem_acct"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_joind(rs.getDate("mem_joind"));
				memberVO.setMem_updated(rs.getDate("mem_updated"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_aka(rs.getString("mem_aka"));
				memberVO.setMem_adds(rs.getString("mem_adds"));
				memberVO.setMem_addc(rs.getString("mem_addc"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_mail(rs.getString("mem_mail"));
				memberVO.setMem_intro_b(rs.getString("mem_intro_b"));
				memberVO.setMem_is_sell(rs.getString("mem_is_sell"));
				memberVO.setMem_acct_s(rs.getString("mem_acct_s"));
				memberVO.setMem_intro_s(rs.getString("mem_intro_s"));
				memberVO.setMem_point_s(rs.getInt("mem_point_s"));
				memberVO.setMem_intro_e(rs.getString("mem_intro_e"));
				memberVO.setMem_is_stop(rs.getString("mem_is_stop"));
				memberVO.setMem_point_b(rs.getInt("mem_point_b"));
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setExp_no(rs.getString("Exp_no"));
				memberVO.setMemg_gr(rs.getString("Memg_gr"));
				memberVO.setMem_acct(rs.getString("Mem_acct"));
				memberVO.setMem_pwd(rs.getString("Mem_pwd"));
				memberVO.setMem_joind(rs.getDate("Mem_joind"));
				memberVO.setMem_updated(rs.getDate("Mem_updated"));
				memberVO.setMem_name(rs.getString("Mem_name"));
				memberVO.setMem_aka(rs.getString("Mem_aka"));
				memberVO.setMem_adds(rs.getString("Mem_adds"));
				memberVO.setMem_addc(rs.getString("Mem_addc"));
				memberVO.setMem_phone(rs.getString("Mem_phone"));
				memberVO.setMem_mail(rs.getString("Mem_mail"));
				memberVO.setMem_intro_b(rs.getString("Mem_intro_b"));
				memberVO.setMem_is_sell(rs.getString("Mem_is_sell"));
				memberVO.setMem_acct_s(rs.getString("Mem_acct_s"));
				memberVO.setMem_intro_s(rs.getString("Mem_intro_s"));
				memberVO.setMem_point_s(rs.getInt("Mem_point_s"));
				memberVO.setMem_intro_e(rs.getString("Mem_intro_e"));
				memberVO.setMem_is_stop(rs.getString("Mem_is_stop"));
				memberVO.setMem_point_b(rs.getInt("Mem_point_b"));
				list.add(memberVO); // Store the row in the list
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


	public static void main(String[] args) {

		MemberJDBCDAO dao = new MemberJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setMemg_gr("0");
//		memberVO1.setMem_acct("xyz111");
//		memberVO1.setMem_pwd("12345");
//		memberVO1.setMem_joind(java.sql.Date.valueOf("2016-1-01"));
//		memberVO1.setMem_name("ㄎㄎㄎ");
//		memberVO1.setMem_adds("中和拉");
//		memberVO1.setMem_is_sell("0");
//		memberVO1.setMem_is_stop("0");
//		memberVO1.setMem_point_s(0);
//		memberVO1.setMem_point_b(0);
//		dao.insert(memberVO1);

		// 修改 OK
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setMem_no("M0000001");
//		memberVO2.setMemg_gr("0");
//		memberVO2.setMem_acct("timmy123");
//		memberVO2.setMem_pwd("12345");
//		memberVO2.setMem_joind(java.sql.Date.valueOf("2016-1-01"));
//		memberVO2.setMem_name("ㄎㄎㄎ");
//		memberVO2.setMem_adds("中和拉");
//		memberVO2.setMem_is_sell("0");
//		memberVO2.setMem_is_stop("0");
//		memberVO2.setMem_point_s(1);
//		memberVO2.setMem_point_b(1);
//		dao.update(memberVO2);

		// 刪除>>不要了
//		dao.delete("1");

		// 查詢 OK
//		MemberVO memberVO3 = dao.findByPrimaryKey("M0000001");
//		System.out.print(memberVO3.getMem_no() + ",");
//		System.out.print(memberVO3.getMem_name() + ",");
//		System.out.println(memberVO3.getMem_aka());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO member : list) {
//			System.out.print(member.getMem_no() + ",");
//			System.out.print(member.getMem_name()+ ",");
//			System.out.print(member.getMem_aka());
//			System.out.println();
//		}
		
	}
}