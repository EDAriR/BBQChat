package com.chat.model;

import java.sql.Date;
import java.util.List;


public class Chat_GroupService {

    private Chat_GroupDAO_interface dao;

    public Chat_GroupService() {
        dao = new Chat_GroupDAO();
    }

    public Chat_GroupVO addCG(String cg_name, Date cg_year,
                              String cg_is_ar, String cg_is_ab, String cg_is_ac,
                              String cg_is_sf, String cg_is_ad, String cg_baby_rd) {

        Chat_GroupVO chat_GroupVO = new Chat_GroupVO();

//      cg_no, cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad, baby_rd

        chat_GroupVO.setCg_name(cg_name);
        chat_GroupVO.setCg_year(cg_year);
        chat_GroupVO.setCg_is_ar(cg_is_ar);
        chat_GroupVO.setCg_is_ab(cg_is_ab);
        chat_GroupVO.setCg_is_ac(cg_is_ac);
        chat_GroupVO.setCg_is_sf(cg_is_sf);
        chat_GroupVO.setCg_is_ad(cg_is_ad);
        chat_GroupVO.setCg_baby_rd(cg_baby_rd);

        dao.insert(chat_GroupVO);

        return chat_GroupVO;
    }

    public Chat_GroupVO updateCG(String cg_no, String cg_name, Date cg_year,
                                 String cg_is_ar, String cg_is_ab, String cg_is_ac,
                                 String cg_is_sf, String cg_is_ad, String cg_baby_rd) {

        Chat_GroupVO chat_GroupVO = new Chat_GroupVO();

        chat_GroupVO.setCg_no(cg_no);
        chat_GroupVO.setCg_name(cg_name);
        chat_GroupVO.setCg_year(cg_year);
        chat_GroupVO.setCg_is_ar(cg_is_ar);
        chat_GroupVO.setCg_is_ab(cg_is_ab);
        chat_GroupVO.setCg_is_ac(cg_is_ac);
        chat_GroupVO.setCg_is_sf(cg_is_sf);
        chat_GroupVO.setCg_is_ad(cg_is_ad);
        chat_GroupVO.setCg_is_ad(cg_is_ad);
        chat_GroupVO.setCg_baby_rd(cg_baby_rd);
        dao.update(chat_GroupVO);

        return chat_GroupVO;
    }

    public void deleteCF(String cg_no) {
        dao.delete(cg_no);
    }

    public Chat_GroupVO getOneCG(String cg_no) {
        return dao.findByPrimaryKey(cg_no);
    }

    public List<Chat_GroupVO> getAll() {
        return dao.getAll();
    }
}
