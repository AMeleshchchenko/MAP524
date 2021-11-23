package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {
    FragmentManager fm;
    TextView text;
    String numlist = "";
    int questionsAnswered = 0;
    int correctAns = 0;
    ArrayList<Question> questions;

    ArrayList<Integer> colorArrayList;
    String[] questionString;
    String[] answerString;
    AlertDialog.Builder builder;

    StorageManager storageManager;
    private Menu menu;

    ProgressBar progressBarView;
    String q1 = "Toronto is the capital of Ontario";
    String q2 = "Montreal is the capital of Quebec";
    String q3 = "Reykjavik is the capital city of Iceland";
    String q4 = "Krasnodar is the capital of Russia";
    String q5 = "New York is the capital of United States";
    String q6 = "Victoria is the capital of Alberta";
    String q7 = "Berlin is the capital of Germany";
    String q8 = "Marseille is the capital of France";

    String a1 = "true";
    String a2 = "true";
    String a3 = "true";
    String a4 = "false";
    String a5 = "false";
    String a6 = "false";
    String a7 = "true";
    String a8 = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarView = (ProgressBar) findViewById(R.id.progressBar);

        q1 = getResources().getString(R.string.questionOne);
        q2 = getResources().getString(R.string.questionTwo);
        q3 = getResources().getString(R.string.questionThree);
        q4 = getResources().getString(R.string.questionFour);
        q5 = getResources().getString(R.string.questionFive);
        q6 = getResources().getString(R.string.questionSix);
        q7 = getResources().getString(R.string.questionSeven);
        q8 = getResources().getString(R.string.questionEight);


        a1 = getResources().getString(R.string.btnTrue);
        a2 = getResources().getString(R.string.btnTrue);
        a3 = getResources().getString(R.string.btnTrue);
        a4 = getResources().getString(R.string.btnTrue);
        a5 = getResources().getString(R.string.btnTrue);
        a6 = getResources().getString(R.string.btnTrue);
        a7 = getResources().getString(R.string.btnTrue);
        a8 = getResources().getString(R.string.btnTrue);

        questionString = new String[]{q1, q2, q3, q4, q5, q6, q7, q8};
        answerString = new String[]{a1, a2, a3, a4, a5, a6, a7, a8};
        questions = new ArrayList<>();
        colorArrayList = new ArrayList<>();

        builder = new AlertDialog.Builder(MainActivity.this);
        storageManager = ((MyApp) getApplication()).getStorageManager();
        colorArrayList.add(getResources().getColor(R.color.DarkOrchid));
        colorArrayList.add(getResources().getColor(R.color.BurlyWood));
        colorArrayList.add(getResources().getColor(R.color.Olive));
        colorArrayList.add(getResources().getColor(R.color.Coral));
        colorArrayList.add(getResources().getColor(R.color.Crimson));
        colorArrayList.add(getResources().getColor(R.color.Peru));
        colorArrayList.add(getResources().getColor(R.color.Tomato));
        colorArrayList.add(getResources().getColor(R.color.Maroon));

        Collections.shuffle(colorArrayList);

        setUpArrayList();
    }

    public void checkans(View view){
        progressBarView.setProgress(13*questionsAnswered);
        fm = getSupportFragmentManager();
        Fragment test = fm.findFragmentById(R.id.mainframe);
        if(test == null){
            AddQuestionsFragment(view);
            questionsAnswered--;
            return;
        }

        Button trueBtn = (Button) view;
        System.out.println("BTN NAME IS " + trueBtn.getText());
        if(questionsAnswered == 0){
            if(trueBtn.getText().equals(questions.get(questionsAnswered).getAnswer())){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        } else if (questionsAnswered == 8){



            fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(fm.findFragmentById(R.id.mainframe));
            transaction.commit();

            builder.setTitle("Result");
            builder.setMessage("Your score is " + correctAns + " out of 8");
            builder.setPositiveButton("Ignore", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
//Button Two : No
            builder.setNegativeButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    storageManager.writeAvg(MainActivity.this, correctAns);
                }
            });
            builder.show();
            questionsAnswered = 0;
            return;
        }

        else {
            if(trueBtn.getText().equals(questions.get(questionsAnswered - 1).getAnswer())){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correctAns++;
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        }

        AddQuestionsFragment(view);
    }

    public void AddQuestionsFragment(View view) {

        fm = getSupportFragmentManager();
        Fragment test = fm.findFragmentById(R.id.mainframe); //is there a fragment in the frame
        FragmentTransaction transaction = fm.beginTransaction();

        //
        Bundle bundle = new Bundle();
        bundle.putString("questionVal", questions.get(questionsAnswered).getQuestion());
        bundle.putInt("backgroundcolor", colorArrayList.get(questionsAnswered));

        if(test == null){

            transaction.add(R.id.mainframe, QuestionsFragment.class, bundle);
            transaction.commit();
        } else {

            transaction.replace(R.id.mainframe, QuestionsFragment.class, bundle);
            transaction.commit();

        }
        questionsAnswered++;
    }


    public int getIndex(int size){
        Random ran = new Random();
        int x = ran.nextInt(8) + 0;
        while(numlist.contains("|" + String.valueOf(x) + "|")){
            x = ran.nextInt(8) + 0;
        }
        numlist += "|" + String.valueOf(x) + "|";
        return x;
    }

    public void setUpArrayList(){
        boolean flag = true;
        int x;
        int size = 0;
        while(flag){
            x = getIndex(8);
            questions.add(new Question(questionString[x], answerString[x]));
            size++;
            if(size >= 8){
                flag = false;
            }
        }

        for(int i = 0; i < 8; i++){
            System.out.println("TESTING LIST" + questions.get(i).getQuestion() + " | ");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.questions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.reset:
                this.storageManager.resetSavedResult(MainActivity.this);
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Averages Reset");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.show();
                break;
            case R.id.getAvg:
                String avg = this.storageManager.readAvg(MainActivity.this);
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Your correct answers are " + avg);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                builder.setNegativeButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        storageManager.writeAvg(MainActivity.this, correctAns);
                    }
                });
                builder.show();
                questionsAnswered = 0;
                break;
        }
        return true;
    }
}
