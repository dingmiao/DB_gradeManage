package com.example.demo.Controller;

import com.example.demo.Service.ClassesService;
import com.example.demo.Service.MessageService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassesService classesService;

    @RequestMapping("addmsg")
    @ResponseBody
    public String add1(@RequestParam("r_id") Integer rid,
                     @RequestParam("text") String text,
                     @RequestParam("sid") Integer sid){
        if(userService.findtNameById(rid)==null) return "用户不存在";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messageService.addMsg(sid,rid,text,formatter.format(date));
        return "发送成功";
    }
    @RequestMapping("receive_msg")
    @ResponseBody
    public List<Map<String,Object>> rece(@RequestParam("id") Integer id){
        return messageService.findAllByRecevier(id);
    }

    @RequestMapping("addannonce")
    @ResponseBody
    public String add2(@RequestParam("c_id") Integer cid,
                       @RequestParam("text") String text,
                       @RequestParam("sid") Integer sid){
        List<Map<String,Object>> classes = classesService.findAllByClassId(cid);
        if(classes == null ) return "课程不存在";
        for(int i = 0; i<classes.size();++i) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            messageService.addMsg(sid, (Integer) classes.get(i).get("student_id"), text, formatter.format(date));
        }
        return "发送成功";
    }

}
