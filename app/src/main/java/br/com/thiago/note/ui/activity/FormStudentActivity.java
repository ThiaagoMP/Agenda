package br.com.thiago.note.ui.activity;

import static br.com.thiago.note.ui.activity.ConstantsActivities.KEY_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.thiago.note.R;
import br.com.thiago.note.dao.StudentDAO;
import br.com.thiago.note.model.Student;

public class FormStudentActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR_NEW_STUDENT = "Novo aluno";
    private static final String TITLE_APPBAR_EDIT_STUDENT = "Edita aluno";
    private EditText fieldName;
    private EditText fieldPhone;
    private EditText fieldEmail;
    private final StudentDAO dao = new StudentDAO();
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        initFields();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_form_student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_form_student_menu_save)
            finishForm();
        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent data = getIntent();
        if (data.hasExtra(KEY_STUDENT)) {
            setTitle(TITLE_APPBAR_EDIT_STUDENT);
            student = (Student) data.getSerializableExtra(KEY_STUDENT);
            fillFields();
        } else {
            setTitle(TITLE_APPBAR_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillFields() {
        fieldName.setText(student.getName());
        fieldPhone.setText(student.getPhone());
        fieldEmail.setText(student.getEmail());
    }

    private void finishForm() {
        fillStudent();
        if (student.idIsValid())
            dao.edit(student);
        else
            dao.save(student);
        finish();
    }

    private void initFields() {
        fieldName = findViewById(R.id.activity_form_student_name);
        fieldPhone = findViewById(R.id.activity_form_student_phone);
        fieldEmail = findViewById(R.id.activity_form_student_email);
    }

    private void fillStudent() {
        String name = fieldName.getText().toString();
        String phone = fieldPhone.getText().toString();
        String email = fieldEmail.getText().toString();

        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
    }
}
