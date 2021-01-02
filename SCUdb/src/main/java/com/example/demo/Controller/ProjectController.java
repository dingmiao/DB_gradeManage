package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Controller
public class ProjectController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/1")
    public String login(@RequestParam("user_name") String user_name, @RequestParam("user_password") String user_password,
                        Map<String, Object> map) {
        System.out.println(user_name);
        if (userService.checkLogin(user_name, user_password)) {
            String user_type = userService.getType(user_name);
            Integer user_id = userService.findId(user_name);
            map.put("type", user_type);
            map.put("t", user_name);
            map.put("id", user_id);
            if( user_type.equals("student"))
                return "manage";
            else if(user_type.equals("teacher"))
                return "teacher";
            else if(user_type.equals("admin"))
                return "admin";
        }
        map.put("t", "账号或者密码错误");
        return "index";
    }

    @RequestMapping(value = "/resign")
    @ResponseBody
    public String resign(@RequestParam("name") String user_name, @RequestParam("password") String user_password,
                         @RequestParam("check_password") String check_password,
                         @RequestParam("department") String department,
                         @RequestParam("tname") String tname,
                         @RequestParam("check") String check) {
        if( ! check.equals(userService.findValue("schoolkey"))) return "验证码错误，请联系学校管理员";
        if (userService.findUser(user_name)) return "用户已存在！";
        if (user_password.length() <= 0 || user_password.length() > 9) return "密码位数应是0-9位";
        String pattern = "[0-9a-z]*";
        if (!Pattern.matches(pattern, user_password)) return "密码只能包含数字和字母";
        if (user_password.equals(check_password)) {
            userService.addStudent(user_name, user_password,tname,department);
            return "注册成功,请返回登录";
        }
        return "两次输入不一样";

    }
    @RequestMapping("addTeacher")
    public String addTeacher(@RequestParam("name") String name,@RequestParam("password") String pwd,
                             @RequestParam("department") String department,
                             @RequestParam("tname") String tname, Model model){

        if (userService.findUser(name)) {
            model.addAttribute("msg","用户已存在！");
            return "admin";
        }
        if (pwd.length() <= 0 || pwd.length() > 9) {

            model.addAttribute("msg","密码位数应是0-9位");
            return "admin";
        }
        String pattern = "[0-9a-z]*";
        if (!Pattern.matches(pattern, pwd)) {
            model.addAttribute("msg","密码只能包含数字和字母");
            return "admin";
        }
        userService.addTeacher(name,pwd,tname,department);
        model.addAttribute("msg","成功");
        model.addAttribute("type","admin");
        return "admin";
    }

    @RequestMapping("changepwd")
    @ResponseBody
    public String changepwd(@RequestParam("type") String type,
                            @RequestParam("id") Integer id,
                            @RequestParam("t") String t,
                            @RequestParam("pwd1") String pwd1,
                            @RequestParam("pwd2") String pwd2
            ,Map<String, Object> map){
        if(!pwd1.equals(pwd2)) return "两次密码不一致";
        if (pwd1.length() <= 0 || pwd1.length() > 9) return "密码位数应是0-9位";
        String pattern = "[0-9a-z]*";
        if (!Pattern.matches(pattern, pwd1)) return "密码只能包含数字和字母";
        userService.changePwd(t,pwd1);
        return "修改成功";
    }


}



