package br.com.thiago.note.ui.activity.actions;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.thiago.note.ui.adapter.ListAdapterStudent;

public class ConfigureAdapter {

    public static ListAdapterStudent configureAdapter(ListView listView, AppCompatActivity activity) {
        ListAdapterStudent adapter = new ListAdapterStudent(activity);
        listView.setAdapter(adapter);
        return adapter;
    }

}
