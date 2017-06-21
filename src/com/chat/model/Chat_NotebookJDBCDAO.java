package com.chat.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Chat_NotebookJDBCDAO implements Chat_NotebookDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // �s�W���
    private static final String INSERT_STMT = "INSERT INTO chat_notebook (cnb_no, cf_no, cg_no, cnb_cnt) " +
            "VALUES ('CNB'||LPAD(to_char(cnb_no_seq.NEXTVAL), 5, '0'), ?, ?, ?)";
    // �d�߸��
    private static final String GET_ALL_STMT = "SELECT * FROM chat_notebook";
    private static final String GET_ONE_STMT = "SELECT * FROM chat_notebook WHERE cnb_no = ?";
    // �R�����
    private static final String DELETE_CHAT_NOTEBOOK = "DELETE FROM chat_notebook WHERE cnb_no = ?";
    // �ק���
    private static final String UPDATE = "UPDATE chat_notebook SET cnb_cnt=? WHERE cnb_no = ?";


    @Override
    public void insert(Chat_NotebookVO chat_notebookVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String[] cnb = {"cnb_no"}; // ���ϥ�sequence���ͽs�����ܤ~�n�g
            pstmt = con.prepareStatement(INSERT_STMT, cnb); // ���ϥ�sequence���ͽs�����ܤ~�n�g�ĤG�ӰѼ�
            pstmt.setString(1, chat_notebookVO.getCf_no());
            pstmt.setString(2, chat_notebookVO.getCg_no());
            pstmt.setString(3, chat_notebookVO.getCnb_cnt());
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
    public void update(Chat_NotebookVO chat_notebookVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, chat_notebookVO.getCnb_cnt());
            pstmt.setString(2, chat_notebookVO.getCnb_no());
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
    public void delete(String cnb_no){

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // 1 �]�w�� pstm.executeUpdate()���e
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(DELETE_CHAT_NOTEBOOK);
            pstmt.setString(1, cnb_no);
            pstmt.executeUpdate();

            // 2���]�w�� pstm.executeUpdate()����
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete" + cnb_no);

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3���]�w���exception�o�ͮɤ�catch�϶���
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

        Chat_NotebookVO chat_notebookVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setString(1, cnb_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_notebookVO = new Chat_NotebookVO();
                chat_notebookVO.setCnb_no(rs.getString("cnb_no"));
                chat_notebookVO.setCf_no(rs.getString("cf_no"));
                chat_notebookVO.setCg_no(rs.getString("cg_no"));
                chat_notebookVO.setCnb_cnt(rs.getString("cnb_cnt"));
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
        return chat_notebookVO;
    }

    @Override
    public List<Chat_NotebookVO> getAll(){

        List<Chat_NotebookVO> list = new ArrayList<Chat_NotebookVO>();
        Chat_NotebookVO chat_notebookVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_notebookVO = new Chat_NotebookVO();
                chat_notebookVO.setCnb_no(rs.getString("cnb_no"));
                chat_notebookVO.setCf_no(rs.getString("cf_no"));
                chat_notebookVO.setCg_no(rs.getString("cg_no"));
                chat_notebookVO.setCnb_cnt(rs.getString("cnb_cnt"));
                list.add(chat_notebookVO); // Store the row in the list
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

        Chat_NotebookJDBCDAO dao = new Chat_NotebookJDBCDAO();

        // �s�W OK
//        Chat_NotebookVO chat_notebookVO1 = new Chat_NotebookVO();
//        chat_notebookVO1.setCf_no("CF000007");
//        chat_notebookVO1.setCnb_cnt("�O�ƥ�����https://img.kekeke.cc/t/x8JrPHoAAr.png");
//        dao.insert(chat_notebookVO1);
//        System.out.println("�s�W����");

        // �ק� OK
//		Chat_NotebookVO chat_notebookVO2 = new Chat_NotebookVO();
//		chat_notebookVO2.setCnb_no("CNB00005");
//		chat_notebookVO2.setCnb_cnt("update");
//		dao.update(chat_notebookVO2);
//		System.out.println("�ק粒��");

        // �R�� OK
//		dao.delete("CNB00005");
//		System.out.println("�R������");

        // �d�� OK
//		Chat_NotebookVO chat_notebookVO3 = dao.findByPrimaryKey("CNB00004");
//		System.out.print(chat_notebookVO3.getCnb_no() + ",");
//		System.out.print(chat_notebookVO3.getCf_no() + ",");
//		System.out.print(chat_notebookVO3.getCg_no() + ",");
//		System.out.print(chat_notebookVO3.getCnb_cnt());
//		System.out.println("---------------------");

        // �d�ߥ��� OK
//		List<Chat_NotebookVO> list = dao.getAll();
//		for (Chat_NotebookVO chat_notebookVO : list) {
//			System.out.print(chat_notebookVO.getCnb_no() + ",");
//			System.out.print(chat_notebookVO.getCf_no() + ",");
//			System.out.print(chat_notebookVO.getCg_no() + ",");
//			System.out.print(chat_notebookVO.getCnb_cnt());
//			System.out.println();
//		}

    }
}