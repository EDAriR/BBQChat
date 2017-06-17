package com.chat.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User_ReportDAO implements User_ReportDAO_interface {
    // 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    // 新增資料
    private static final String INSERT_STMT = "INSERT INTO user_report "
            + "(mem_no_ed, mem_no_ing, urpt_cnt, urpt_date, urpt_rsn, urpt_is_cert, urpt_unrsn) "
            + "VALUES (?, ?, ?, current_timestamp, ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT mem_no_ed, urpt_cnt, urpt_rsn, urpt_is_cert FROM user_report";
    private static final String GET_ONE_STMT = "SELECT mem_no_ed, urpt_cnt, urpt_rsn, urpt_is_cert "
            + "FROM User_Report WHERE mem_no_ed = ?";
    // 刪除資料
    private static final String DELETE_NEWS = "DELETE FROM User_Report WHERE mem_no_ed = ? AND mem_no_ing =?";
    // 修改資料
    private static final String UPDATE = "UPDATE User_Report SET URPT_IS_CERT=? WHERE mem_no_ed = ? AND mem_no_ing =?";

    @Override
    public void insert(User_ReportVO user_ReportVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            // (MEM_NO_ED, MEM_NO_ING, URPT_CNT, URPT_DATE, URPT_RSN,
            // URPT_IS_CERT, URPT_UNRSN)
            pstmt.setString(1, user_ReportVO.getMem_no_ed());
            pstmt.setString(2, user_ReportVO.getMem_no_ing());
            pstmt.setString(3, user_ReportVO.getUrpt_cnt());
            pstmt.setString(4, user_ReportVO.getUrpt_rsn());
            pstmt.setString(5, user_ReportVO.getUrpt_is_cert());
            pstmt.setString(6, user_ReportVO.getUrpt_unrsn());

            pstmt.executeUpdate();

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
    public void update(User_ReportVO user_ReportVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, user_ReportVO.getMem_no_ed());
            pstmt.setString(2, user_ReportVO.getMem_no_ing());
            pstmt.setString(3, user_ReportVO.getUrpt_is_cert());
            pstmt.executeUpdate();

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
    public void delete(String mem_no_ed, String mem_no_ing) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();

            // 1 設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(DELETE_NEWS);
            pstmt.setString(1, mem_no_ed);
            pstmt.setString(1, mem_no_ing);
            pstmt.executeUpdate();

            // 2 設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete User Report :" + mem_no_ed);

            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3 設定於當有exception發生時之catch區塊內
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("rollback error occured. " + excep.getMessage());
                }
            }
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public User_ReportVO findByPrimaryKey(String mem_no_ed) {

        User_ReportVO user_ReportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();

            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, mem_no_ed);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user_ReportVO = new User_ReportVO();
                user_ReportVO.setMem_no_ed(rs.getString("mem_no_ed"));
                user_ReportVO.setUrpt_cnt(rs.getString("urpt_cnt"));
            }
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
        return user_ReportVO;
    }

    @Override
    public List<User_ReportVO> getAll() {

        List<User_ReportVO> list = new ArrayList<User_ReportVO>();
        User_ReportVO user_ReportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user_ReportVO = new User_ReportVO();
                user_ReportVO.setMem_no_ed(rs.getString("mem_no_ed"));
                user_ReportVO.setUrpt_cnt(rs.getString("urpt_cnt"));
                user_ReportVO.setUrpt_rsn(rs.getString("urpt_rsn"));
                list.add(user_ReportVO); // Store the row in the list
            }
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
}