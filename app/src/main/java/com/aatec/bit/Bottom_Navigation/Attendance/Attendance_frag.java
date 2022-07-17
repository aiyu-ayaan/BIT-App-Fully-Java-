package com.aatec.bit.Bottom_Navigation.Attendance;

import static android.app.Activity.RESULT_OK;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.bit.Bottom_Navigation.Attendance.AttendanceAdapter.AttendanceAdapter;
import com.aatec.bit.Bottom_Navigation.Attendance.Database.Attendance;
import com.aatec.bit.Bottom_Navigation.Attendance.Database.Attendance_ViewModel;
import com.aatec.bit.Bottom_Navigation.Attendance.EditList.Edit_Attendance;
import com.aatec.bit.Bottom_Navigation.Attendance.List_All.List_All;
import com.aatec.bit.R;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

public class Attendance_frag extends Fragment {
    private Attendance_ViewModel viewModel;
    private Vibrator vibrator;
    private RecyclerView show_data;
    private AttendanceAdapter adapter;
    private AdView mAdView;
    private LottieAnimationView img;
    private setIntValue listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_attendance, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(Attendance_ViewModel.class);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        show_data = v.findViewById(R.id.show_data);
        img = v.findViewById(R.id.img);

//        RecyclerView
        adapter = new AttendanceAdapter(getContext());
        show_data.setLayoutManager(new LinearLayoutManager(getContext()));
        show_data.setAdapter(adapter);
        viewModel.getGetAllAttendance().observe(getActivity(), new Observer<List<Attendance>>() {
            @Override
            public void onChanged(List<Attendance> attendances) {
                adapter.submitList(attendances);
                try {
                    int i = 0;
                    if (attendances.size() == 0) {
                        img.setVisibility(View.VISIBLE);
                        i = 0;
                    } else {
                        img.setVisibility(View.INVISIBLE);
                        for (Attendance attendance : attendances) {
                            if (attendance.getTotal() != 0) {
                                int per = percentage(attendance.getPresent(), attendance.getTotal());
                                if (per < 75) {
                                    i++;
                                }
                            }
                        }
                    }
                    listener.sendIntValue(i);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Check : if Present
        Check();

//        Checl : if Absent
        Wrong();


//        set Click
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), List_All.class));
            }
        });

//        Admob
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        return v;
    }

    private int percentage(double present, double total) {
        return (int) ((present / total) * 100);
    }

    //    Absent
    private void Wrong() {
        adapter.setOnWrongListener(new AttendanceAdapter.onWrongListener() {
            @Override
            public void setOnWrongListener(Attendance attendance) {
                int j = 0;//Present,Total
                j = attendance.getTotal();
                j++;
                Attendance finalAtt = new Attendance(attendance.getSubject_name(), j, attendance.getPresent());
                finalAtt.setId(attendance.getId());
                viewModel.update(finalAtt);
            }
        });
    }

    //    Present
    private void Check() {
        adapter.setOnCleckListener(new AttendanceAdapter.onCheckListener() {
            @Override
            public void setOnClickListener(Attendance attendance) {
                int i = 0, j = 0;//Present,Total
                i = attendance.getPresent();
                j = attendance.getTotal();
                i++;
                j++;
                Attendance finalAtt = new Attendance(attendance.getSubject_name(), j, i);
                finalAtt.setId(attendance.getId());
                viewModel.update(finalAtt);
            }
        });

//        Update
        adapter.setOnUpdateListener(new AttendanceAdapter.updateListener() {
            @Override
            public void setOnUpdateListener(Attendance attendance, CardView doots) {
                Intent intent = new Intent(getContext(), Edit_Attendance.class);
                intent.putExtra(List_All.EXTRA_ID, attendance.getId());
                intent.putExtra(List_All.EXTRA_SUBJECT, attendance.getSubject_name());
                intent.putExtra(List_All.EXTRA_PRESENT, attendance.getPresent());
                intent.putExtra(List_All.EXTRA_TOTAL, attendance.getTotal());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                        Pair.create(doots, "expand"));
                startActivityForResult(intent, List_All.UPDATE, options.toBundle());
            }
        });
        adapter.setOnDeleteListener(new AttendanceAdapter.deleteListener() {
            @Override
            public void setOnDeleteListener(Attendance attendance) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Do u want to delete " + attendance.getSubject_name() + " !")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.delete(attendance);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });
    }


    //Setup of menu

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Here notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        Clearing all menu item
        menu.clear();
//        adding new menu
        inflater.inflate(R.menu.list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ibutton:
                vibrator.vibrate(50);
                Toast.makeText(getContext(), "By default it's set to 75%", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                getActivity().startActivity(new Intent(getContext(), List_All.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == List_All.UPDATE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(List_All.EXTRA_ID, -1);
            if (id != -1) {
                String subject = data.getStringExtra(List_All.EXTRA_SUBJECT);
                int total = data.getIntExtra(List_All.EXTRA_TOTAL, -1);
                int present = data.getIntExtra(List_All.EXTRA_TOTAL, -1);
                Attendance attendance = new Attendance(subject, total, present);
                attendance.setId(id);
//                Update
                viewModel.update(attendance);
                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (setIntValue) getActivity();
        } catch (ClassCastException e) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public interface setIntValue {
        void sendIntValue(int i);
    }
}
