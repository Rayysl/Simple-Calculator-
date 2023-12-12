package com.intinya.calculator20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private String currentInput = "0";
    private String operand1 = "";
    private String operand2 = "";
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        updateResultView();
    }

    public void onDigitClick(View view) {
        String digit = ((Button) view).getText().toString();
        if (currentInput.equals("0")) {
            currentInput = digit;
        } else {
            currentInput += digit;
        }
        updateResultView();
    }

    public void onOperatorClick(View view) {
        operator = ((Button) view).getText().toString();
        operand1 = currentInput;
        currentInput = "0";
    }

    public void onEqualsClick(View view) {
        operand2 = currentInput;
        double result = performOperation();
        currentInput = String.valueOf(result);
        operand1 = "";
        operand2 = "";
        operator = "";
        updateResultView();
    }

    public void onClearClick(View view) {
        currentInput = "0";
        operand1 = "";
        operand2 = "";
        operator = "";
        updateResultView();
    }

    private double performOperation() {
        double result = 0.0;
        switch (operator) {
            case "+":
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;
            case "-":
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;
            case "*":
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;
            case "/":
                if (!operand2.equals("0")) {
                    result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                } else {
                    result = Double.POSITIVE_INFINITY; // Handle division by zero
                }
                break;
            default:
                result = Double.parseDouble(currentInput);
                break;
        }
        return result;
    }

    private void updateResultView() {
        resultTextView.setText(currentInput);
    }
}