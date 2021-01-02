package com.example.demo.Service;

import com.example.demo.Reporsitory.UserReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "UserService")
public class UserService {

    @Autowired
    private UserReporsitory userReporsitory;

    public boolean checkLogin(String name, String pwd) {
        String tpwd = ((Integer)name.concat(pwd).hashCode()).toString();
        String passwd = userReporsitory.findName(name);
        if (tpwd.equals(passwd))
            return true;
        else return false;
    }

    public boolean findUser(String user) {
        String name = userReporsitory.findUser(user);
        if (name == null) return false;
        else return true;
    }
    public Integer findId(String user){
        return userReporsitory.findId(user);
    }

    public void addStudent(String name, String pwd,String tname,String department) {
        String password = ((Integer)name.concat(pwd).hashCode()).toString();
        userReporsitory.addStudent(name, password, tname,department);
    }
    public void addTeacher(String name, String pwd,String tname,String department) {
        String password = ((Integer)name.concat(pwd).hashCode()).toString();
        userReporsitory.addTeacher(name, password, tname,department);
    }

    public String getType(String name) {
        return userReporsitory.getType(name);
    }

    public void changePwd(String name, String pwd){
        String tpwd = ((Integer)name.concat(pwd).hashCode()).toString();
        userReporsitory.changePwd(name,tpwd);
    }
    public String findtName(Integer id){
        return userReporsitory.findtNameById(id);
    }

    public String findtNameById(Integer id){
        return userReporsitory.findtNameById(id);
    }

    public String findValue(String key){
        return userReporsitory.findValue(key);
    }
    public void setVa(String key, String va){
        userReporsitory.setVa(key,va);
    }
    public List<Map<String,Object>> findAllc(){
        return userReporsitory.findAllc();
    }
}
