package com.example.demo.Service;

import com.example.demo.Reporsitory.MessageReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    private MessageReporsitory messageReporsitory;
    public List<Map<String,Object>> findAllByRecevier(Integer recevier){
        return messageReporsitory.findAllByRecevier(recevier);
    }
    public List<Map<String,Object>> findAllBySender(Integer sender){
        return messageReporsitory.findAllBySender(sender);
    }
    public void addMsg(Integer sender,Integer recevier,String msg,String time){
        messageReporsitory.addMsg(sender,recevier,msg,time);
    }
}
