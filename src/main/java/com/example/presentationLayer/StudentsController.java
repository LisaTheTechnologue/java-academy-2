package com.example.presentationLayer;

import com.example.repositoryLayer.IBaseStudentRepository;
import com.example.repositoryLayer.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentsController {
    private String errorMessage = "";

    @Autowired
    IStudentRepository studentRepository;

    @GetMapping("/api/v1/studentsWithPaging")
    public ResponseEntity<List<StudentDTO>> read(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        return new ResponseEntity<List<StudentDTO>>(studentRepository.read(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/api/v1/students")
    public ResponseEntity<List<StudentDTO>> read() {
        return new ResponseEntity<List<StudentDTO>>(studentRepository.read(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/students/{id}")
    public ResponseEntity<StudentDTO> read(@PathVariable("id") int studentId) {
        return new ResponseEntity<StudentDTO>(studentRepository.read(studentId), HttpStatus.OK);
    }

    @PostMapping("/api/v1/students")
    public ResponseEntity create(@RequestBody StudentDTO studentDTO) {
        if (isValidated(studentDTO)) {
            var studentAfterCreated = studentRepository.create(studentDTO);
            return new ResponseEntity<StudentDTO>(studentAfterCreated, HttpStatus.CREATED);
        } else {
            var validationModel = new ValidationModel("create(student)", "isValidated(student)", errorMessage);
            return new ResponseEntity<ValidationModel>(validationModel, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/v1/students/{id}")
    public ResponseEntity<?> update(@RequestBody StudentDTO studentDTO, @PathVariable("id") int studentId) {
        try {
            studentRepository.update(studentId, studentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            var errorModel = new ErrorModel("update(Student student, int studentId)",
                    Arrays.stream(exception.getStackTrace()).findFirst().toString(),
                    exception.getMessage(),
                    exception.getStackTrace().toString());

            return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/students/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int studentId) {
        studentRepository.delete(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean isValidated(StudentDTO student) {
        //validate "Name"
        if (student.getFirstName().length() > 50 | student.getLastName().length() > 50) {
            errorMessage = "StudentName must less than equal 50";
            return false;
        }

        return true;

        //validate "Phone", BTVN3: phone hợp lệ là phone có 10 ký số

    }
}
