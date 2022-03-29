package br.com.alura.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Student;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();
    private static int counterIds = 1;

    public void save(Student student) {
        student.setId(counterIds);
        students.add(student);
        atualizeIds();
    }

    private void atualizeIds() {
        counterIds++;
    }

    public void edit(Student student) {
        Student studentEncontrado = findStudentById(student);
        if (studentEncontrado != null) {
            int posicaoDoAluno = students.indexOf(studentEncontrado);
            students.set(posicaoDoAluno, student);
        }
    }

    @Nullable
    private Student findStudentById(Student student) {
        for (Student a :
                students) {
            if (a.getId() == student.getId()) {
                return a;
            }
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
