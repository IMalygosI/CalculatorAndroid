package com.example.calculatorandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultField;
    EditText numberField;
    TextView operationField;
    Double operand = null;
    String lastOperation = "=";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultField = findViewById(R.id.resultField);
        numberField = findViewById(R.id.editTextText);
        operationField = findViewById(R.id.textTextView);

        findViewById(R.id.buttonPlus).setOnClickListener((view)->onOperationClick("+"));
        findViewById(R.id.buttonMinus).setOnClickListener((view)->onOperationClick("-"));
        findViewById(R.id.Umno).setOnClickListener((view)->onOperationClick("*"));
        findViewById(R.id.Delit).setOnClickListener((view)->onOperationClick("/"));
        findViewById(R.id.ravno).setOnClickListener((view)->onOperationClick("="));

        findViewById(R.id.comma).setOnClickListener((view)->onNumberClick(","));
        findViewById(R.id.nuull).setOnClickListener((view)->onNumberClick("0"));
        findViewById(R.id.one).setOnClickListener((view)->onNumberClick("1"));
        findViewById(R.id.two).setOnClickListener((view)->onNumberClick("2"));
        findViewById(R.id.three).setOnClickListener((view)->onNumberClick("3"));
        findViewById(R.id.four).setOnClickListener((view)->onNumberClick("4"));
        findViewById(R.id.five).setOnClickListener((view)->onNumberClick("5"));
        findViewById(R.id.six).setOnClickListener((view)->onNumberClick("6"));
        findViewById(R.id.seven).setOnClickListener((view)->onNumberClick("7"));
        findViewById(R.id.ningh).setOnClickListener((view)->onNumberClick("8"));
        findViewById(R.id.nine).setOnClickListener((view)->onNumberClick("9"));

        findViewById(R.id.buttonPlusMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bu2ttonPlusMinus();
            }
        });

        findViewById(R.id.buttonInverse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInverseClick();
            }
        });
        findViewById(R.id.buttonSquare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSquareClick();
            }
        });
        findViewById(R.id.buttonCE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCEClick();
            }
        });
        findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCClick();
            }
        });
        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick();
            }
        });
        findViewById(R.id.buttonSqrt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSqrtClick();
            }
        });
        findViewById(R.id.buttonPercent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPercentClick();
            }
        });
    }
    public void bu2ttonPlusMinus(){
        String currentText = numberField.getText().toString();
        if(!currentText.startsWith("-")){
            numberField.setText("-" + currentText);
        } else {
            numberField.setText(currentText.substring(1));
        }
    }
    private void onInverseClick() {
        String numberStr = numberField.getText().toString();
        if (!numberStr.isEmpty()) {
            Double number = Double.valueOf(numberStr);
            if (number != 0) {
                operand = 1 / number;
                resultField.setText(operand.toString());
                numberField.setText("");
            }
        }
    }
    private void onPercentClick() {
        String numberStr = numberField.getText().toString();
        if (!numberStr.isEmpty()) {
            Double number = Double.valueOf(numberStr);
            operand = number / 100;
            resultField.setText(operand.toString());
            numberField.setText("");
        }
    }
    private void onSquareClick() {
        String numberStr = numberField.getText().toString();
        if (!numberStr.isEmpty()) {
            Double number = Double.valueOf(numberStr);
            operand = number * number;
            resultField.setText(operand.toString());
            numberField.setText("");
        }
    }
    private void onCEClick() {
        numberField.setText("");
    }
    private void onCClick() {
        numberField.setText("");
        resultField.setText("");
        operand = null;
        lastOperation = "=";
    }
    private void onDeleteClick() {
        String text = numberField.getText().toString();
        if (!text.isEmpty()) {
            numberField.setText(text.substring(0, text.length() - 1));
        }
    }
    private void onSqrtClick() {
        String numberStr = numberField.getText().toString();
        if (!numberStr.isEmpty()) {
            Double number = Double.valueOf(numberStr);
            if (number >= 0) {
                operand = Math.sqrt(number);
                resultField.setText(operand.toString());
                numberField.setText("");
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }
    public void onNumberClick(String number){
        numberField.append(number);
        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onOperationClick(String op){

        String number = numberField.getText().toString();
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }
    private void performOperation(Double number, String operation){
        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}