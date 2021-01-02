package com.example.demo.Reporsitory;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface UserReporsitory extends JpaRepository<User,Integer> {
    @Query(value = "select password from user where name =?", nativeQuery = true)
    String findName(String password);

    @Query(value = "select name from user where name =?", nativeQuery = true)
    String findUser(String user);

    @Query(value = "select id from user where name =?", nativeQuery = true)
    Integer findId(String user);

    @Modifying
    @Transactional
    @Query(value = "insert into user(name,password,type,true_name,department) values(?1,?2,'student',?3,?4)", nativeQuery = true)
    void addStudent(String name, String pwd,String tname,String department);

    @Modifying
    @Transactional
    @Query(value = "insert into user(name,password,type,true_name,department) values(?1,?2,'teacher',?3,?4)", nativeQuery = true)
    void addTeacher(String name, String pwd,String tname,String department);

    @Modifying
    @Transactional
    @Query(value = "update user set password = ?2 where name =?1",nativeQuery = true)
    void changePwd(String name, String pwd);

    @Query(value = "select type from user where Name=?", nativeQuery = true)
    String getType(String name);
    @Query(value = "select true_name from user where id =?",nativeQuery = true)
    String findtNameById(Integer id);

    @Query(value = "select va from control where the_key =?",nativeQuery = true)
    String findValue(String key);

    @Query(value = "select * from control",nativeQuery = true)
    List<Map<String,Object>> findAllc();
    @Modifying
    @Transactional
    @Query(value = "update control set va = ?2 where the_key = ?1",nativeQuery = true)
    void setVa(String key,String va);
}
