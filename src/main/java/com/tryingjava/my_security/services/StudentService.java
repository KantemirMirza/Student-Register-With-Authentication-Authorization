package com.tryingjava.my_security.services;

import com.tryingjava.my_security.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    void createStudent(Student student);

    Student getStudentById(Long id);

    void deleteStudentById(Student student);
}
