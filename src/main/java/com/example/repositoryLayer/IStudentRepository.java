package com.example.repositoryLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Integer> {
//    public ArrayList<StudentDTO> read();
//    public StudentDTO read(int id);
//    public StudentEntity create(StudentEntity student);
//    public void update(int id, StudentEntity student);
//    public void delete(int id);
}
