package com.aatec.bit.Bottom_Navigation.Attendance.List_All;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Bottom_Navigation.Attendance.Database.Attendance;
import com.aatec.bit.R;

public class List_All_adapter extends ListAdapter<Attendance, List_All_adapter.List_All_holder> {

    private static final DiffUtil.ItemCallback<Attendance> DIFF_CALL_BACK = new DiffUtil.ItemCallback<Attendance>() {
        @Override
        public boolean areItemsTheSame(@NonNull Attendance oldItem, @NonNull Attendance newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Attendance oldItem, @NonNull Attendance newItem) {
            return oldItem.getSubject_name().equals(newItem.getSubject_name())
                    && oldItem.getPresent() == newItem.getPresent()
                    && oldItem.getTotal() == newItem.getTotal();
        }
    };
    private UpdateListener listenerUpdate;
    private ResetListener listenerReset;
    private DeleteListener listenerDelete;
    public List_All_adapter() {
        super(DIFF_CALL_BACK);
    }

    @NonNull
    @Override
    public List_All_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_all, parent, false);
        return new List_All_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull List_All_holder holder, int position) {
        Attendance attendance = getItem(position);
        holder.tv_present.setText(String.valueOf(attendance.getPresent()));
        holder.tv_subject.setText(attendance.getSubject_name());
        holder.tv_total.setText(String.valueOf(attendance.getTotal()));

//        Update
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerUpdate.setOnUpdateListener(attendance);            }
        });

//        Reset
        holder.iv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerReset.setOnResetListener(attendance);
            }
        });

//        Delete
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerDelete.setOnDeleteListener(attendance);
            }
        });

    }

//Update
    public void setOnUpdateListener(UpdateListener listener) {
        this.listenerUpdate = listener;
    }

//    Reset
    public void setOnResetListener(ResetListener listener){
        this.listenerReset = listener;
    }

//       Delete
    public void setOnDeleteListener(DeleteListener listener){
        this.listenerDelete = listener;
    }


    interface UpdateListener {
        void setOnUpdateListener(Attendance attendance);
    }

    interface ResetListener{
        void setOnResetListener(Attendance attendance);
    }

    interface DeleteListener{
        void setOnDeleteListener(Attendance attendance);
    }

    class List_All_holder extends RecyclerView.ViewHolder {
        private TextView tv_subject, tv_present, tv_total;
        private ImageView iv_edit, iv_reset, iv_delete;

        public List_All_holder(@NonNull View itemView) {
            super(itemView);
            tv_subject = itemView.findViewById(R.id.tv_subject);
            tv_present = itemView.findViewById(R.id.tv_present);
            tv_total = itemView.findViewById(R.id.tv_total);
            iv_edit = itemView.findViewById(R.id.iv_edit);
            iv_reset = itemView.findViewById(R.id.iv_reset);
            iv_delete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
