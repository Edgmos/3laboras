package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class DeleteNoteActivity extends AppCompatActivity {
    private ListView lvNotes;
    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;

    Spinner spSelectToDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
        Spinner spSelectToDelete = findViewById(R.id.spSelectToDelete);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spSelectToDelete.setAdapter(listAdapter);
    }

    public void onDeleteClick(View view) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> savedNotesList = sp.getStringSet("notes",new HashSet<String>());
//        String selectedNote = spSelectToDelete.getSelectedItem().toString();
        Spinner spSelectToDelete = (Spinner) findViewById(R.id.spSelectToDelete);
        String selected = spSelectToDelete.getSelectedItem().toString();
//        sp.edit().clear().commit();
//        String selectedNote = "vienas";
        Set <String> StrSet = new HashSet<String>();
        for (String savedNote : savedNotesList){
            if (savedNote.equalsIgnoreCase(selected)){
                Toast.makeText(this,savedNote, Toast.LENGTH_SHORT).show();
            }
            else{
                StrSet.add(savedNote);
            }
        }
        spEd.putStringSet("notes", StrSet);//.commit();
        spEd.apply();
        finish();
    }
}
