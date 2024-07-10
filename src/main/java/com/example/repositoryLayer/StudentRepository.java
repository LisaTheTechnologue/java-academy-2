package com.example.repositoryLayer;

import com.example.presentationLayer.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository{

    @Autowired
    IBaseStudentRepository baseStudentRepository;

    @Override
    public List<StudentDTO> read() {
        var studentEntities= baseStudentRepository.findAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for(int i=0; i<studentEntities.size(); i++)
        {
            var studentDTO = new StudentDTO(studentEntities.get(i).getId(), studentEntities.get(i).getFirstName(), studentEntities.get(i).getLastName(), studentEntities.get(i).getPhone());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO read(int id) {
        var studentEntity = baseStudentRepository.findById(id).get();
        var studentDTO = new StudentDTO(studentEntity.getId(), studentEntity.getFirstName(), studentEntity.getLastName(), studentEntity.getPhone());
        return studentDTO;
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) {
        var studentEntity = new StudentEntity(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getPhone());
        var studentCreated = baseStudentRepository.save(studentEntity);
        return new StudentDTO(studentCreated.getId(),studentCreated.getFirstName(),studentCreated.getLastName(),studentCreated.getPhone());
    }

    @Override
    public void update(int id, StudentDTO studentDTO) {
        var studentEntity = baseStudentRepository.findById(id).get();
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setPhone(studentDTO.getPhone());

        baseStudentRepository.save(studentEntity);
    }

    @Override
    public void delete(int id) {
        baseStudentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> read(int pageNumber, int pageSize) {
        Pageable pageable = null;
        pageable = PageRequest.of(pageNumber, pageSize);

        //Manual Mapper
        var studentsEntity = baseStudentRepository.findAll(pageable);
        List<StudentDTO> studentDTOs = new ArrayList<>();

        studentsEntity.forEach(student -> {
            StudentDTO dto = new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getPhone());
            studentDTOs.add(dto);
        });

        //Auto Mapper[OPTIONAL]
        return studentDTOs;
    }
}
