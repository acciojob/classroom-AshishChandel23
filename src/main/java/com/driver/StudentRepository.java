package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
    private Map<String, Student> studentData = new HashMap<>();
    private Map<String, Teacher> teacherData = new HashMap<>();
    public void addStudent(Student student){
        studentData.put(student.getName(), student);
    }
    public void addTeacher(Teacher teacher){
        teacherData.put(teacher.getName(), teacher);
    }
    public Student getStudentByName(String name){
        if(studentData.containsKey(name)) return studentData.get(name);
        return null;
    }
    public Teacher getTeacherByName(String name){
        if(teacherData.containsKey(name)) return teacherData.get(name);
        return null;
    }

    public List<String> getAllStudents() {
            return new ArrayList<>(studentData.keySet());
        }
    public List<String> getAllTeachers() {
        return new ArrayList<>(teacherData.keySet());
    }

    public void deleteTeacherBYName(String teacher) {
        teacherData.remove(teacher);
    }
    public void deleteStudentBYName(String student) {
        studentData.remove(student);
    }

    public void deleteAllTeachers() {
        teacherData.clear();
    }
}
