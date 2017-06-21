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
            + "VALUES (?, ?, ?, SYSDATE, ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT * FROM user_report";
    private static final String GET_ONE_STMT = "SELECT * FROM User_Report WHERE mem_no_ed = ? AND mem_no_ing=?";
    // 修改資料
    private static final String UPDATE = "UPDATE User_Report SET urpt_is_cert=?, urpt_unrsn=? WHERE mem_no_ed = ? AND mem_no_ing =?";

    @Override
    public void insert(User_ReportVO user_reportVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, user_reportVO.getMem_no_ed());
            pstmt.setString(2, user_reportVO.getMem_no_ing());
            pstmt.setString(3, user_reportVO.getUrpt_cnt());
            pstmt.setString(4, user_reportVO.getUrpt_rsn());
            pstmt.setString(5, user_reportVO.getUrpt_is_cert());
            pstmt.setString(6, user_reportVO.getUrpt_unrsn());

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
    public void update(User_ReportVO user_reportVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, user_reportVO.getUrpt_is_cert());
            pstmt.setString(2, user_reportVO.getUrpt_unrsn());
            pstmt.setString(3, user_reportVO.getMem_no_ed());
            pstmt.setString(4, user_reportVO.getMem_no_ing());

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
    public User_ReportVO findByPrimaryKey(String mem_no_ed, String mem_no_ing) {

        User_ReportVO user_reportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
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

            con = ds.getConnection();
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