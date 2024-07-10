package com.example.repositoryLayer;

import com.example.presentationLayer.StudentDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {
    private final List<StudentEntity> students = new ArrayList<StudentEntity>();
    private final ModelMapper modelMapper = new ModelMapper();
    public StudentRepository() {
        //will replace by Database
        students.add(new StudentEntity(108, "nguyen", "van a", "0972757056"));
        students.add(new StudentEntity(109, "tran", "thi b", "0465756767"));
        students.add(new StudentEntity(110, "huyn", "c", "078978564"));
    }

    public List<StudentDTO> read()
    {
        return students.stream()
                .map(s -> modelMapper.map(s, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO read(int id) {
        var studentEntity = students.stream().filter((animal) -> animal.getId() == id).findFirst().get();

        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    public StudentDTO create(StudentDTO student) {
        students.add(modelMapper.map(student, StudentEntity.class));
        return student;
    }

    public void update(int id, StudentDTO student)
    {
        var oldStudent = students.stream().filter((animal) -> animal.getId() == id).findFirst().get();
        oldStudent.setFirstName(student.getFirstName());
        oldStudent.setLastName(student.getLastName());
        oldStudent.setPhone(student.getPhone());
    }

    public void delete(int id) {
        var student = students.stream().filter((animal) -> animal.getId() == id).findFirst().get();
        students.remove(student);
    }
}
