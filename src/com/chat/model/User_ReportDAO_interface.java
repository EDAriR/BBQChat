package com.chat.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface User_ReportDAO_interface {

    public void insert(User_ReportVO user_reportVO);
    public void update(User_ReportVO user_reportVO);
    public User_ReportVO findByPrimaryKey(String mem_no_ed, String mem_no_ing);
    public List<User_ReportVO> getAll();

}
