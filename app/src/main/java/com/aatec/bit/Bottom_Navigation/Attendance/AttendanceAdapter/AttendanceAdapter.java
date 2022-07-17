package com.aatec.bit.Bottom_Navigation.Attendance.AttendanceAdapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Bottom_Navigation.Attendance.Database.Attendance;
import com.aatec.bit.R;

public class AttendanceAdapter extends ListAdapter<Attendance, AttendanceAdapter.AttendanceHolder> {

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
    private updateListener listener;
    private onCheckListener checkListener;
    private onWrongListener wrongListener;
    private Context context;
    private deleteListener deleteListener;

    public AttendanceAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_attendance, parent, false);
        return new AttendanceHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceHolder holder, int position) {
        Attendance attendance = getItem(position);

//        TextViews
        holder.text_view_subject.setText(attendance.getSubject_name());
        holder.text_view_present.setText(String.valueOf(attendance.getPresent()));
        holder.text_view_total.setText(String.valueOf(attendance.getTotal()));

//        ImageView
        holder.correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkListener.setOnClickListener(attendance);
            }
        });
        holder.wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongListener.setOnWrongListener(attendance);
            }
        });

        int present = attendance.getPresent();
        int total = attendance.getTotal();
//        Progress bar
        if (total != 0) {
            holder.progress_circular.setProgress(percentage(present, total));
            holder.text_view_percentage.setText(String.valueOf(percentage(present, total) + "%"));
//            Condition
            allCondition(holder.progress_circular, holder.text_view_status, holder.ivStatus,
                    total, present, percentage(present, total));
        } else {
            holder.progress_circular.setProgress(100);
            holder.progress_circular.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.background), PorterDuff.Mode.SRC_IN);
            holder.text_view_percentage.setText("0%");
            holder.ivStatus.setImageResource(R.drawable.white);
            holder.text_view_status.setText("");
        }


//        Three Dots
        holder.doots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(context, holder.doots);
                menu.inflate(R.menu.three_dots);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                listener.setOnUpdateListener(attendance,holder.layout);
                                break;
                            case R.id.delete:
                                deleteListener.setOnDeleteListener(attendance);
                                break;
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
    }


    //    for percentage
    private int percentage(double present, double total) {
        return (int) ((present / total) * 100);
    }

    //    How much u need to attend
    private int attend(int total, int present) {
        return (3 * total) - (4 * present);
    }


    //    Reverse
    private int absent(int total, int present) {
        return (((4 * present) - (3 * total)) / 3);
    }

    //    All Condition
    private void allCondition(ProgressBar progressBar, TextView textView, ImageView imageView
            , int total, int present, int percentage) {
        progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.SRC_IN);
        if (percentage >= 75) {
            imageView.setImageResource(R.drawable.recgreen);
            if (percentage == 75) {
                textView.setText("On track don't miss next class");
                return;
            }
            if (absent(total, present) == 0) {
                textView.setText("On track don't miss next class");
                return;
            }
            textView.setText("You can leave " + absent(total, present) + " Class");
        } else {
            progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN);
            imageView.setImageResource(R.drawable.recred);
            textView.setText("Attend Next " + String.valueOf(attend(total, present)) + " Class");
        }
    }

    public void setOnCleckListener(onCheckListener cleckListener) {
        this.checkListener = cleckListener;
    }

    public void setOnWrongListener(onWrongListener wrongListener) {
        this.wrongListener = wrongListener;
    }

    public void setOnUpdateListener(updateListener listener) {
        this.listener = listener;
    }

    //    Interface for Present
    public interface onCheckListener {
        void setOnClickListener(Attendance attendance);
    }

    //    Interface for Absent
    public interface onWrongListener {
        void setOnWrongListener(Attendance attendance);
    }

    //    Update
    public interface updateListener {
        void setOnUpdateListener(Attendance attendance,CardView layout);
    }

    //    Delete
    public interface deleteListener {
        void setOnDeleteListener(Attendance attendance);
    }

    public void setOnDeleteListener(deleteListener deleteListener){
        this.deleteListener = deleteListener;
    }
    class AttendanceHolder extends RecyclerView.ViewHolder {

        private TextView text_view_subject, text_view_present, text_view_total, text_view_status, text_view_percentage;
        private ProgressBar progress_circular;
        private ImageView correct, wrong, ivStatus;
        private RelativeLayout doots;
        private CardView layout;

        public AttendanceHolder(@NonNull View itemView) {
            super(itemView);
            text_view_present = itemView.findViewById(R.id.text_view_present);
            text_view_subject = itemView.findViewById(R.id.text_view_subject);
            text_view_total = itemView.findViewById(R.id.text_view_total);
            text_view_status = itemView.findViewById(R.id.text_view_status);
            progress_circular = itemView.findViewById(R.id.progress_circular);
            correct = itemView.findViewById(R.id.correct);
            wrong = itemView.findViewById(R.id.wrong);
            text_view_percentage = itemView.findViewById(R.id.text_view_percentage);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            doots = itemView.findViewById(R.id.doots);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
