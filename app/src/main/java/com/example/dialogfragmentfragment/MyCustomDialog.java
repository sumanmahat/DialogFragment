package com.example.dialogfragmentfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyCustomDialog extends DialogFragment {
    private static final String TAG = "MyCustomDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected onInputSelected;

    //widgets
    private EditText mInput;
    private TextView mActionOk, mActionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_my_custom,container,false);
        mActionOk = view.findViewById(R.id.action_ok);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mInput = view.findViewById(R.id.input);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: canceling dialog");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");
                String input = mInput.getText().toString();
                if (!input.equals("")) {
//                    //easier way
//                    MainFragment mainFragment = (MainFragment) getActivity().getFragmentManager().findFragmentByTag("MainFragment");
//                    mainFragment.mTextview.setText(input);

                    onInputSelected.sendInput(input);

                }
                getDialog().dismiss();
            }
        });
        return  view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            onInputSelected = (OnInputSelected) getTargetFragment();

        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException"+ e.getMessage() );
        }
    }
}
