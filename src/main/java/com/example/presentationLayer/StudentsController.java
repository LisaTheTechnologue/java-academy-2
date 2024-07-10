package com.example.presentationLayer;

import com.example.repositoryLayer.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class StudentsController {
    private String errorMessage = "";
    @Autowired
    IStudentRepository studentRepository;

    @GetMapping("/api/v1/students")
    public ResponseEntity<ArrayList<StudentDTO>> read() {
        var studentEntities= studentRepository.findAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for(int i=0; i<3; i++)
        {
            var studentDTO = new StudentDTO(studentEntities.get(i).getId(), studentEntities.get(i).getFirstName(), studentEntities.get(i).getLastName(), studentEntities.get(i).getPhone());
            studentDTOS.add(studentDTO);
        }
        return new ResponseEntity<ArrayList<StudentDTO>>(studentDTOS, HttpStatus.OK);
    }

//    @GetMapping("/api/v1/students/{id}")
//    public ResponseEntity<StudentDTO> read(@PathVariable("id") int studentId) {
//        return new ResponseEntity<StudentDTO>(studentRepository.read(studentId), HttpStatus.OK);
//    }
//
//    @PostMapping("/api/v1/students")
//    public ResponseEntity create(@RequestBody StudentDTO student) {
//        if (isValidated(student)) {
//            var studentAfterCreated = studentRepository.create(student);
//            return new ResponseEntity<StudentDTO>(studentAfterCreated, HttpStatus.CREATED);
//        } else {
//            var validationModel = new ValidationModel("create(student)", "isValidated(student)", errorMessage);
//            return new ResponseEntity<ValidationModel>(validationModel, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PutMapping("/api/v1/students/{id}")
//    public ResponseEntity<?> update(@RequestBody StudentDTO student, @PathVariable("id") int studentId) {
//        try {
//            studentRepository.update(studentId, student);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        catch (Exception exception) {
//            var errorModel = new ErrorModel("update(Student student, int studentId)",
//                    Arrays.stream(exception.getStackTrace()).findFirst().toString(),
//                    exception.getMessage(),
//                    exception.getStackTrace().toString());
//
//            return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/api/v1/students/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") int studentId) {
//        studentRepository.delete(studentId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    private boolean isValidated(StudentDTO student) {
//        //validate "Name"
//        if (student.getName().length() > 50) {
//            errorMessage = "StudentName must less than equal 50";
//            return false;
//        }
//
//        return true;
//
//        //validate "Phone", BTVN3: phone hợp lệ là phone có 10 ký số
//
//    }
}
