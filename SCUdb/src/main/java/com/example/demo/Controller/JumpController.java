package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class JumpController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String getHtml() {
        return "index";
    }

    @RequestMapping(value = "/test")
    public String get() {
        return "test";
    }
    @RequestMapping(value = "/admin")
    public String get1(Model model) {
        model.addAttribute("type","admin");
        return "admin";
    }
    @RequestMapping(value = "/adminControl")
    public String get1223(Map<String, Object> map) {
        map.put("type","admin");
        return "adminControl";
    }
    @RequestMapping(value = "/adminC")
    public String get123(Map<String, Object> map) {
        List<Map<String, Object>> res = userService.findAllc();
        map.put("type","admin");
        map.put("res", res);
        return "adminControl";
    }
    @RequestMapping(value = "/set_the_key")
    @ResponseBody
    public String get213(@RequestParam("key") String key,@RequestParam("va") String va,Model model) {
        userService.setVa(key,va);
        return "成功";
    }
    public String get2(Model model) {
        model.addAttribute("type","admin");
        return "adminControl";
    }
    @RequestMapping(value = "/teacher")
    public String get1(@RequestParam("type") String type,
                       @RequestParam("id") Integer id,
                       @RequestParam("t") String t, Model model) {
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("t",t);
        return "teacher";
    }

    @RequestMapping(value = "/tosetGrade")
    public String get2(@RequestParam("type") String type,
                       @RequestParam("id") Integer id,
                       @RequestParam("t") String t, Model model) {
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("t",t);
        return "setGrade";
    }
    @RequestMapping(value = "/toChangePwd")
    public String get32(@RequestParam("type") String type,
                       @RequestParam("id") Integer id,
                       @RequestParam("t") String t, Model model) {
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("t",t);
        return "changepwd";
    }
    @RequestMapping(value = "/toChangePwd2")
    public String get321(@RequestParam("type") String type,
                        @RequestParam("id") Integer id,
                        @RequestParam("t") String t, Model model) {
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("t",t);
        return "changepwd2";
    }
    @RequestMapping(value = "/tomanage")
    public String get3(@RequestParam("type") String type,
                       @RequestParam("id") Integer id,
                       @RequestParam("t") String t, Model model) {
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("t",t);
        return "manage";
    }
    @RequestMapping(value = "/tosearch")
    public String get4(@RequestParam("type") String type,
                       @RequestParam("id") Integer id,
                       @RequestParam("t") String t, Model model) {
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("t",t);
        return "searchGrade";
    }

}
