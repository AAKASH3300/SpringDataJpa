package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.persistence.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // this interface is a repository interface
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Jpa Custom methods
    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    //JPQL --> Based on the classes we have created not based on the tables in the database
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //NativeQuery for the same getStudentByEmailAddress method
    //Whenever we need have a complex action we can go for native query
    @Query(
            value = " SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native Named Param
    @Query(
            value = " SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String emailId
    );

    //---------------------Update the records-------------------------

    @Modifying // to make this query to allow changes in the database
    @Transactional // all the method that perform some transaction we use transaction and after the transaction/method is complete it will be committed
    //Mostly it will be added on service layer , if there occurs any error the transaction will roll back
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName,String emailId);


}
