package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.persistence.entity.Guardian;
import com.demo.spring.data.jpa.persistence.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
// @DataJpaTest --> helps to repo layer and once completed will flush the data
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("aakash@gmail.com")
                .firstName("Aakash")
                .lastName("Balu")
                //.guardianName("Sakthivel")
                //.guardianEmail("sakthi@gmail.com")
                //.guardianMobile("9524322355")
                .build();

        studentRepository.save(student); //save method of JpaRepository that extends CrudRepository
    }

    @Test
    public void saveStudentWithGuardianDetails() {

        Guardian guardian = Guardian.builder()
                .name("Syed")
                .email("Syed@gmail.com")
                .mobile("9865662781")
                .build();

        Student student = Student.builder()
                .firstName("Pradeep")
                .lastName("Murugan")
                .emailId("Pradeep@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("StudentList =" + studentList);
    }

    @Test
    public void printStudentByFirstName() {

        List<Student> students =
                studentRepository.findByFirstName("Aakash");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {

        List<Student> students =
                studentRepository.findByFirstNameContaining("sh");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByLastNameNotNull() {

        List<Student> students =
                studentRepository.findByLastNameNotNull();

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName() {

        List<Student> students =
                studentRepository.findByGuardianName("Syed");

        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "aakash@gmail.com"
                );
        System.out.println("students = " + student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress() {
        String student =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "aakash@gmail.com"
                );
        System.out.println("FirstName = " + student);
    }

    @Test
    public void printGetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "aakash@gmail.com"
                );
        System.out.println("Student =" + student);
    }
}