package br.com.thiago.note.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.note.model.Student;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();
    private static int counterIds = 1;

    public void  save(Student student) {
        student.setId(counterIds);
        students.add(student);
        actualizeIds();
    }

    private void actualizeIds() {
        counterIds++;
    }

    public void edit(Student student) {
        Student studentFind = findStudentById(student);
        if (studentFind != null) {
            int studentPosition = students.indexOf(studentFind);
            students.set(studentPosition, student);
        }
    }

    @Nullable
    private Student findStudentById(Student student) {
        for (Student a : students) {
            if (a.getId() == student.getId())
                return a;
        }
        return null;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void remove(Student student) {
        Student studentFind = findStudentById(student);
        if (studentFind != null) {
            students.remove(studentFind);
        }
    }
}
