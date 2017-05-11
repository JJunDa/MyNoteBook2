package com.example.administrator.mynotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynotebook.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import MyInterface.saveNote;
import utils.NoteUtils;

/**
 * Created by Administrator on 2017/5/9.
 */

public class AddActivity extends AppCompatActivity {
    private Spinner spinner;
    private ImageView leftbtn;
    private Button btnSave;
    private saveNote msaveNote;

    private TextView add_time;
    private TextView add_contenttitle;
    private TextView add_content;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inits();

    }

    private void inits() {
        spinner = (Spinner) findViewById(R.id.spinner1);
        leftbtn = (ImageView) findViewById(R.id.add_left);
        btnSave = (Button) findViewById(R.id.btnSave);
        add_time = (TextView) findViewById(R.id.add_time);
        add_contenttitle =(TextView) findViewById(R.id.add_contenttitle);
        add_content = (TextView) findViewById(R.id.add_content);

        initsSpinner();
        okBtnAction();
        saveButAction();

    }


    private void okBtnAction() {
        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                gotoMainActivity();
            }
        });
    }

    private void saveButAction() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int id = R.drawable.book;
                int notifiId =R.drawable.notifications;
                int starId =R.drawable.star_a;
                String title = add_contenttitle.getText().toString();
                String content =add_content.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new Date());
                Log.i("AAA",id+",notifiId"+notifiId+",starId"+starId+",title"+title+",content"+content+",date"+date);

                NoteUtils.addNote(AddActivity.this, id, notifiId, starId, title,content, date);

                //通知MainActivity
                msaveNote.addLists(false,id,notifiId,starId,title,content,date,date);

                gotoMainActivity();
            }
        });
    }


    private void gotoMainActivity() {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private void initsSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] notestyles = getResources().getStringArray(R.array.notestyle);
                Toast.makeText(AddActivity.this, "此记事本为:'" + notestyles[position] + "'类型笔记本", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
