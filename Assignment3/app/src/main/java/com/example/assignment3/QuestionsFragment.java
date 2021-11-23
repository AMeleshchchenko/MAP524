package com.example.assignment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionsFragment extends Fragment {
    TextView textview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View v = super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.questions_fragment,container, false);

        textview = (TextView) v.findViewById(R.id.textInQuestionsFragment);
        String question = getArguments().getString("questionVal");
        int color = getArguments().getInt("backgroundcolor");
        textview.setText(question);
        textview.setBackgroundColor(color);



        return v;
    }
}
