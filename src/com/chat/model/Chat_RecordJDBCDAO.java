package com.chat.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Chat_RecordJDBCDAO implements Chat_RecordDAO_interface {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // �s�W���
    private static final String INSERT_STMT = "INSERT INTO chat_record " +
            "(cr_no, cf_no, cg_no, cr_date, cr_cnt) " +
            "VALUES ('CR'||LPAD(to_char(cr_no_seq.NEXTVAL), 6, '0'), ?, ?, SYSDATE, ?)";
    // �d�߸��
    private static final String GET_ALL_STMT = "SELECT * FROM chat_record";
    private static final String GET_ONE_STMT = "SELECT * FROM chat_record WHERE cr_no = ?";
    // �R�����
    private static final String DELETE_CHAT_RECORD = "DELETE FROM chat_record WHERE cr_no = ?";


    @Override
    public void insert(Chat_RecordVO chat_recordVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String[] cr = {"cr_no"}; // ���ϥ�sequence���ͽs�����ܤ~�n�g
            pstmt = con.prepareStatement(INSERT_STMT, cr); // ���ϥ�sequence���ͽs�����ܤ~�n�g�ĤG�ӰѼ�
            pstmt.setString(1, chat_recordVO.getCf_no());
            pstmt.setString(2, chat_recordVO.getCg_no());
            pstmt.setString(3, chat_recordVO.getCr_cnt());
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
    public void delete(String cr_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // 1 �]�w�� pstm.executeUpdate()���e
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(DELETE_CHAT_RECORD);
            pstmt.setString(1, cr_no);
            pstmt.executeUpdate();

            // 2 �]�w�� pstm.executeUpdate()����
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Delete Chat_Record : " + cr_no);

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
    public Chat_RecordVO findByPrimaryKey(String cr_no) {

        Chat_RecordVO chat_recordVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, cr_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_recordVO = new Chat_RecordVO();
                chat_recordVO.setCr_no(rs.getString("cr_no"));
                chat_recordVO.setCf_no(rs.getString("cf_no"));
                chat_recordVO.setCg_no(rs.getString("cg_no"));
                chat_recordVO.setCr_date(rs.getDate("cr_date"));
                chat_recordVO.setCr_cnt(rs.getString("cr_cnt"));
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
        return chat_recordVO;
    }

    @Override
    public List<Chat_RecordVO> getAll() {

        List<Chat_RecordVO> list = new ArrayList<Chat_RecordVO>();
        Chat_RecordVO chat_recordVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                chat_recordVO = new Chat_RecordVO();
                chat_recordVO.setCr_no(rs.getString("cr_no"));
                chat_recordVO.setCf_no(rs.getString("cf_no"));
                chat_recordVO.setCg_no(rs.getString("cg_no"));
                chat_recordVO.setCr_date(rs.getDate("cr_date"));
                chat_recordVO.setCr_cnt(rs.getString("cr_cnt"));
                list.add(chat_recordVO); // Store the row in the list
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

        Chat_RecordJDBCDAO dao = new Chat_RecordJDBCDAO();

        // �s�W 
//        Chat_RecordVO chat_recordVO1 = new Chat_RecordVO();
//        chat_recordVO1.setCf_no("CF000004");
//        chat_recordVO1.setCr_cnt("��ѰO������*");
//        dao.insert(chat_recordVO1);
//        System.out.println("�s�W���\");

        // �R�� OK
//		dao.delete("CR000006");
//		System.out.println("delete");

        // �d�� OK
//		Chat_RecordVO chat_recordVO3 = dao.findByPrimaryKey("CR000001");
//		System.out.print(chat_recordVO3.getCr_no() + ",");
//		System.out.print(chat_recordVO3.getCf_no() + ",");
//		System.out.print(chat_recordVO3.getCg_no() + ",");
//		System.out.print(chat_recordVO3.getCr_date() + ",");
//		System.out.println(chat_recordVO3.getCr_cnt());
//		System.out.println("---------------------");

        // �d�ߥ��� OK
//		List<Chat_RecordVO> list = dao.getAll();
//		for (Chat_RecordVO chat_recordVO : list) {
//			System.out.print(chat_recordVO.getCr_no() + ",");
//			System.out.print(chat_recordVO.getCf_no() + ",");
//			System.out.print(chat_recordVO.getCg_no() + ",");
//			System.out.print(chat_recordVO.getCr_date() + ",");
//			System.out.print(chat_recordVO.getCr_cnt());
//			System.out.println();
//		}

    }
}