package com.chat.model;

import com.chat.dao.Chat_Group_ItemDAO;

import java.util.List;


public class Chat_Group_ItemService {

    private Chat_Group_ItemDAO_interface dao;

    public Chat_Group_ItemService() {
        dao = new Chat_Group_ItemDAO();
    }

    public Chat_Group_ItemVO addChat_Friend(String cg_no, String mem_no) {

        Chat_Group_ItemVO chat_Group_ItemVO = new Chat_Group_ItemVO();
        
//      cg_no, mem_no
        chat_Group_ItemVO.setCg_no(cg_no);
        chat_Group_ItemVO.setMem_no(mem_no);

        dao.insert(chat_Group_ItemVO);

        return chat_Group_ItemVO;
    }

    public void deleteCF(String cg_no,String mem_no) {
        dao.delete(cg_no,mem_no);
    }

    public Chat_Group_ItemVO getOneCF(String cg_no,String mem_no) {
        return dao.findByPrimaryKey(cg_no,mem_no);
    }

    public List<Chat_Group_ItemVO> getAll() {
        return dao.getAll();
    }
}
