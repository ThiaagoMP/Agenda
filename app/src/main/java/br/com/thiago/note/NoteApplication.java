package br.com.thiago.note;

import android.app.Application;

import br.com.thiago.note.dao.StudentDAO;
import br.com.thiago.note.model.Student;

public class NoteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StudentDAO dao = new StudentDAO();
        dao.save(new Student("Thiago", "43999656360", "thiaago.mp@gmail.com"));
    }
}
