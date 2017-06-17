package com.chat.model;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;


public class Chat_FriendDAO implements Chat_FriendDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;		
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
    // 新增資料
    private static final String INSERT_STMT = "INSERT INTO chat_friend (cf_no, mem_no_s, mem_no_o, cf_is_del) " +
            "VALUES ('cf'||LPAD(TO_CHAR(cf_no_seq.NEXTVAL),3,'0'), ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT cf_no, mem_no_s, mem_no_o, cf_is_del FROM chat_friend";
    private static final String GET_ONE_STMT = "SELECT cf_no, mem_no_s, mem_no_o, cf_is_del FROM chat_friend WHERE cf_no = ?";
    // 刪除資料
    private static final String DELETE_PROC = "DELETE FROM chat_friend WHERE cf_no = ?";
    // 修改資料
    private static final String UPDATE = "UPDATE chat_friend SET cf_is_del=? WHERE cf_no = ? ";


    @Override
    public void insert(Chat_FriendVO chat_FriendVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

        	con = ds.getConnection();            
            String[] cf = {"cf_no"}; // 有使用sequence產生編號的話才要寫
            pstmt = con.prepareStatement(INSERT_STMT, cf); // 有使用sequence產生編號的話才要寫第二個參數
            
            pstmt.setString(1, chat_FriendVO.getMem_no_s());
            pstmt.setString(2, chat_FriendVO.getMem_no_o());
            pstmt.setString(3, chat_FriendVO.getCf_is_del());
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
    public void update(Chat_FriendVO chat_FriendVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

        	con = ds.getConnection();        	
            pstmt = con.prepareStatement(UPDATE);
            
            pstmt.setString(1, chat_FriendVO.getCf_is_del());
            pstmt.setString(2, chat_FriendVO.getCf_no());
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
    public void delete(String cf_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
        	
        	con = ds.getConnection();        	
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DELETE_PROC);
            
            pstmt.setString(1, cf_no);
            pstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat Friend :" + cf_no);
            
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {                	
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
    public Chat_FriendVO findByPrimaryKey(String cf_no) {

        Chat_FriendVO chat_FriendVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

        	con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setString(1, cf_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(rs.getString("cf_no"));
                chat_FriendVO.setMem_no_o(rs.getString("mem_no_o"));
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
        return chat_FriendVO;
    }

    @Override
    public List<Chat_FriendVO> getAll() {

        List<Chat_FriendVO> list = new ArrayList<Chat_FriendVO>();
        Chat_FriendVO chat_FriendVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(rs.getString("cf_no"));
                chat_FriendVO.setMem_no_o(rs.getString("mem_no_o"));
                list.add(chat_FriendVO); // Store the row in the list
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