package com.example.android.cartoonquiz;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {
    int boxResult;
    private RadioGroup radioGroup;
    private EditText editText;
    TextView questionText;
    TextView questionNumberText;

    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;

    private int questionIndex = 1;
    boolean name1 = false;
    boolean name2 = false;
    boolean name3 = false;
    boolean name4 = false;
    // Declaring and initializing KEY_INDEX to serve as NVP (Name-Value Pair).
    private static final String KEY_INDEX = "index";
    private static final String KEY_INDEX_2 = "index2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        if (savedInstanceState != null) {
            boxResult = savedInstanceState.getInt(KEY_INDEX, boxResult);
        }
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(KEY_INDEX_2, questionIndex);
        }
        Button mButton = findViewById(R.id.submitButton);

        if (questionIndex < 6) {
            questionIndex--;
        }
        submitButton(mButton);
        checkAnswer();
    }

    // Adding a state to the checkbox questions, if the var. is checked. turn to true, otherwise turn to false
    private CheckBox.OnCheckedChangeListener onCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.checkBoxName1: {
                            name1 = isChecked;
                            checkAnswer();
                            break;
                        }
                        case R.id.checkBoxName2: {
                            name2 = isChecked;
                            checkAnswer();
                            break;
                        }
                        case R.id.checkBoxName3: {
                            name3 = isChecked;
                            checkAnswer();
                            break;
                        }
                        case R.id.checkBoxName4: {
                            name4 = isChecked;
                            checkAnswer();
                            break;
                        }
                    }
                }
            };

    // Adding a checkBox questions
    private void addCheckBoxGroup() {
        RadioGroup radioGroupLayout = findViewById(R.id.answerGroup);

        CheckBox checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText(R.string.name1_text);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setId(R.id.checkBoxName1);
        checkBox.setChecked(false);
        radioGroupLayout.addView(checkBox);

        checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText(R.string.name2_text);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setId(R.id.checkBoxName2);
        checkBox.setChecked(false);
        radioGroupLayout.addView(checkBox);

        checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText(R.string.name3_text);
        checkBox.setId(R.id.checkBoxName3);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setChecked(false);
        radioGroupLayout.addView(checkBox);

        checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText(R.string.name4_text);
        checkBox.setId(R.id.checkBoxName4);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setChecked(false);
        radioGroupLayout.addView(checkBox);
    }

    // Linking variables with the questions from the radioGroup View
    public void setupView() {
        questionText = findViewById(R.id.questionText);
        questionNumberText = findViewById(R.id.question_number);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")

    // When the user will press the button it will store the value and increment the question number
    public void submitButton(View view) {

        //Checks if we got to the end of the quiz, if that's true it exits the function.
        if (questionIndex == 7) return;
        //Declarations to all the required variables to change the app's appearance.
        switch (questionIndex) {
            case 1: {
                questionText.setText("What's the title of Cinderella's song in the movie " +
                        "with the same name?");
                option1.setText("A dream is wish your hearts makes;");
                option2.setText("I have a dream;");
                option3.setText("It's my dream you take;");
                option4.setText("I'm just a dreamer");
                checkAnswer();
                break;
            }
            case 2: {
                checkAnswer();
                questionText.setText("Who casts the voice of Rapunzel in \"Tangled\"");
                option1.setText("Hene Woods;");
                option2.setText("Donna Murpy;");
                option3.setText("Mandy Moor;");
                option4.setText("Miley Cyrus");
                break;
            }
            case 3: {
                checkAnswer();
                questionText.setText("In \"Moana\", Moana has a pet named");
                option1.setText("Hiphop;");
                option2.setText("Hanna;");
                option3.setText("Heihei;");
                option4.setText("Hammurabi");
                break;
            }
            //EditText question.
            case 4: {
                checkAnswer();
                questionText.setText("What's the name of Timon's song from \"The Lion King\"");
                radioGroup.removeAllViews();
                addEditText();
                break;
            }
            //Editable text field.
            case 5: {
                checkAnswer();
                questionText.setText("In \"Sleeping Beauty\" the names " +
                        "of Aurora's good fairies are:");
                radioGroup.removeAllViews();
                addCheckBoxGroup();
                break;
            }
            //Case 6 => we have formed
            case 6: {
                questionText.setText("");
                Toast toast = Toast.makeText(this, "Congratulations! Your score is: "
                                + boxResult + " point(s) out of 6p.",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER |
                        Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                radioGroup.removeAllViews();
                break;
            }
            default:
                break;
        }
        //Recording the answer and move on to the next question.
        questionIndex++;
        if (questionIndex < 7) questionNumberText.setText("Question " + questionIndex + ":");
        else
            questionNumberText.setText("\"Even miracles take a little time!\"");
    }

    // Denotes that the annotated element should only be called on the given API level or higher.
    @RequiresApi(api = Build.VERSION_CODES.M)
    // Indicates that Lint should ignore the specified warnings for the annotated element.
    @SuppressLint("SetTextI18n")

    // This method create a nea editText View
    private void addEditText() {
        RadioGroup radioGroupLayout = findViewById(R.id.answerGroup);
        editText = new EditText(MainActivity.this);
        editText.setHint("Matata");
        editText.setTextColor(WHITE);
        editText.setWidth(radioGroupLayout.getWidth());
        editText.setId(R.id.answerEditText);
        radioGroupLayout.addView(editText);
    }

    // This method checking all the answers, if it is true => will add one to the boxResult => otherwise 0
    public void checkAnswer() {
        radioGroup = findViewById(R.id.answerGroup);
        switch (questionIndex) {
            case 1: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option2) {
                    boxResult += 1;
                }
                radioGroup.check(-1);
                break;
            }
            case 2: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option1) {
                    boxResult += 1;
                }
                radioGroup.check(-1);
                break;
            }
            case 3: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option3) {
                    boxResult += 1;

                }
                radioGroup.check(-1);
                break;
            }
            case 4: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option3) {
                    boxResult += 1;
                }
                radioGroup.check(-1);
                break;
            }
            case 5: {
                editText = findViewById(R.id.answerEditText);
                if (editText.getText().toString().trim().equalsIgnoreCase("hakuna matata")) {
                    boxResult += 1;
                }
            }
            case 6: {
                if (!name1 && name2 && name3 && name4) {
                    boxResult += 1;
                }
            }
            default:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
         /*
        *Save UI state changes to the savedInstanceState.
        * This bundle will be passed to onCreate if the process is
        * killed and restarted.
        * Storing a NVP ("Name-Value Pair") map, and it will get
        * passed in to onCreate () method.
        */
        savedInstanceState.putInt(KEY_INDEX, boxResult);
        savedInstanceState.putInt(KEY_INDEX_2, questionIndex);
    }
}
