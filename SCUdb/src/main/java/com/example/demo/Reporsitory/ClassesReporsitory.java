package com.example.demo.Reporsitory;

import com.example.demo.Entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface ClassesReporsitory extends JpaRepository<Classes,Integer> {
    @Query(value = "select classes.*,user.true_name from user,classes where user.id = classes.teacher_id and class_name =?", nativeQuery = true)
    List<Map<String,Object>> findClassByName(String className);
    @Query(value = "select classes.* from classes where teacher_name =?", nativeQuery = true)
    List<Map<String,Object>> findClassByTeacher(String teacher);
    @Query(value = "select user.true_name,classes.*,grade.grade from user,classes,grade where user.id = grade.student_id and classes.id = grade.class_id and name =?", nativeQuery = true)
    List<Map<String,Object>> findByStudent(String studentName);
    @Query(value = "select user.true_name,classes.*,grade.grade from user,classes,grade where user.id = grade.student_id and classes.id = grade.class_id and name =?1 and term = ?2", nativeQuery = true)
    List<Map<String,Object>> findByStudentTerm(String studentName,String term);

    @Query(value = "select * from classes where teacher_id = ?",nativeQuery = true)
    List<Map<String,Object>> findClassesByTeacher_id(Integer id);

    @Query(value = "select * from grade where class_id =?",nativeQuery = true)
    List<Map<String,Object>> findAllByClassId(Integer id);
    @Query(value = "select id from grade where class_id=?1 and student_id =?2",nativeQuery = true)
    Integer checkChoose(Integer class_id,Integer student_id);
    @Query(value = "select id from classes where department = ?1 and class_name =?2 and teacher_id = ?3 ",nativeQuery = true)
    Integer findClassId(String department, String class_name, Integer teacher_id);

    @Query(value = "SELECT classes.*,grade.grade from classes,grade WHERE classes.id = grade.class_id and student_id = ?",nativeQuery = true)
    List<Map<String,Object>> findGradeBySid(Integer id);
    @Query(value = "SELECT classes.*,grade.grade from classes,grade WHERE classes.id = grade.class_id and student_id = ?1 and class_name =?2",nativeQuery = true)
    List<Map<String,Object>> findGradeBySidAndName(Integer id,String class_name);

    @Modifying
    @Transactional
    @Query(value = "update grade set grade = ?1 where class_id = ?2 and student_id = ?3",nativeQuery = true)
    void setGrade(Double grade, Integer class_id, Integer student_id);

    @Modifying
    @Transactional
    @Query( value = "insert into grade(class_id, student_name,student_id) values(?1,?2,?3) ",nativeQuery = true)
    void chooseClass(Integer class_id, String student_name,Integer student_id);

    @Modifying
    @Transactional
    @Query(value = "insert into classes(department, class_name,teacher_name,teacher_id,term,time,type,credit,classroom) values(?1,?2,?3,?4,?5,?6,?7,?8,?9) ",nativeQuery = true)
    void addClass(String department, String class_name, String teacher_name, Integer teacher_id, String term, String time,String type,Float credit,String classroom);
    
    @Modifying
    @Transactional
    @Query(value = "delete from grade where class_id =?1 and student_id = ?2",nativeQuery = true)
    void deleteStudent(Integer class_id, Integer student_id);

    @Modifying
    @Transactional
    @Query(value = "delete from grade where class_id =?1",nativeQuery = true)
    void deleteClasss(Integer class_id);

    @Modifying
    @Transactional
    @Query(value = "delete from classes where id =?1",nativeQuery = true)
    void deleteClasss2(Integer class_id);

    @Query(value = "select credit from user where id =?",nativeQuery = true)
    Float findCredit(Integer id);
    @Modifying
    @Transactional
    @Query(value = "update user set credit = ?2 where id = ?1",nativeQuery = true)
    void changeCredit(Integer id, Float credit);

    @Query(value = "select grade from grade where class_id=?1 and student_id = ?2",nativeQuery = true)
    Double findPassedGrade(Integer class_id, Integer student_id);
}
