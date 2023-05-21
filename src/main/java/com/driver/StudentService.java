package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private Map<String, List<String>> studentTeacherPair = new HashMap<>(); // Teacher-Student
    private StudentRepository studentRepository = new StudentRepository();
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }
    public void addTeacher(Teacher teacher){
        studentRepository.addTeacher(teacher);
    }

    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    public Teacher getTeacherByName(String name) {
        return studentRepository.getTeacherByName(name);
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        List<String> students = getStudentsByTeacherName(teacher);
        for(String s :   students){
            studentRepository.deleteStudentBYName(s);
        }
        studentTeacherPair.remove(teacher);
        studentRepository.deleteTeacherBYName(teacher);
    }

    public void deleteAllTeachers() {
        List<String> teachers =  studentRepository.getAllTeachers();
        for(String t : teachers){
            deleteTeacherByName(t);
        }
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        if(studentTeacherPair.containsKey(teacher)){
            return studentTeacherPair.get(teacher);
        }
        return new ArrayList<>();
    }

    public void addStudentTeacherPair(String student, String teacher) throws RuntimeException{
        if(studentRepository.getStudentByName(student)==null){
            throw new RuntimeException("Student not present in  DB");
        }
        if(studentRepository.getTeacherByName(teacher)==null) {
            throw new RuntimeException(("Teacher not present in DB"));
        }
        if(!studentTeacherPair.containsKey(teacher)){
            studentTeacherPair.put(teacher, new ArrayList<>());
        }
        Teacher teacher1 = studentRepository.getTeacherByName(teacher);
        teacher1.setNumberOfStudents(teacher1.getNumberOfStudents()+1);
        studentRepository.addTeacher(teacher1);
        List<String> l = studentTeacherPair.get(teacher);
        l.add(student);
        studentTeacherPair.put(teacher,l);
    }
}
