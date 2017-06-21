package com.chat.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Chat_GroupJDBCDAO implements Chat_GroupDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // �s�W���
    private static final String INSERT_STMT = "INSERT INTO chat_group " +
            "(cg_no, cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad, cg_baby_rd) " +
            "VALUES ('CG'||LPAD(to_char(cg_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
    // �d�߸��
    private static final String GET_ALL_STMT = "SELECT * FROM chat_group";
    private static final String GET_ONE_STMT = "SELECT * FROM chat_group WHERE cg_no = ?";
    // �R����� �ݳs��
    private static final String DELETE_CHAT_RECORDs = "DELETE FROM chat_record WHERE cg_no = ?";
    private static final String DELETE_CHAT_NOTEBOOKs = "DELETE FROM chat_notebook WHERE cg_no = ?";
    private static final String DELETE_GROUP_ITEMs = "DELETE FROM chat_group_item WHERE cg_no = ?";
    private static final String DELETE_CHAT_GROUP = "DELETE FROM chat_group WHERE cg_no = ?";
    // �ק���
    private static final String UPDATE = "UPDATE chat_group SET cg_name=?, cg_year=?, cg_is_ar=?, cg_is_ab=?, " +
    		"cg_is_ac=?, cg_is_sf=?, cg_is_ad=?, cg_baby_rd=? WHERE cg_no = ?";

    @Override
    public void insert(Chat_GroupVO chat_groupVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String[] cols = {"cg_no"}; // ���ϥ�sequence���ͽs�����ܤ~�n�g
            pstmt = con.prepareStatement(INSERT_STMT, cols); // ���ϥ�sequence���ͽs�����ܤ~�n�g�ĤG�ӰѼ�
            pstmt.setString(1, chat_groupVO.getCg_name());
            pstmt.setDate(2, chat_groupVO.getCg_year());
            pstmt.setString(3, chat_groupVO.getCg_is_ar());
            pstmt.setString(4, chat_groupVO.getCg_is_ab());
            pstmt.setString(5, chat_groupVO.getCg_is_ac());
            pstmt.setString(6, chat_groupVO.getCg_is_sf());
            pstmt.setString(7, chat_groupVO.getCg_is_ad());
            pstmt.setString(8, chat_groupVO.getCg_baby_rd());
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
    public void update(Chat_GroupVO chat_groupVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, chat_groupVO.getCg_name());
            pstmt.setDate(2, chat_groupVO.getCg_year());
            pstmt.setString(3, chat_groupVO.getCg_is_ar());
            pstmt.setString(4, chat_groupVO.getCg_is_ab());
            pstmt.setString(5, chat_groupVO.getCg_is_ac());
            pstmt.setString(6, chat_groupVO.getCg_is_sf());
            pstmt.setString(7, chat_groupVO.getCg_is_ad());
            pstmt.setString(8, chat_groupVO.getCg_baby_rd());
            pstmt.setString(9, chat_groupVO.getCg_no());
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
    public void delete(String cg_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // 1 �]�w�� pstm.executeUpdate()���e
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DELETE_CHAT_RECORDs);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(DELETE_CHAT_NOTEBOOKs);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(DELETE_GROUP_ITEMs);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(DELETE_CHAT_GROUP);
            pstmt.setString(1, cg_no);
            pstmt.executeUpdate();

            // 2 �]�w�� pstm.executeUpdate()����
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat_Group: " + cg_no);

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3 �]�w���exception�o�ͮɤ�catch�϶���
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

        Chat_GroupVO chat_groupVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setString(1, cg_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_groupVO = new Chat_GroupVO();
                chat_groupVO.setCg_no(rs.getString("cg_no"));
                chat_groupVO.setCg_name(rs.getString("cg_name"));
                chat_groupVO.setCg_year(rs.getDate("cg_year"));
                chat_groupVO.setCg_is_ar(rs.getString("cg_is_ar"));
                chat_groupVO.setCg_is_ab(rs.getString("cg_is_ab"));
                chat_groupVO.setCg_is_ac(rs.getString("cg_is_ac"));
                chat_groupVO.setCg_is_sf(rs.getString("cg_is_sf"));
                chat_groupVO.setCg_is_ad(rs.getString("cg_is_ad"));
                chat_groupVO.setCg_baby_rd(rs.getString("cg_baby_rd"));
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
        return chat_groupVO;
    }

    @Override
    public List<Chat_GroupVO> getAll() {
        List<Chat_GroupVO> list = new ArrayList<Chat_GroupVO>();
        Chat_GroupVO chat_groupVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_groupVO = new Chat_GroupVO();
                chat_groupVO.setCg_no(rs.getString("cg_no"));
                chat_groupVO.setCg_name(rs.getString("cg_name"));
                chat_groupVO.setCg_year(rs.getDate("cg_year"));
                chat_groupVO.setCg_is_ar(rs.getString("cg_is_ar"));
                chat_groupVO.setCg_is_ab(rs.getString("cg_is_ab"));
                chat_groupVO.setCg_is_ac(rs.getString("cg_is_ac"));
                chat_groupVO.setCg_is_sf(rs.getString("cg_is_sf"));
                chat_groupVO.setCg_is_ad(rs.getString("cg_is_ad"));
                chat_groupVO.setCg_baby_rd(rs.getString("cg_baby_rd"));
                list.add(chat_groupVO); // Store the row in the list
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

        Chat_GroupJDBCDAO dao = new Chat_GroupJDBCDAO();

        // �s�W  OK
//        Chat_GroupVO chat_groupVO1 = new Chat_GroupVO();
//        chat_groupVO1.setCg_name("�s�մ���1");
//        chat_groupVO1.setCg_year(java.sql.Date.valueOf("2002-02-01"));
//        chat_groupVO1.setCg_is_ab("0");
//        chat_groupVO1.setCg_is_ac("1");
//        chat_groupVO1.setCg_is_sf("0");
//        chat_groupVO1.setCg_is_ad("1");
//        chat_groupVO1.setCg_is_ar("1");
//        chat_groupVO1.setCg_baby_rd("�S�ɶ���ı�g");
//        dao.insert(chat_groupVO1);
//        System.out.println("insert");

        // �ק� OK
//		Chat_GroupVO chat_groupVO2 = new Chat_GroupVO();
//		chat_groupVO2.setCg_no("CG000003");
//		chat_groupVO2.setCg_name("�s�մ���1");
//		chat_groupVO2.setCg_year(java.sql.Date.valueOf("2002-02-01"));
//		chat_groupVO2.setCg_is_ab("0");
//		chat_groupVO2.setCg_is_ac("1");
//		chat_groupVO2.setCg_is_sf("0");
//		chat_groupVO2.setCg_is_ad("1");
//		chat_groupVO2.setCg_is_ar("1");
//		chat_groupVO2.setCg_baby_rd("�S�ɶ���ı�g");
//		dao.update(chat_groupVO2);
//		System.out.println("update");

        // �R�� OK
//		dao.delete("CG000002");
//		System.out.println("Delete");

        // �d�� OK
//        Chat_GroupVO chat_groupVO3 = dao.findByPrimaryKey("CG000002");
//        System.out.print(chat_groupVO3.getCg_no() + ",");
//        System.out.print(chat_groupVO3.getCg_name() + ",");
//        System.out.print(chat_groupVO3.getCg_year() + ",");
//        System.out.print(chat_groupVO3.getCg_is_ar() + ",");
//        System.out.print(chat_groupVO3.getCg_is_ab() + ",");
//        System.out.print(chat_groupVO3.getCg_is_ac() + ",");
//        System.out.print(chat_groupVO3.getCg_is_sf() + ",");
//        System.out.print(chat_groupVO3.getCg_is_ad() + ",");
//        System.out.println(chat_groupVO3.getCg_baby_rd());
//        System.out.println("---------------------");

        // �d�ߥ��� OK
//        List<Chat_GroupVO> list = dao.getAll();
//        for (Chat_GroupVO chat_groupVO : list) {
//            System.out.print(chat_groupVO.getCg_no() + ",");
//            System.out.print(chat_groupVO.getCg_name() + ",");
//            System.out.print(chat_groupVO.getCg_year() + ",");
//            System.out.print(chat_groupVO.getCg_is_ar() + ",");
//            System.out.print(chat_groupVO.getCg_is_ab() + ",");
//            System.out.print(chat_groupVO.getCg_is_ac() + ",");
//            System.out.print(chat_groupVO.getCg_is_sf() + ",");
//            System.out.print(chat_groupVO.getCg_is_ad() + ",");
//            System.out.print(chat_groupVO.getCg_baby_rd());
//            System.out.println();
//        }
    }
}