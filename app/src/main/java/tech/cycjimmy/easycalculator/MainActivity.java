package tech.cycjimmy.easycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_0;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_point;
    Button button_clear;
    Button button_del;
    Button button_plus;
    Button button_minus;
    Button button_multiply;
    Button button_divide;
    Button button_equal;
    EditText et_input;

    boolean clear_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 实例化
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_point = findViewById(R.id.button_point);
        button_clear = findViewById(R.id.button_clear);
        button_del = findViewById(R.id.button_del);
        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_multiply = findViewById(R.id.button_multiply);
        button_divide = findViewById(R.id.button_divide);
        button_equal = findViewById(R.id.button_equal);
        et_input = findViewById(R.id.et_input);

        // eventBind
        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();

        switch (v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_point:

                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break;

            case R.id.button_plus:
            case R.id.button_minus:
            case R.id.button_multiply:
            case R.id.button_divide:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;

            case R.id.button_clear:
                str = "";
                et_input.setText("");
                break;

            case R.id.button_del:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                } else if (!str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;

            case R.id.button_equal:
                getResult();
                break;
        }
    }

    private void getResult() {
        String exp = et_input.getText().toString();

        if (exp.equals("")) {
            return;
        }

        if (!exp.contains(" ")) {
            return;
        }

        if (clear_flag) {
            clear_flag = false;
            return;
        }
        clear_flag = true;

        String s1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        String s2 = exp.substring(exp.indexOf(" ") + 3);

        if (!s1.equals("") && s2.equals("")) {
            return;
        }

        if (s1.equals("") && s2.equals("")) {
            et_input.setText("");
            return;
        }

        double result = 0;

        if (!s2.equals("")) {

            double d1;

            if (s1.equals("")) {
                d1 = 0;
            } else {
                d1 = Double.parseDouble(s1);
            }

            double d2 = Double.parseDouble(s2);

            switch (op) {
                case "+":
                    result = d1 + d2;
                    break;

                case "-":
                    result = d1 - d2;
                    break;

                case "×":
                    result = d1 * d2;
                    break;

                case "÷":
                    if (d2 == 0) {
                        result = 0;
                    } else {
                        result = d1 / d2;
                    }
                    break;
            }

            if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {
                int r = (int) result;
                et_input.setText(r + "");
            } else {
                et_input.setText(result + "");
            }
        }
    }
}
