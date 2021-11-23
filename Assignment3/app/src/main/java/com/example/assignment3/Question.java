package com.example.assignment3;

public class Question {


    String question;
    String answer;
    public Question(){

    }

    public Question(String question, String answer){
        this.question = question;
        this.answer  = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }


}
