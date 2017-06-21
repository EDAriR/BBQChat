package com.chat.model;

import java.util.*;
import java.sql.*;


public class Chat_Group_ItemJDBCDAO implements Chat_Group_ItemDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // 新增資料
    private static final String INSERT_STMT = "INSERT INTO chat_group_item (cg_no, mem_no) " +
            "VALUES (?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT * FROM chat_group_item";
    private static final String GET_BY_CG_NO_STMT = "SELECT * FROM chat_group_item WHERE cg_no =?";
    private static final String GET_BY_MEM_NO_STMT = "SELECT * FROM chat_group_item WHERE mem_no =?";
    // 刪除資料
    private static final String DELETE_GROUP_ITEM = "DELETE FROM chat_group_item WHERE cg_no = ? AND mem_no =?";


    @Override
    public void insert(Chat_Group_ItemVO chat_group_itemVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, chat_group_itemVO.getCg_no());
            pstmt.setString(2, chat_group_itemVO.getMem_no());
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
    public void delete(String cg_no, String mem_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // 1 設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DELETE_GROUP_ITEM);
            pstmt.setString(1, cg_no);
            pstmt.setString(2, mem_no);
            pstmt.executeUpdate();

            // 2 設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat Group Item" + cg_no + mem_no);

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. "
                    + e.getMessage());
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
    public List<Chat_Group_ItemVO> findByCgNo(String cg_no) {

        List<Chat_Group_ItemVO> list = new ArrayList<Chat_Group_ItemVO>();
        Chat_Group_ItemVO chat_group_itemVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_BY_CG_NO_STMT);
            pstmt.setString(1, cg_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_group_itemVO = new Chat_Group_ItemVO();
                chat_group_itemVO.setCg_no(rs.getString("cg_no"));
                chat_group_itemVO.setMem_no(rs.getString("mem_no"));
                list.add(chat_group_itemVO); // Store the row in the list
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

    @Override
    public List<Chat_Group_ItemVO> findByMemNo(String mem_no) {

        List<Chat_Group_ItemVO> list = new ArrayList<Chat_Group_ItemVO>();
        Chat_Group_ItemVO chat_group_itemVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_BY_MEM_NO_STMT);
            pstmt.setString(1, mem_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_group_itemVO = new Chat_Group_ItemVO();
                chat_group_itemVO.setCg_no(rs.getString("cg_no"));
                chat_group_itemVO.setMem_no(rs.getString("mem_no"));
                list.add(chat_group_itemVO); // Store the row in the list
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

    @Override
    public List<Chat_Group_ItemVO> getAll() {

        List<Chat_Group_ItemVO> list = new ArrayList<Chat_Group_ItemVO>();
        Chat_Group_ItemVO chat_group_itemVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_group_itemVO = new Chat_Group_ItemVO();
                chat_group_itemVO.setCg_no(rs.getString("cg_no"));
                chat_group_itemVO.setMem_no(rs.getString("mem_no"));
                list.add(chat_group_itemVO); // Store the row in the list
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

        Chat_Group_ItemJDBCDAO dao = new Chat_Group_ItemJDBCDAO();

        // 新增 OK
//        Chat_Group_ItemVO chat_group_itemVO1 = new Chat_Group_ItemVO();
//        chat_group_itemVO1.setCg_no("CG000008");
//        chat_group_itemVO1.setMem_no("M0000004");
//        dao.insert(chat_group_itemVO1);
//        System.out.println("新增成功");

        // 刪除 OK
//		dao.delete("CG000006","M0000004");
//		System.out.println("delete");

        // 查詢 cg_no OK
//		List<Chat_Group_ItemVO> list = dao.findByCgNo("CG000006");
//		for (Chat_Group_ItemVO chat_group_itemVO : list) {
//			System.out.print(chat_group_itemVO.getCg_no() + ",");
//			System.out.print(chat_group_itemVO.getMem_no());
//			System.out.println();
//		}

        // 查詢 mem_no OK
//		List<Chat_Group_ItemVO> list = dao.findByMemNo("M0000004");
//		for (Chat_Group_ItemVO chat_group_itemVO : list) {
//			System.out.print(chat_group_itemVO.getCg_no() + ",");
//			System.out.print(chat_group_itemVO.getMem_no());
//			System.out.println();
//		}

        // 查詢全部 OK
//		List<Chat_Group_ItemVO> list = dao.getAll();
//		for (Chat_Group_ItemVO chat_group_itemVO : list) {
//			System.out.print(chat_group_itemVO.getCg_no() + ",");
//			System.out.print(chat_group_itemVO.getMem_no());
//			System.out.println();
//		}

    }
}