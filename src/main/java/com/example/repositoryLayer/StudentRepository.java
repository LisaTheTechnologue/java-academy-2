//package com.example.repositoryLayer;
//
//import com.example.presentationLayer.StudentDTO;
//
//import java.util.ArrayList;
//
//public class StudentRepository implements IStudentRepository{
////    private final ArrayList<StudentEntity> students = new ArrayList<StudentEntity>();
////
////    public StudentRepository() {
////        //will replace by Database
////        students.add(new StudentEntity(108, "nguyen", "van a", "0972757056"));
////        students.add(new StudentEntity(109, "tran", "thi b", "0465756767"));
////        students.add(new StudentEntity(110, "huyn", "c", "078978564"));
////    }
////
////    public ArrayList<StudentDTO> read()
////    {
////        return students;
////    }
////
////    public StudentDTO read(int id) {
////        var studentEntity = students.stream().filter((animal) -> animal.getId() == id).findFirst().get();
////
////        //Manual Mapper
////        var studentDTO = new StudentDTO(studentEntity.getId(),
////                studentEntity.getFirstName(),
////                studentEntity.getLastName(),
////                studentEntity.getPhone());
////        return studentDTO;
////    }
////
////    public StudentEntity create(StudentEntity student) {
////        students.add(student);
////        return student;
////    }
////
////    public void update(int id, StudentEntity student)
////    {
////        var oldStudent = students.stream().filter((animal) -> animal.getId() == id).findFirst().get();
////        oldStudent.setName(student.getName());
////        oldStudent.setPhone(student.getPhone());
////    }
////
////    public void delete(int id) {
////        var student = students.stream().filter((animal) -> animal.getId() == id).findFirst().get();
////        students.remove(student);
////    }
//}
