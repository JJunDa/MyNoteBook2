package com.example.administrator.mynotebook.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.mynotebook.R;
import com.example.administrator.mynotebook.ui.date.Note;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import utils.NoteUtils;
import utils.ThreadUtils;

import static com.example.administrator.mynotebook.R.id.main_lv_search_results;

/**
 * Created by Administrator on 2017/5/9.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText search_et_input;
    private ImageView search_iv_delete;
    private Button search_btn_back;
    private RecyclerView mRecyclerView;
    private String searchTitle;
    private List<Note> searchList = new ArrayList<Note>();
    private MyAdapter mMyAdapter;
    private Thread mThread = new Thread();
    private boolean flag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        inits();
    }

    private void inits() {

        search_et_input = (EditText) findViewById(R.id.search_et_input);
        search_iv_delete = (ImageView) findViewById(R.id.search_iv_delete);
        search_btn_back = (Button) findViewById(R.id.search_btn_back);
        mRecyclerView = (RecyclerView) findViewById(main_lv_search_results);

        search();
        backBtnAction();

    }

    private void backBtnAction() {
        search_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                finish();
            }
        });
    }

    private void search() {

        new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!flag){
                            try {
                                Thread.sleep(2000);
                                searchTitle = search_et_input.getText().toString();
                                Log.i("search_title------",searchTitle);
                                searchList = NoteUtils.getSearchNoteList(SearchActivity.this,searchTitle);
                                Log.i("search_list------",searchList.toString());

                                //返回主线程
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showSearchViews();
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
        ).start();


    }

    //显示搜索结果
    private void showSearchViews() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(searchList);
        mRecyclerView.setAdapter(mMyAdapter);

        mMyAdapter.changDate(searchList);
    }
}
