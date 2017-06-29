package com.chat.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface Chat_FriendDAO_interface {
    public void insert(Chat_FriendVO cf_no);
    public void update(Chat_FriendVO cf_no);
    public Chat_FriendVO findByPrimaryKey(String cf_no);
    public List<Chat_FriendVO> findByMemNoS(String mem_no_s);
    public List<Chat_FriendVO> getAll();
}
