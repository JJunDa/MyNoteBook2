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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.mynotebook.R;
import com.example.administrator.mynotebook.ui.date.EventBusEvent;
import com.example.administrator.mynotebook.ui.date.Note;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.MyAdapter;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatingActionButton;
    private ImageView menubtn;
    private ImageView searchbtn;
    private MyAdapter mMyAdapter;
    private List<Note> mNoteList = new ArrayList<Note>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        EventBus.getDefault().register(MainActivity.this);//注册EventBus
    }

    //EventBus接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(EventBusEvent event){
//        String msg = "onEventMainThread收到了消息：" + event.getMsg();
//        Log.d("harvic", msg);
        String msg = "添加成功！";
        addNote(event.getMsg());

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(MainActivity.this);//反注册EventBus
    }

    //初始化控件，菜单和添加事件
    private void inits() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add);
        menubtn = (ImageView) findViewById(R.id.menu);
        searchbtn = (ImageView) findViewById(R.id.search);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        addBtnAction();//菜单右滑
        menuButAction();//跳转到addActivity
        searchAction();
    }

    private void searchAction() {
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    //菜单右滑
    private void menuButAction() {
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mNavigationView);
            }
        });
    }

    //跳转到addActivity
    private void addBtnAction() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    //把添加的记事本用RcycleView显示出来
    private void addNote(Note addNote) {
        mNoteList.add(addNote);

        //默认添加
        defaultadd();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(mNoteList);
        mRecyclerView.setAdapter(mMyAdapter);

        mMyAdapter.changDate(mNoteList);
    }

    //默认添加
    private void defaultadd() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date());
        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a,
                "aab","ssss",date,date));
        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a,
                "abb","ssss",date,date));
        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a,
                "acc","ssss",date,date));
        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a,
                "bbb","ssss",date,date));
        mNoteList.add(new Note(false, R.drawable.book,R.drawable.notifications,R.drawable.star_a,
                "baa","ssss",date,date));
    }

}




