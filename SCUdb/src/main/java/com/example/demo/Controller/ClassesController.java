package com.example.demo.Controller;

import com.example.demo.Service.ClassesService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class ClassesController {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findClassByName")
    public String findClassByName(@RequestParam("className") String className,
                                  @RequestParam("type") String type,
                                  @RequestParam("id") Integer id,
                                  @RequestParam("t") String t
                                  ,Map<String, Object> map){
        List<Map<String, Object>> res = classesService.findClassByName(className);
        map.put("type",type);
        map.put("id",id);
        map.put("t",t);
        map.put("res", res);
        return "manage";
    }

    @RequestMapping(value = "/findClassByTeacher")
    public String findClassByTeacher(@RequestParam("className") String className,
                                  @RequestParam("type") String type,
                                  @RequestParam("id") Integer id,
                                  @RequestParam("t") String t,Map<String, Object> map){
        List<Map<String, Object>> res= classesService.findClassByTeacher(className);
        map.put("type",type);
        map.put("id",id);
        map.put("t",t);
        map.put("res", res);
        return "manage";
    }

    @RequestMapping(value ="chooseClass")
    @ResponseBody
    public String choose(@RequestParam("sid") Integer sid, @RequestParam("class_id") Integer class_id) {
        if (classesService.checkChoose(class_id, sid) == null) {
            String tname = userService.findtNameById(sid);
            classesService.chooseClass(class_id, tname, sid);
            return "选课成功";
        }
        else {
            return "课程已经被选择";
        }
    }

    @RequestMapping(value="addClass")
    @ResponseBody
    public String addClass(@RequestParam("name") String name, @RequestParam("department") String department,
                           @RequestParam("term") String term, @RequestParam("time") String time,
                           @RequestParam("teacher_id") Integer id,@RequestParam("class_type") String class_type,
                           @RequestParam("credit") Float credit,@RequestParam("classroom") String classroom){
        if(classesService.findClassId(department,name,id) != null)
            return "课程已经存在";
        String teacher_name = userService.findtNameById(id);
        classesService.addClass(department,name,teacher_name,id,term,time,class_type,credit,classroom);
        return "添加成功";
    }

    @RequestMapping(value = "/findClassesByTeacher_id")
    public String findClassesByTeacher_id(@RequestParam("type") String type,
                                  @RequestParam("id") Integer id,
                                  @RequestParam("t") String t
                                    ,Map<String, Object> map){
        List<Map<String, Object>> res = classesService.findClassesByTeacher_id(id);
        map.put("type",type);
        map.put("id",id);
        map.put("t",t);
        map.put("res", res);
        return "setGrade";
    }

    @RequestMapping(value = "/findAllByClassId")
    public String findAllByClassId(@RequestParam("type") String type,
                                          @RequestParam("id") Integer id,
                                          @RequestParam("t") String t,
            @RequestParam("class_id") Integer class_id,@RequestParam("credit") Float credit
            ,Map<String, Object> map){
        List<Map<String, Object>> res = classesService.findAllByClassId(class_id);
        map.put("type",type);
        map.put("id",id);
        map.put("t",t);
        map.put("res", res);
        map.put("credit",credit);
        return "setGrade2";
    }

    @RequestMapping(value = "/setGrade")
    @ResponseBody
    public String setGrade(@RequestParam("type") String type,
                           @RequestParam("id") Integer id,
                           @RequestParam("t") String t,
                           @RequestParam("class_id") Integer class_id,
                           @RequestParam("student_id") Integer student_id,
                            @RequestParam("grade") Double grade,
                            @RequestParam("credit") Float credit
            ,Map<String, Object> map){
        if(grade<0||grade>100) return "成绩不合理";
        classesService.setGrade(grade,class_id,student_id,credit);
        return "成功";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(MultipartFile file,Float credit){
        //解析excel文件
        List<ArrayList<String>> row = Analysis.analysis(file);
        //打印信息
        /*for (int i = 0;i<row.size();i++){
            List<Object> cell = row.get(i);
            for (int j = 0;j<cell.size();j++){
                System.out.print(cell.get(j)+" ");
            }
            System.out.println();
        }*/

        for (int i = 0;i<row.size();i++){
            Double grade = Double.parseDouble(row.get(i).get(4));
            Integer class_id = Integer.parseInt(row.get(i).get(0));
            Integer student_id = Integer.parseInt(row.get(i).get(2));
            classesService.setGrade(grade,class_id,student_id,credit);
        }
        return "成功,请刷新查看";
    }

    @RequestMapping(value = "/findGradeBySid")
    public String findGradeBySid(@RequestParam("type") String type,
                                 @RequestParam("id") Integer id,
                                 @RequestParam("t") String t
            ,Map<String, Object> map){
        List<Map<String, Object>> res = classesService.findGradeBySid(id);
        Float scredit = classesService.findCredit(id);
        map.put("type",type);
        map.put("id",id);
        map.put("t",t);
        map.put("res", res);
        map.put("scredit",scredit);
        return "searchGrade";
    }
    @RequestMapping(value = "/findGradeBySidAndName")
    public String findGradeBySidAndName(@RequestParam("type") String type,
                                 @RequestParam("id") Integer id,
                                 @RequestParam("t") String t, @RequestParam("className") String name
            ,Map<String, Object> map){
        List<Map<String, Object>> res = classesService.findGradeBySidAndName(id,name);
        map.put("type",type);
        map.put("id",id);
        map.put("t",t);
        map.put("res", res);
        return "searchGrade";
    }

    @RequestMapping(value = "deleteStudent")
    @ResponseBody
    public String deleteStudent(@RequestParam Integer class_id,@RequestParam Integer student_id,
                                @RequestParam String code){

        if(code.equals(userService.findValue("deleteStudent"))){
            classesService.deleteStudent(class_id,student_id);
            return "成功";
        }
        else return "代码错误";
    }

    @RequestMapping(value = "/deleteClass")
    public String deleteC(@RequestParam Integer class_id,Map<String, Object> map){

        map.put("type","admin");
        if(classesService.findAllByClassId(class_id).size() == 0)
        {
            map.put("msg","无该课程");
            return "admin";
        }
        classesService.deleteClasss(class_id);
        map.put("msg","成功");
        return "admin";
    }
}
