package com.example.presentationLayer;

import com.example.repositoryLayer.IStudentRepository;
import com.example.repositoryLayer.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentsController {
    private String errorMessage = "";
    @Autowired
    IStudentRepository studentRepository;
    ModelMapper modelMapper = new ModelMapper();
//    StudentRepository studentRepository = new StudentRepository();
    @GetMapping("/api/v1/students")
    public ResponseEntity<List<StudentDTO>> read() {
        var studentEntities= studentRepository.findAll();
        List<StudentDTO> studentDTOS = studentEntities.stream()
                .map(s -> modelMapper.map(s,StudentDTO.class))
                .collect(Collectors.toList());
//        for(int i=0; i<studentDTOS.size(); i++)
//        {
//            var studentDTO = new StudentDTO(studentEntities.get(i).getId(), studentEntities.get(i).getFirstName(), studentEntities.get(i).getLastName(), studentEntities.get(i).getPhone());
//            studentDTOS.add(studentDTO);
//        }
        return new ResponseEntity<List<StudentDTO>>(studentDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/v1/students/{id}")
    public ResponseEntity<StudentDTO> read(@PathVariable("id") int studentId) {
        Optional<StudentEntity> studentDB = studentRepository.findById(studentId);
        if(studentDB.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        StudentEntity studentEntity = studentDB.get();
        var studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
        return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/api/v1/students")
    public ResponseEntity create(@RequestBody StudentDTO student) {
        if (isValidated(student)) {
            var studentEntity = modelMapper.map(student, StudentEntity.class);
            var studentCreated = studentRepository.save(studentEntity);
            var studentAfterCreated =  modelMapper.map(studentCreated, StudentDTO.class);
            return new ResponseEntity<StudentDTO>(studentAfterCreated, HttpStatus.CREATED);
        } else {
            var validationModel = new ValidationModel("create(student)", "isValidated(student)", errorMessage);
            return new ResponseEntity<ValidationModel>(validationModel, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/v1/students/{id}")
    public ResponseEntity<?> update(@RequestBody StudentDTO student,
                                    @PathVariable("id") int studentId) {
        try {
            Optional<StudentEntity> studentDB = studentRepository.findById(studentId);
            if(studentDB.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            StudentEntity studentEntity = studentDB.get();
            studentEntity.setLastName(student.getLastName());
            studentEntity.setFirstName(student.getFirstName());
            studentEntity.setPhone(student.getPhone());
            studentRepository.save(studentEntity);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            var errorModel = new ErrorModel("update(Student student, int studentId)",
                    Arrays.stream(exception.getStackTrace()).findFirst().toString(),
                    exception.getMessage(),
                    exception.getStackTrace().toString());

            return new ResponseEntity<ErrorModel>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/students/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int studentId) {
        Optional<StudentEntity> studentDB = studentRepository.findById(studentId);
        if(studentDB.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        StudentEntity studentEntity = studentDB.get();
        studentRepository.delete(studentEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean isValidated(StudentDTO student) {
        //validate "Name"
        if (student.getFirstName().length() > 50) {
            errorMessage = "StudentFirstName must less than equal 50";
            return false;
        }

        return true;

    }
}
