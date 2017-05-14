package com.example.administrator.mynotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynotebook.R;
import com.example.administrator.mynotebook.ui.date.EventBusEvent;
import com.example.administrator.mynotebook.ui.date.Note;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import utils.NoteUtils;

/**
 * Created by Administrator on 2017/5/9.
 */

public class AddActivity extends AppCompatActivity {
    private Spinner spinner;
    private ImageView leftbtn;
    private Button btnSave;
    private TextView add_time;
    private TextView add_contenttitle;
    private TextView add_content;
    private String NOW_TIME = null;
    private String NOW_DAY = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inits();

    }
    //初始化控件
    private void inits() {
        spinner = (Spinner) findViewById(R.id.spinner1);
        leftbtn = (ImageView) findViewById(R.id.add_left);
        btnSave = (Button) findViewById(R.id.btnSave);
        add_time = (TextView) findViewById(R.id.add_time);
        add_contenttitle =(TextView) findViewById(R.id.add_contenttitle);
        add_content = (TextView) findViewById(R.id.add_content);

        initsSpinner();//初始化下拉框
        getNowTime();//获得当前时间
        add_time.setText(NOW_DAY+" | "+NOW_TIME);
        reBtnAction();//返回按钮事件
        saveButAction();//保存按钮事件

    }

    //返回按钮事件
    private void reBtnAction() {
        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }

    //返回按钮事件
    private void saveButAction() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int id = R.drawable.book;
                int notifiId =R.drawable.notifications;
                int starId =R.drawable.star_a;
                String title = add_contenttitle.getText().toString();
                String content =add_content.getText().toString();
                String date=NOW_DAY;
                String timeStr = NOW_TIME;

                Log.i("AAA----",id+",notifiId-"+notifiId+",starId-"+starId+",title-"+title+",content-"+content+",date-"+date);
                NoteUtils.addNote(AddActivity.this, id, notifiId, starId, title,content, date);


                Note newNote = new Note(false,id,notifiId,starId,title,content,date,timeStr);
             //用EventBus通知MainActivity
                EventBus.getDefault().post(new EventBusEvent(newNote));

                finish();
            }
        });
    }


    private void gotoMainActivity() {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //初始化下拉框
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
    //获得当前日期NOW_DAY，时间NOW_TIME
    public void getNowTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        NOW_DAY=sdf.format(new Date());

        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow();
        int hour = t.hour; // 0-23
        int minute = t.minute;

        if (hour >= 6 && hour <= 12) {
            NOW_TIME = "上午 " + hour + ":" + minute;
        } else if (hour >= 12 && hour <= 19) {
            NOW_TIME = "下午 " + hour + ":" + minute;
        } else {
            NOW_TIME = "晚上 " + hour + ":" + minute;
        }
    }
}
