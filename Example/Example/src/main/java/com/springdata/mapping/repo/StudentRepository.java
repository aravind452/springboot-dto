package com.springdata.mapping.repo;


import com.springdata.mapping.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String name);


    public List<Student> findByLastNameNotNull();


    public List<Student> findByGuardianName(String guardianName);


    // JPQL
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);


    // NATIVE QUERY
    @Query(value = "select * from tbl_student s where s.email_address = ?1", nativeQuery = true)
    public Student getStudentByEmailAddressNative(String emailId);


    @Query(value = "select * from tbl_student s where s.email_address = :emailId", nativeQuery = true)
    public Student getStudentByEmailAddressNativeNamed(@Param("emailId") String emailID);

    @Modifying
    @Transactional
    @Query(value = "update tbl_student set first_name =?1 where email_address = ?2", nativeQuery = true)
    int updateStudentByEmailId(String firstName, String emailId);


}
