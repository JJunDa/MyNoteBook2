package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import com.example.administrator.mynotebook.ui.date.Note;
import com.example.administrator.mynotebook.ui.date.NoteContentProvider;
import com.example.administrator.mynotebook.ui.date.TableNote;

/**
 * Created by Administrator on 2017/5/10.
 */

public class NoteUtils {
        public static boolean addNote(Context context, int id, int notifiId, int starId,
                                      String title, String content, String createDate){
            ContentValues values = new ContentValues();

//            values.put(TableNote.COL_IS_DEL,isDel);
            values.put(TableNote.COL_ID,id);
            values.put(TableNote.COL_NOTIFIID_ID,notifiId);
            values.put(TableNote.COL_STAR_ID,starId);
            values.put(TableNote.COL_NOTIFIID_ID,title);
            values.put(TableNote.COL_CONTENT,content);
            values.put(TableNote.COL_NOTIFIID_ID,content);
            values.put(TableNote.COL_NOTIFIID_ID,createDate);

            context.getContentResolver().insert(NoteContentProvider.NOTE_URI, values);
            return true;
        }

    public static ArrayList<Note> getNoteList(Context context) {
        String[] projection = {TableNote.COL_ID,TableNote.COL_NOTIFIID_ID, TableNote.COL_STAR_ID,TableNote.COL_TITLE,
                TableNote.COL_CONTENT, TableNote.COL_CREATE_DATE,TableNote.COL_IS_DEL};
        Cursor c = context.getContentResolver().query(NoteContentProvider.NOTE_URI, projection, null,null, null);

        ArrayList<Note> noteList = new ArrayList<>();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Note note = new Note();
            note.setId(c.getInt(0));
            note.setNotifiId(c.getInt(1));
            note.setStarId(c.getInt(2));
            note.setTitle(c.getString(3));
            note.setContent(c.getString(4));
            note.setCreateDate(c.getString(5));

            noteList.add(note);
        }
        c.close();
        return  noteList;
    }
}
