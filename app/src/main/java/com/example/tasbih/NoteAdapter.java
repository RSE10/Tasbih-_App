package com.example.tasbih;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NoteAdapter extends FirebaseRecyclerAdapter<NoteModel,NoteAdapter.MyUserViewHoler> {
    Context context;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NoteAdapter(@NonNull FirebaseRecyclerOptions<NoteModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyUserViewHoler holder, @SuppressLint("RecyclerView") int position, @NonNull NoteModel model) {
        holder.title.setText(model.getTitle());
        holder.content.setText(model.getContent());
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared_preference",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        String username = sharedPreferences.getString("username","default_value");

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                builder.setTitle("Are you sure delete this item");
                builder.setMessage("Delete data can not be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("note").child(username)
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
                        Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.title.getContext())
                                .setContentHolder(new ViewHolder(R.layout.edit_note_dialougue_show))
                                        .setExpanded(true,1200)
                                                .create();
                View vw = dialogPlus.getHolderView();

                TextInputEditText title = vw.findViewById(R.id.edit_title);
                TextInputEditText content = vw.findViewById(R.id.edit_content);
                AppCompatButton savBtn = vw.findViewById(R.id.save_edit_note);
                
                title.setText(model.getTitle());
                content.setText(model.getContent());
                dialogPlus.show();
                
                savBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("title", Objects.requireNonNull(title.getText()).toString());
                        map.put("content", Objects.requireNonNull(content.getText()).toString());

                        FirebaseDatabase.getInstance().getReference().child("note")
                                .child(username).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();
                                        Toast.makeText(context, "Update Note", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                
            }
        });

    }

    @NonNull
    @Override
    public MyUserViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new MyUserViewHoler(view);
    }

    public class MyUserViewHoler extends RecyclerView.ViewHolder{
        TextView title,content;
        ImageView editBtn,deleteBtn;
        public MyUserViewHoler(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            content = itemView.findViewById(R.id.noteContent);
            editBtn = itemView.findViewById(R.id.edit_view_button);
            deleteBtn = itemView.findViewById(R.id.delete_view_button);
        }

    }
}
