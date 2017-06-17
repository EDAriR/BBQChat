package com.chat.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Chat_GroupDAO implements Chat_GroupDAO_interface {

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
    private static final String INSERT_STMT = "INSERT INTO Chat_Group " +
            "(cg_no, cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad, cg_baby_rd) " +
            "VALUES ('cg'||LPAD(to_char(cg_no_seq.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT cg_no , cg_name FROM chat_group";
    private static final String GET_ONE_STMT = "SELECT cg_no, cg_name FROM chat_group WHERE cg_no = ?";
    // 刪除資料 需連動
    private static final String DELETE_CHAT_RECORD = "DELETE FROM chat_record WHERE cg_no = ?";
    private static final String DELETE_CHAT_NOTEBOOK = "DELETE FROM chat_notebook WHERE cg_no = ?";
    private static final String DELETE_GROUP_ITEM = "DELETE FROM chat_group_item WHERE cg_no = ?";
    private static final String DELETE_CHAT_GROUP = "DELETE FROM chat_group WHERE cg_no = ?";
    // 修改資料
    private static final String UPDATE = "UPDATE chat_group SET cg_name=? WHERE cg_no = ?";

    @Override
    public void insert(Chat_GroupVO chat_GroupVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
//        	(cg_no, CG_NAME, CG_YEAR, CG_IS_AR, CG_IS_AB, CG_IS_AC, CG_IS_SF, CG_IS_AD, BABY_RD
            con = ds.getConnection();
            String[] cg = {"cg_no"}; // 有使用sequence產生編號的話才要寫
            pstmt = con.prepareStatement(INSERT_STMT, cg); // 有使用sequence產生編號的話才要寫第二個參數
            pstmt.setString(1, chat_GroupVO.getCg_name());
            pstmt.setDate(2, chat_GroupVO.getCg_year());
            pstmt.setString(3, chat_GroupVO.getCg_is_ar());
            pstmt.setString(4, chat_GroupVO.getCg_is_ab());
            pstmt.setString(5, chat_GroupVO.getCg_is_ac());
            pstmt.setString(6, chat_GroupVO.getCg_is_sf());
            pstmt.setString(7, chat_GroupVO.getCg_is_ad());
            pstmt.setString(8, chat_GroupVO.getBaby_rd());
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
    public void update(Chat_GroupVO chat_GroupVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(2, chat_GroupVO.getCg_no());
            pstmt.setString(1, chat_GroupVO.getCg_name());
            pstmt.setString(1, chat_GroupVO.getBaby_rd());
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
    public void delete(String cg_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();

            // 1 設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DELETE_CHAT_RECORD);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(DELETE_CHAT_NOTEBOOK);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(DELETE_GROUP_ITEM);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(DELETE_CHAT_GROUP);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            // 2 設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat_Group: " + cg_no);


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
    public Chat_GroupVO findByPrimaryKey(String cg_no) {

        Chat_GroupVO chat_GroupVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setString(1, cg_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_GroupVO = new Chat_GroupVO();
                chat_GroupVO.setCg_no(rs.getString("cg_no"));
                chat_GroupVO.setCg_name(rs.getString("cg_name"));
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
        return chat_GroupVO;
    }

    @Override
    public List<Chat_GroupVO> getAll() {
        List<Chat_GroupVO> list = new ArrayList<Chat_GroupVO>();
        Chat_GroupVO chat_GroupVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_GroupVO = new Chat_GroupVO();
                chat_GroupVO.setCg_no(rs.getString("cg_no"));
                chat_GroupVO.setCg_name(rs.getString("cg_name"));
                list.add(chat_GroupVO); // Store the row in the list
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