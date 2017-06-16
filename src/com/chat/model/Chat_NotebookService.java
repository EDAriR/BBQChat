package com.chat.model;

import java.util.List;


public class Chat_NotebookService {

    private Chat_NotebookDAO_interface dao;

    public Chat_NotebookService() {
        dao = new Chat_NotebookDAO();
    }

    public Chat_NotebookVO addChat_Friend(String cnb_no,
           String cf_no, String cg_no, String cnb_cnt) {

        Chat_NotebookVO chat_NotebookVO = new Chat_NotebookVO();
        
//      cf_no  mem_no_s  mem_no_o  cf_is_del
        chat_NotebookVO.setCnb_no(cnb_no);
        chat_NotebookVO.setCf_no(cf_no);
        chat_NotebookVO.setCg_no(cg_no);
        chat_NotebookVO.setCnb_cnt(cnb_cnt);
        dao.insert(chat_NotebookVO);

        return chat_NotebookVO;
    }

    public Chat_NotebookVO updateChat_Friend(String cnb_no,String cnb_cnt) {

        Chat_NotebookVO chat_NotebookVO = new Chat_NotebookVO();

        chat_NotebookVO.setCg_no(cnb_no);
        chat_NotebookVO.setCnb_cnt(cnb_cnt);

        dao.update(chat_NotebookVO);

        return chat_NotebookVO;
    }

    public void deleteCF(String cnb_no) {
        dao.delete(cnb_no);
    }

    public Chat_NotebookVO getOneCF(String cnb_no) {
        return dao.findByPrimaryKey(cnb_no);
    }

    public List<Chat_NotebookVO> getAll() {
        return dao.getAll();
    }
}
