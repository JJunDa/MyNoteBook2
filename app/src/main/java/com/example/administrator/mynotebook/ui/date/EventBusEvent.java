package com.example.administrator.mynotebook.ui.date;

/**
 * Created by Administrator on 2017/5/13.
 */

public class EventBusEvent {
    private Note mNote;
    public EventBusEvent(Note mNote){
        this.mNote = mNote;
    }
    public Note getMsg(){
        return mNote;
    }
    public void setMessage(Note mNote) {
        this.mNote = mNote;
    }

}
