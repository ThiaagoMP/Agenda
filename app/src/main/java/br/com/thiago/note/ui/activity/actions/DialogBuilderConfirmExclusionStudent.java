package br.com.thiago.note.ui.activity.actions;

import android.app.AlertDialog;
import android.view.MenuItem;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.thiago.note.dao.StudentDAO;
import br.com.thiago.note.model.Student;
import br.com.thiago.note.ui.adapter.ListAdapterStudent;

public class DialogBuilderConfirmExclusionStudent {

    public static void dialogBuilder(AppCompatActivity activity, MenuItem item, ListAdapterStudent adapter, StudentDAO dao) {
        new AlertDialog
                .Builder(activity)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student studentFill = adapter.getItem(menuInfo.position);
                    remove(studentFill, dao, adapter);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private static void remove(Student student, StudentDAO dao, ListAdapterStudent adapter) {
        dao.remove(student);
        adapter.remove(student);
    }

}
