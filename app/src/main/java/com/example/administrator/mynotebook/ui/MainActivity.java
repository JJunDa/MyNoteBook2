package com.example.administrator.mynotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mynotebook.R;
import com.example.administrator.mynotebook.ui.date.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import MyInterface.saveNote;
import adapter.MyAdapter;

public class MainActivity extends AppCompatActivity implements saveNote {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatingActionButton;
    private ImageView menubtn;

    private MyAdapter mMyAdapter;
    private List<Note> mNoteList = new ArrayList<Note>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();

    }

    private void inits() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add);
        menubtn = (ImageView) findViewById(R.id.menu);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        addBtnAction();
        menuButAction();

    }

    private void menuButAction() {
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mNavigationView);
            }
        });
    }

    private void addBtnAction() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               addNote();
//                showNewNote();
             Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addNote() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());

        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow();
        int hour = t.hour; // 0-23
        int minute = t.minute;
        String timeStr;
        if(hour>=6 && hour<=12){
            timeStr = "上午 "+ hour+":"+minute;
        }else if(hour>=12 && hour<=19){
            timeStr = "下午 "+ hour+":"+minute;
        }else{
            timeStr = "晚上 "+ hour+":"+minute;
        }

        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a, "标题1", "内容1", date, timeStr));


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(mNoteList);
        mRecyclerView.setAdapter(mMyAdapter);

    }

    @Override
    public void addLists(boolean isDel, int id, int notifiId, int starId, String title, String content, String createDate, String modifyDate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());

        Log.i("AAA","________________________");
        Time t=new Time();
        t.setToNow();
        int hour = t.hour;
        int minute = t.minute;
        String timeStr;
        if(hour>=6 && hour<=12){
            timeStr = "上午 "+ hour+":"+minute;
        }else if(hour>=12 && hour<=19){
            timeStr = "下午 "+ hour+":"+minute;
        }else{
            timeStr = "晚上 "+ hour+":"+minute;
        }

        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a, "标题1", "内容1", date, timeStr));

        mNoteList.add(new Note(isDel,id,notifiId,starId,title,content,createDate,modifyDate));

        showNewNote();
    }

    private void showNewNote() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(mNoteList);
        mRecyclerView.setAdapter(mMyAdapter);
    }
}




