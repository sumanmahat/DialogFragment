package com.example.dialogfragmentfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment implements MyCustomDialog.OnInputSelected {
    private static final String TAG = "MainFragment";

    private Button mOpenDialog;
    public TextView mTextview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mOpenDialog = view.findViewById(R.id.open_dialog);
        mTextview = view.findViewById(R.id.input_display);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");
                MyCustomDialog dialog = new MyCustomDialog();
                dialog.setTargetFragment(MainFragment.this,1);
                dialog.show(getFragmentManager(),"MyCustomDialog");
            }
        });
        return view;

    }

    @Override
    public void sendInput(String input) {
        mTextview.setText(input);
    }
}
