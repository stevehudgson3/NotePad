package app.com.example.steve.notes41235;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditYourNote extends AppCompatActivity implements TextWatcher {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent i = getIntent();
        noteId = i.getIntExtra("noteId", -1);

        if(noteId != -1){

            editText.setText(MainActivity.notes.get(noteId));

        }

        editText.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        MainActivity.notes.set(noteId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.steve.notes41235", Context.MODE_PRIVATE);

        if (MainActivity.set == null ){

            MainActivity.set = new HashSet<String>();

        }else{

            MainActivity.set.clear();

        }

        MainActivity.set.clear();
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().putStringSet("notes", MainActivity.set).apply();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
