package com.chat.model;

import java.sql.Timestamp;
import java.util.List;


public class User_ReportService {

    private User_ReportDAO_interface dao;

    public User_ReportService() {
        dao = new User_ReportDAO();
    }

    public User_ReportVO addUser_Report(String mem_no_ed, String mem_no_ing,
                                       String urpt_cnt, Timestamp urpt_date,
                                       String urpt_rsn, String urpt_is_cert,
                                       String urpt_unrsn) {

        User_ReportVO user_ReportVO = new User_ReportVO();

//      cf_no  mem_no_s  mem_no_o  cf_is_del
        user_ReportVO.setMem_no_ed(mem_no_ed);
        user_ReportVO.setMem_no_ing(mem_no_ing);
        user_ReportVO.setUrpt_cnt(urpt_cnt);
        user_ReportVO.setUrpt_date(urpt_date);
        user_ReportVO.setUrpt_rsn(urpt_rsn);
        user_ReportVO.setUrpt_is_cert(urpt_is_cert);
        user_ReportVO.setUrpt_unrsn(urpt_unrsn);
        dao.insert(user_ReportVO);

        return user_ReportVO;
    }

    public User_ReportVO updateUser_Report(String mem_no_ed, String mem_no_ing,
                                          String urpt_is_cert, String urpt_unrsn) {

        User_ReportVO user_ReportVO = new User_ReportVO();

        user_ReportVO.setMem_no_ed(mem_no_ed);
        user_ReportVO.setMem_no_ing(mem_no_ing);
        user_ReportVO.setUrpt_is_cert(urpt_is_cert);
        user_ReportVO.setUrpt_unrsn(urpt_unrsn);

        dao.update(user_ReportVO);

        return user_ReportVO;
    }

    public User_ReportVO getOneUserRepor(String mem_no_ed, String mem_no_ing) {
        return dao.findByPrimaryKey(mem_no_ed, mem_no_ing);
    }

    public List<User_ReportVO> getAll() {
        return dao.getAll();
    }
}
