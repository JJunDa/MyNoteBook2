package adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mynotebook.R;

import java.util.List;

import com.example.administrator.mynotebook.ui.date.Note;

/**
 * Created by Administrator on 2017/5/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Note> mNoteList;

    public MyAdapter(List<Note> mNoteList){
        this.mNoteList = mNoteList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.item_img.setImageResource(note.getId());
        holder.item_notifi.setImageResource(note.getNotifiId());
        holder.item_star.setImageResource(note.getStarId());
        holder.item_title.setText(note.getTitle());
        holder.item_cretime.setText(String.valueOf(note.getCreateDate()));
        holder.item_retime.setText(String.valueOf(note.getModifyDate()));
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_img;
        private ImageView item_notifi;
        private ImageView item_star;
        private TextView item_title;
        private TextView item_cretime;
        private TextView item_retime;

        public ViewHolder(View itemView) {
            super(itemView);
            item_img = (ImageView) itemView.findViewById(R.id.item_img);
            item_notifi = (ImageView) itemView.findViewById(R.id.item_notifi);
            item_star = (ImageView) itemView.findViewById(R.id.item_star);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            item_cretime = (TextView) itemView.findViewById(R.id.item_cretime);
            item_retime = (TextView) itemView.findViewById(R.id.item_retime);
        }
    }
}
