package com.chat.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Chat_RecordDAO implements Chat_RecordDAO_interface {
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
    private static final String INSERT_STMT = "INSERT INTO chat_record " +
            "(cr_no, cr_date, cf_no, cg_no, cr_cnt) " +
            "VALUES ('cr'||LPAD(TO_CHAR(adm_no_seq.NEXTVAL),4,'0'), CURRENT_TIMESTAMP, ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT cr_no, cr_date, cr_cnt FROM chat_record";
    private static final String GET_ONE_STMT = "SELECT cr_no, cr_date, cr_cnt FROM chat_record WHERE cr_no = ?";
    // 刪除資料
    private static final String DELETE_PROC = "DELETE FROM chat_record WHERE cr_no = ?";
    // 修改資料
    private static final String UPDATE = "UPDATE chat_record SET cr_cnt=? WHERE cr_no = ?";


    @Override
    public void insert(Chat_RecordVO chat_RecordVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            String[] cr = {"cr_no"}; // 有使用sequence產生編號的話才要寫
            pstmt = con.prepareStatement(INSERT_STMT, cr); // 有使用sequence產生編號的話才要寫第二個參數
            pstmt.setString(1, chat_RecordVO.getCf_no());
            pstmt.setString(2, chat_RecordVO.getCg_no());
            pstmt.setString(3, chat_RecordVO.getCr_cnt());
            pstmt.executeUpdate();

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
    public void update(Chat_RecordVO chat_RecordVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, chat_RecordVO.getCr_cnt());
            pstmt.setString(2, chat_RecordVO.getCr_no());
            pstmt.executeUpdate();

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
    public void delete(String cr_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            // 1 設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(DELETE_PROC);
            pstmt.setString(1, cr_no);
            pstmt.executeUpdate();

            // 2 設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat_Record : " + cr_no);

            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3 設定於當有exception發生時之catch區塊內
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("rollback error occured. "
                            + excep.getMessage());
                }
            }
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
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
    public Chat_RecordVO findByPrimaryKey(String cr_no) {

        Chat_RecordVO chat_RecordVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, cr_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_RecordVO = new Chat_RecordVO();
                chat_RecordVO.setCr_no(rs.getString("cr_no"));
                chat_RecordVO.setCr_cnt(rs.getString("cr_cnt"));
            }
            con = ds.getConnection();
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
        return chat_RecordVO;
    }

    @Override
    public List<Chat_RecordVO> getAll() {

        List<Chat_RecordVO> list = new ArrayList<Chat_RecordVO>();
        Chat_RecordVO chat_RecordVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_RecordVO = new Chat_RecordVO();
                chat_RecordVO.setCr_no(rs.getString("cr_no"));
                chat_RecordVO.setCr_cnt(rs.getString("cr_cnt"));
                list.add(chat_RecordVO); // Store the row in the list
            }
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
}