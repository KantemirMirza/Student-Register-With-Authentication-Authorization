package com.tryingjava.my_security.controllers;

import com.tryingjava.my_security.models.Student;
import com.tryingjava.my_security.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/index")
    public String main(){
        return"index";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("getAllStudents", students);
        return "st/student";
    }

    @GetMapping("/students/get")
    public String getStudentCreateForm(Model model){
        model.addAttribute("getStudentCreateForm","");
        return"st/getStudentCreateForm";
    }

    @PostMapping("/students/post")
    public String postStudentCreateForm(@ModelAttribute Student student){
        studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/update")
    public String getStudentUpdateForm(Model model, @PathVariable Long id){
        Student student = studentService.getStudentById(id);
        model.addAttribute("getStudentUpdateForm",student);
        return"st/getStudentUpdateForm";
    }

    @PostMapping("/students/{id}/update")
    public String postStudentUpdateForm(@PathVariable Long id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String department,
                                        @RequestParam String email,
                                        @RequestParam String phone){
        Student student = studentService.getStudentById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDepartment(department);
        student.setEmail(email);
        student.setPhone(phone);
        //student.setRegisterDate(registerDate); @RequestParam LocalDate registerDate
        studentService.createStudent(student);
        return"redirect:/students";
    }

    @GetMapping("/students/{id}/delete")
    public String deleteStudent(Model model, @PathVariable Long id){
        Student student = studentService.getStudentById(id);
        studentService.deleteStudentById(student);
        model.addAttribute("deleteStudent","" );
        return"redirect:/students";
    }

    @GetMapping("/students/{id}/info")
    public String getStudentInfoForm(Model model, @PathVariable Long id){
        Student student = studentService.getStudentById(id);
        model.addAttribute("getStudentInfoForm", student);
        return"st/getStudentInfoForm";
    }
}
