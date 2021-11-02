package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class Details extends AppCompatActivity {
    long id;
    TextView mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();
        id = i.getLongExtra("ID",0);

        NodeDatabase db  = new NodeDatabase(this);
        Note note = db.getNote(id);


        Toast.makeText(getApplicationContext(),"ID->"+id,Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(note.getTitle());
        }
        mDetails = findViewById(R.id.DeatilsoFnote);
        mDetails.setText(note.getContent());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NodeDatabase db  = new NodeDatabase(getApplicationContext());
                db.DeletNote(id);
                Toast.makeText(getApplicationContext(),"DELETED SUCCESSFULLY",Toast.LENGTH_SHORT).show();

                finish();

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == R.id.editNote)
        {
            Toast.makeText(getApplicationContext(),"Change",Toast.LENGTH_SHORT).show();
            Intent i =new Intent(this,Edit.class);
            i.putExtra("ID",id);
            startActivity(i);
        }
        if (item.getItemId() == R.id.sinDeatils){
            MyDialogueFrafment myDialogueFrafment = new MyDialogueFrafment();
            myDialogueFrafment.show(getSupportFragmentManager(),"My Fragment");
        }

        return super.onOptionsItemSelected(item);
    }
    private  void goToMain(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

}
