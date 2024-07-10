package com.example;

import com.example.repositoryLayer.IStudentRepository;
import com.example.repositoryLayer.StudentEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(IStudentRepository studentRepository) {
		return (args) -> {
			// save few students
			StudentEntity student1 = new StudentEntity(108, "nguyen", "van a", "0972757056");
			StudentEntity student2 = new StudentEntity(109, "tran", "thi b", "0465756767");
			StudentEntity student3 = new StudentEntity(110, "huyn", "c", "078978564");

			studentRepository.save(student1);
			studentRepository.save(student2);
			studentRepository.save(student3);
		};
	}
}
