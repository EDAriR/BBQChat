package com.chat.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface Chat_Group_ItemDAO_interface {

    public void insert(Chat_Group_ItemVO chat_group_itemVO);

    public void delete(String cg_no, String mem_no);

    public List<Chat_Group_ItemVO> findByCgNo(String cg_no);
    
    public List<Chat_Group_ItemVO> findByMemNo(String mem_no);

    public List<Chat_Group_ItemVO> getAll();

}
