package com.chat.model;

import java.util.*;
import java.sql.*;


public class Chat_FriendJDBCDAO implements Chat_FriendDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // �s�W���
    private static final String INSERT_STMT = "INSERT INTO chat_friend (cf_no, mem_no_s, mem_no_o, cf_is_del) " +
            "VALUES ('CF'||LPAD(to_char(cf_no_seq.NEXTVAL),6,'0'), ?, ?, ?)";
    // �d�߸��
    private static final String GET_ALL_STMT = "SELECT * FROM chat_friend";
    private static final String GET_ONE_STMT = "SELECT * FROM chat_friend WHERE cf_no = ?";
    // �ק���
    private static final String UPDATE = "UPDATE chat_friend SET cf_is_del=? WHERE cf_no = ? ";


    @Override
    public void insert(Chat_FriendVO chat_FriendVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String[] cf = {"cf_no"}; // ���ϥ�sequence���ͽs�����ܤ~�n�g
            pstmt = con.prepareStatement(INSERT_STMT, cf); // ���ϥ�sequence���ͽs�����ܤ~�n�g�ĤG�ӰѼ�
            pstmt.setString(1, chat_FriendVO.getMem_no_s());
            pstmt.setString(2, chat_FriendVO.getMem_no_o());
            pstmt.setString(3, chat_FriendVO.getCf_is_del());
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
    public void update(Chat_FriendVO chat_FriendVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, chat_FriendVO.getCf_is_del());
            pstmt.setString(2, chat_FriendVO.getCf_no());
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
    public Chat_FriendVO findByPrimaryKey(String cf_no) {

        Chat_FriendVO chat_FriendVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, cf_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(rs.getString("cf_no"));
                chat_FriendVO.setMem_no_s(rs.getString("mem_no_s"));
                chat_FriendVO.setMem_no_o(rs.getString("mem_no_o"));
                chat_FriendVO.setCf_is_del(rs.getString("cf_is_del"));
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

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_FriendVO = new Chat_FriendVO();
                chat_FriendVO.setCf_no(rs.getString("cf_no"));
                chat_FriendVO.setMem_no_s(rs.getString("mem_no_s"));
                chat_FriendVO.setMem_no_o(rs.getString("mem_no_o"));
                chat_FriendVO.setCf_is_del(rs.getString("cf_is_del"));
                list.add(chat_FriendVO); // Store the row in the list
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

        Chat_FriendJDBCDAO dao = new Chat_FriendJDBCDAO();
        // ���լݬݨC�ӫ��O�O�_�i�H�ϥ�
        // �s�W OK
//        Chat_FriendVO chat_friendVO1 = new Chat_FriendVO();
//        chat_friendVO1.setMem_no_s("M0000002");
//        chat_friendVO1.setMem_no_o("M0000007");
//        chat_friendVO1.setCf_is_del("0");
//        dao.insert(chat_friendVO1);
//        System.out.println("�s�W���\");

        // �ק� OK
//        Chat_FriendVO chat_friendVO2 = new Chat_FriendVO();
//        chat_friendVO2.setCf_no("CF000001");
//        chat_friendVO2.setCf_is_del("1");
//        dao.update(chat_FriendVO2);
//        
//        System.out.println("update");

        // �d�� OK
//        Chat_FriendVO chat_friendVO3 = dao.findByPrimaryKey("CF000001");
//        System.out.print(chat_friendVO3.getCf_no() + ",");
//        System.out.print(chat_friendVO3.getMem_no_s() + ",");
//        System.out.print(chat_friendVO3.getMem_no_o() + ",");
//        System.out.println(chat_friendVO3.getCf_is_del());
//        System.out.println("---------------------");

        // �d�ߥ��� OK
//        List<Chat_FriendVO> list = dao.getAll();
//        for (Chat_FriendVO chat_friendVO : list) {
//            System.out.print(chat_friendVO.getCf_no() + ",");
//            System.out.print(chat_friendVO.getMem_no_s() + ",");
//            System.out.print(chat_friendVO.getMem_no_o() + ",");
//            System.out.print(chat_friendVO.getCf_is_del());
//            System.out.println();
//        }
    }
}