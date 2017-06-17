package com.chat.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Chat_NotebookDAO implements Chat_NotebookDAO_interface {
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
    private static final String INSERT_STMT = "INSERT INTO chat_notebook (cnb_no, cnb_cnt) " +
            "VALUES ('cnb'||LPAD(to_char(cnb_no_seq.NEXTVAL),3,'0'), ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT cnb_no , cnb_cnt FROM chat_notebook";
    private static final String GET_ONE_STMT = "SELECT cnb_no, cnb_cnt FROM chat_notebook WHERE cnb_no = ?";
    // 刪除資料
    private static final String DELETE_PROC = "DELETE FROM chat_notebook WHERE cnb_no = ?";
    // 修改資料
    private static final String UPDATE = "UPDATE chat_notebook SET cnb_cnt=? WHERE cnb_no = ?";


    @Override
    public void insert(Chat_NotebookVO chat_NotebookVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            String[] cnb = {"cnb_no"}; // 有使用sequence產生編號的話才要寫
            pstmt = con.prepareStatement(INSERT_STMT, cnb); // 有使用sequence產生編號的話才要寫第二個參數
            pstmt.setString(1, chat_NotebookVO.getCnb_cnt());
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
    public void update(Chat_NotebookVO chat_NotebookVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();

            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, chat_NotebookVO.getCnb_cnt());
            pstmt.setString(2, chat_NotebookVO.getCnb_no());
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
    public void delete(String cnb_no){

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            // 1 設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(DELETE_PROC);
            pstmt.setString(1, cnb_no);
            pstmt.executeUpdate();

            // 2●設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete" + cnb_no);

            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3●設定於當有exception發生時之catch區塊內
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
    public Chat_NotebookVO findByPrimaryKey(String cnb_no){

        Chat_NotebookVO chat_NotebookVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setString(1, cnb_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_NotebookVO = new Chat_NotebookVO();
                chat_NotebookVO.setCnb_no(rs.getString("cnb_no"));
                chat_NotebookVO.setCnb_cnt(rs.getString("cnb_cnt"));
            }
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
        return chat_NotebookVO;
    }

    @Override
    public List<Chat_NotebookVO> getAll(){

        List<Chat_NotebookVO> list = new ArrayList<Chat_NotebookVO>();
        Chat_NotebookVO chat_NotebookVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_NotebookVO = new Chat_NotebookVO();
                chat_NotebookVO.setCnb_no(rs.getString("cnb_no"));
                chat_NotebookVO.setCnb_cnt(rs.getString("cnb_cnt"));
                list.add(chat_NotebookVO); // Store the row in the list
            }

            con = ds.getConnection();
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