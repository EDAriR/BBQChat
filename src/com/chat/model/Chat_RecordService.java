package com.chat.model;

import com.chat.dao.Chat_RecordDAO;

import java.sql.Date;
import java.util.List;


public class Chat_RecordService {

    private Chat_RecordDAO_interface dao;

    public Chat_RecordService() {
        dao = new Chat_RecordDAO();
    }

    public Chat_RecordVO addChat_Record(String cr_no,String cf_no,String cg_no,
                                        Date cr_date, String cr_cnt) {

        Chat_RecordVO chat_RecordVO = new Chat_RecordVO();
        
//      cf_no  mem_no_s  mem_no_o  cf_is_del
        chat_RecordVO.setCr_no(cr_no);
        chat_RecordVO.setCf_no(cf_no);
        chat_RecordVO.setCg_no(cg_no);
        chat_RecordVO.setCr_date(cr_date);
        chat_RecordVO.setCr_cnt(cr_cnt);
        dao.insert(chat_RecordVO);

        return chat_RecordVO;
    }

    public Chat_RecordVO updateChat_Record(String cr_no, String cr_cnt) {

        Chat_RecordVO chat_RecordVO = new Chat_RecordVO();

        chat_RecordVO.setCf_no(cr_no);
        chat_RecordVO.setCr_cnt(cr_cnt);

        dao.update(chat_RecordVO);

        return chat_RecordVO;
    }

    public void deleteCR(String cr_no) {
        dao.delete(cr_no);
    }

    public Chat_RecordVO getOneCR(String cr_no) {
        return dao.findByPrimaryKey(cr_no);
    }

    public List<Chat_RecordVO> getAll() {
        return dao.getAll();
    }
}
