package com.tryingjava.my_security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="student")
public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="student_id")
        private Long id;

        @Column(name="first_name", nullable = false)
        private String firstName;

        @Column(name="last_name", nullable = false)
        private String lastName;
        @Column(name="department", nullable = false)
        private String department;

        @Column(name="e_mail", nullable = false)
        private String email;

        @Column(name="phone", nullable = false)
        private String phone;

        //        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        //        private LocalDateTime registerTime;
}
