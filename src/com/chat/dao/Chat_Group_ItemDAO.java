package com.chat.dao;

import com.chat.model.Chat_Group_ItemDAO_interface;
import com.chat.model.Chat_Group_ItemVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Chat_Group_ItemDAO implements Chat_Group_ItemDAO_interface {
    // �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    // �s�W���
    private static final String INSERT_STMT = "INSERT INTO chat_group_item (cg_no, mem_no) " +
            "VALUES (?, ?)";
    // �d�߸��
    private static final String GET_ALL_STMT = "SELECT cg_no , mem_no FROM chat_group_item";
    private static final String GET_ONE_STMT = "SELECT cg_no, mem_no FROM chat_group_item WHERE cg_no = ?";
    // �R�����
    private static final String DELETE_PROC = "DELETE FROM chat_group_item WHERE cg_no = ?";


    @Override
    public void insert(Chat_Group_ItemVO chat_Group_ItemVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();

            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, chat_Group_ItemVO.getCg_no());
            pstmt.setString(2, chat_Group_ItemVO.getMem_no());
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
    public void delete(String cg_no, String mem_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();

            // 1 �]�w�� pstm.executeUpdate()���e
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DELETE_PROC);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            // 2 �]�w�� pstm.executeUpdate()����
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat Group Item" + cg_no);

              // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3 �]�w�����exception�o�ͮɤ�catch�϶���
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
    public Chat_Group_ItemVO findByPrimaryKey(String cg_no, String mem_no) {

        Chat_Group_ItemVO chat_Group_ItemVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setString(1, cg_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_Group_ItemVO = new Chat_Group_ItemVO();
                chat_Group_ItemVO.setCg_no(rs.getString("cg_no"));
                chat_Group_ItemVO.setMem_no(rs.getString("mem_no"));
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
        return chat_Group_ItemVO;
    }

    public List<Chat_Group_ItemVO> getAll() {

        List<Chat_Group_ItemVO> list = new ArrayList<Chat_Group_ItemVO>();
        Chat_Group_ItemVO chat_Group_ItemVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_Group_ItemVO = new Chat_Group_ItemVO();
                chat_Group_ItemVO.setCg_no(rs.getString("cg_no"));
                chat_Group_ItemVO.setMem_no(rs.getString("mem_no"));
                list.add(chat_Group_ItemVO); // Store the row in the list
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