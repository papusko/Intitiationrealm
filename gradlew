package com.africa.cloud.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.myapplication.model.Question;
import com.africa.cloud.myapplication.model.QuestionBank;

import java.util.Arrays;

public class JeuxActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mQuestionText;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;


    private boolean mEnableTouchEvent;
    //declaration des attribut de type class dans le model
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNombreDeQuestion;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    //declaration des etats du score et du nombre de question
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION= "currentQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);


        mQuestionText = (TextView) findViewById(R.id.activity_jeux_question_text);
        mAnswer1Button = (Button) findViewById(R.id.activity_jeux_answer1_btn);
        mAnswer2Button = (Button) findViewById(R.id.activity_jeux_answer2_btn);
        mAnswer3Button = (Button) findViewById(R.id.activity_jeux_answer3_btn);
        mAnswer4Button = (Button) findViewById(R.id.activity_jeux_answer4_btn);
        mQuestionBank = this.generateQuestions();
        mEnableTouchEvent = true;

        if(savedInstanceState!=null){
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNombreDeQuestion = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else {
            mScore = 0;
            mNombreDeQuestion = 4;
        }



        // Use the same listener for the four buttons.
// The tag value will be used to distinguish the button triggered
        mAnswer1Button.setOnClickListener(this);
        mAnswer2Button.setOnClickListener(this);
        mAnswer3Button.setOnClickListener(this);
        mAnswer4Button.setOnClickListener(this);

        //distinction des buttons par click en les assignants un index (identifiant)
        mAnswer1Button.setTag(0);
        mAnswer2Button.setTag(1);
        mAnswer3Button.setTag(2);
        mAnswer4Button.setTag(3);

        mCurrentQuestion= mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);



    }


    private QuestionBank generateQuestions() {
        Question question1 = new Question("Quel est le nom de l'actuel président du Sénégal ?",
                Arrays.asList("Abdoulaye Wade", "Macky Sall", "Sadio Mané", "Ousmane Sonko"),
                1);

        Question question2 = new Question("Combien de CAN le Sénégal a-t-il remporté?",
                Arrays.asList("1", "3", "0", "5"),
                2);

        Question question3 = new Question("Qui est le créateur du système d'exploitation Android?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("Quand le premier homme at-il atterri sur la lune?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("Quelle est la capitale du Mali?",
                Arrays.asList("Bamako", "Dakar", "Abidjan", "Ouagadougou"),
                0);

        Question question6 = new Question("Dans quel pays se trouve le monument de la renaissance?",
                Arrays.asList("Bresil", "Sénégal", "Madagascar", "Mexique"),
                1);
        Question question7 = new Question("Quel pays d'afrique est considéré comme le chateau d'eau d'Afrique?",
                Arrays.asList("Sénégal", "Cote d'ivoire", "Guinée Conakry", "Gambie"),
                2);

        Question question8 = new Question("Quel est le domaine de premier niveau en Belgique?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("Quel est le numéro de maison de The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);




        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5));


    }


    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        mQuestionText.setText(question.getmQuestion());
        mAnswer1Button.setText(question.getmChoiceList().get(0));
        mAnswer2Button.setText(question