package com.example.repositoryLayer;

import com.example.presentationLayer.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IStudentRepository {
    public List<StudentDTO> read();
    public StudentDTO read(int id);
    public StudentDTO create(StudentDTO student);
    public void update(int id, StudentDTO student);
    public void delete(int id);

    public List<StudentDTO> read(int pageNumber, int pageSize);
}
