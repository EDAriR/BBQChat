package com.chat.model;

import java.util.*;
import java.sql.*;

public class User_ReportJDBCDAO implements User_ReportDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // 新增資料
    private static final String INSERT_STMT = "INSERT INTO user_report "
            + "(mem_no_ed, mem_no_ing, urpt_cnt, urpt_date, urpt_rsn, urpt_is_cert, urpt_unrsn) "
            + "VALUES (?, ?, ?, SYSDATE, ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT * FROM user_report";
    private static final String GET_ONE_STMT = "SELECT * FROM User_Report WHERE mem_no_ed = ? AND mem_no_ing=?";
    // 刪除資料
    private static final String UPDATE = "UPDATE User_Report SET urpt_is_cert=?, urpt_unrsn=? WHERE mem_no_ed = ? AND mem_no_ing =?";

    @Override
    public void insert(User_ReportVO user_reportVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, user_reportVO.getMem_no_ed());
            pstmt.setString(2, user_reportVO.getMem_no_ing());
            pstmt.setString(3, user_reportVO.getUrpt_cnt());
            pstmt.setString(4, user_reportVO.getUrpt_rsn());
            pstmt.setString(5, user_reportVO.getUrpt_is_cert());
            pstmt.setString(6, user_reportVO.getUrpt_unrsn());

            pstmt.executeUpdate();

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public void update(User_ReportVO user_reportVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, user_reportVO.getUrpt_is_cert());
            pstmt.setString(2, user_reportVO.getUrpt_unrsn());
            pstmt.setString(3, user_reportVO.getMem_no_ed());
            pstmt.setString(4, user_reportVO.getMem_no_ing());

            pstmt.executeUpdate();

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public User_ReportVO findByPrimaryKey(String mem_no_ed, String mem_no_ing) {

        User_ReportVO user_reportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, mem_no_ed);
            pstmt.setString(2, mem_no_ing);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user_reportVO = new User_ReportVO();
                user_reportVO.setMem_no_ed(rs.getString("mem_no_ed"));
                user_reportVO.setMem_no_ing(rs.getString("mem_no_ing"));
                user_reportVO.setUrpt_cnt(rs.getString("urpt_cnt"));
                user_reportVO.setUrpt_date(rs.getTimestamp("urpt_date"));
                user_reportVO.setUrpt_rsn(rs.getString("urpt_rsn"));
                user_reportVO.setUrpt_is_cert(rs.getString("urpt_is_cert"));
                user_reportVO.setUrpt_unrsn(rs.getString("urpt_unrsn"));
            }

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
        return user_reportVO;
    }

    @Override
    public List<User_ReportVO> getAll() {

        List<User_ReportVO> list = new ArrayList<User_ReportVO>();
        User_ReportVO user_reportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user_reportVO = new User_ReportVO();
                user_reportVO.setMem_no_ed(rs.getString("mem_no_ed"));
                user_reportVO.setMem_no_ing(rs.getString("mem_no_ing"));
                user_reportVO.setUrpt_cnt(rs.getString("urpt_cnt"));
                user_reportVO.setUrpt_date(rs.getTimestamp("urpt_date"));
                user_reportVO.setUrpt_rsn(rs.getString("urpt_rsn"));
                user_reportVO.setUrpt_is_cert(rs.getString("urpt_is_cert"));
                user_reportVO.setUrpt_unrsn(rs.getString("urpt_unrsn"));
                list.add(user_reportVO); // Store the row in the list
            }
//			 mem_no_ed, URPT_CNT, URPT_RSN,

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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

        User_ReportJDBCDAO dao = new User_ReportJDBCDAO();

        // 新增  OK
//		User_ReportVO user_reportVO1 = new User_ReportVO();
//		user_reportVO1.setMem_no_ed("M0000001");
//		user_reportVO1.setMem_no_ing("M0000009");
//		user_reportVO1.setUrpt_cnt("787846sas");
//		user_reportVO1.setUrpt_rsn("睹絏痙ē");
//		user_reportVO1.setUrpt_is_cert("0");
//		dao.insert(user_reportVO1);
//		System.out.println("insert OK");

        // 刪除 OK
//        User_ReportVO user_reportVO2 = new User_ReportVO();
//        user_reportVO2.setMem_no_ed("M0000001");
//        user_reportVO2.setMem_no_ing("M0000009");
//        user_reportVO2.setUrpt_is_cert("1");
//        dao.update(user_reportVO2);

        // 查詢 OK
//		 User_ReportVO user_reportVO3 = dao.findByPrimaryKey("M0000001", "M0000002");
//		 System.out.print(user_reportVO3.getMem_no_ed() + ",");
//		 System.out.print(user_reportVO3.getMem_no_ing() + ",");
//		 System.out.print(user_reportVO3.getUrpt_cnt() + ",");
//		 System.out.print(user_reportVO3.getUrpt_date() + ",");
//		 System.out.print(user_reportVO3.getUrpt_rsn() + ",");
//		 System.out.print(user_reportVO3.getUrpt_is_cert() + ",");
//		 System.out.println(user_reportVO3.getUrpt_unrsn());
//		 System.out.println("---------------------");

        // 查詢全部 OK
//		 List<User_ReportVO> list = dao.getAll();
//		 for (User_ReportVO user_reportVO : list) {
//		 System.out.print(user_reportVO.getMem_no_ed() + ",");
//		 System.out.print(user_reportVO.getMem_no_ing() + ",");
//		 System.out.print(user_reportVO.getUrpt_cnt() + ",");
//		 System.out.print(user_reportVO.getUrpt_date() + ",");
//		 System.out.print(user_reportVO.getUrpt_rsn() + ",");
//		 System.out.print(user_reportVO.getUrpt_is_cert() + ",");
//		 System.out.print(user_reportVO.getUrpt_unrsn());
//		 System.out.println();
//		 }
    }
}