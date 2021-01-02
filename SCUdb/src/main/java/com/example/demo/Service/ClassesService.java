package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Reporsitory.ClassesReporsitory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@Service(value = "ClassesService")
public class ClassesService {
    @Autowired
    private ClassesReporsitory classesReporsitory;

    public List<Map<String,Object>> findClassByName(String className){ //传入课程名称 返回该课程名的所有老师和课程信息
        List<Map<String,Object>> res = classesReporsitory.findClassByName(className);
        return res;
    }

    public List<Map<String,Object>> findClassByTeacher(String teacher){ //传入教师名称 返回该老师的所有课程信息
        List<Map<String,Object>> res = classesReporsitory.findClassByTeacher(teacher);
        return res;
    }
    public List<Map<String,Object>> findClassByStudent(String studentName){ // 传入学生账号  返回学生所有课程的信息
        List<Map<String,Object>> res = classesReporsitory.findByStudent(studentName);
        return res;
    }
    public List<Map<String,Object>> findByStudentTerm(String studentName,String term){ //传入学生账号和学期 返货学生所有课程信息
        List<Map<String,Object>> res = classesReporsitory.findByStudentTerm(studentName,term);
        return res;
    }

    public void setGrade(Double grade, Integer class_id, Integer student_id,Float credit){  //设置学生成绩
        Double lastGrade = classesReporsitory.findPassedGrade(class_id,student_id);

         if(lastGrade!=null &&(((lastGrade<60)&&(grade>=60))||((lastGrade>=60)&&(grade<60)))) {
                if (grade >= 60)
                    changeCredit(student_id, credit, true);
                else
                    changeCredit(student_id, credit, false);
        }
         if(lastGrade == null && grade >= 60)
             changeCredit(student_id, credit, true);
        classesReporsitory.setGrade(grade, class_id,student_id);
    }
    public void chooseClass(Integer class_id, String student_name,Integer student_id){
        classesReporsitory.chooseClass(class_id,student_name,student_id);
    }
    public void addClass(String department, String class_name, String teacher_name, Integer teacher_id, String term, String time,String type,Float credit,String classroom){
        classesReporsitory.addClass(department,class_name,teacher_name,teacher_id,term,time,type,credit,classroom);
    }
    public Integer findClassId(String department, String class_name, Integer teacher_id){
        return classesReporsitory.findClassId(department,class_name,teacher_id);
    }

    public Integer checkChoose(Integer class_id,Integer student_id){
        return classesReporsitory.checkChoose(class_id,student_id);
    }
    public List<Map<String,Object>> findClassesByTeacher_id(Integer id){
        return classesReporsitory.findClassesByTeacher_id(id);
    }
    public List<Map<String,Object>> findAllByClassId(Integer id){
        return classesReporsitory.findAllByClassId(id);
    }
    public List<Map<String,Object>> findGradeBySid(Integer id){
        return classesReporsitory.findGradeBySid(id);
    }
    public List<Map<String,Object>> findGradeBySidAndName(Integer id,String class_name){
        return classesReporsitory.findGradeBySidAndName(id,class_name);
    }
    public void deleteStudent(Integer class_id, Integer student_id){
        classesReporsitory.deleteStudent(class_id,student_id);
    }

    public void deleteClasss(Integer class_id){
        classesReporsitory.deleteClasss(class_id);
        classesReporsitory.deleteClasss2(class_id);
    }
    public Float findCredit(Integer id){
        return classesReporsitory.findCredit(id);
    }
    public void changeCredit(Integer id, Float credit, boolean pass){
        Float now = classesReporsitory.findCredit(id);
        if(pass)
            now += credit;
        else
            now-=credit;
        classesReporsitory.changeCredit(id,now);
    }
}
