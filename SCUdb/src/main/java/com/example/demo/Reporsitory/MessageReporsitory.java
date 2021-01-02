package com.example.demo.Reporsitory;

import com.example.demo.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface MessageReporsitory extends JpaRepository<Message,Integer> {

    @Query(value = "select * from message where recevier =?",nativeQuery = true)
    List<Map<String,Object>> findAllByRecevier(Integer recevier);

    @Query(value = "select * from message where sender =?",nativeQuery = true)
    List<Map<String,Object>> findAllBySender(Integer sender);

    @Modifying
    @Transactional
    @Query(value = "insert into message(sender,recevier,msg,time) values(?1,?2,?3,?4)",nativeQuery = true)
    void addMsg(Integer sender,Integer recevier,String msg,String time);
}
