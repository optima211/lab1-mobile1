package com.example.user.lab1_calc;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user.lab1_calc.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = 'x';
    private static final char DIVISION = '/';
    private static final char SIN = 'S';

    private char CURRENT_ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo;
    private double result;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = binding.editText.getText().toString();
                    if (!text.contains(".")) {
                        text = text + ".";
                    }
                    binding.editText.setText(text);
                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNumber("9");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean canDo = CheckValuesBeforeOperation();
                    if (!canDo) return;

                    CURRENT_ACTION = ADDITION;
                    String text = decimalFormat.format(valueOne) + " + ";
                    binding.infoTextView.setText(text);
                    binding.editText.setText(null);

                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean canDo = CheckValuesBeforeOperation();
                    if (!canDo) return;

                    CURRENT_ACTION = SUBTRACTION;
                    String text = decimalFormat.format(valueOne) + " - ";
                    binding.infoTextView.setText(text);
                    binding.editText.setText(null);

                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean canDo = CheckValuesBeforeOperation();
                    if (!canDo) return;

                    CURRENT_ACTION = MULTIPLICATION;
                    String text = decimalFormat.format(valueOne) + " * ";
                    binding.infoTextView.setText(text);
                    binding.editText.setText(null);

                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean canDo = CheckValuesBeforeOperation();
                    if (!canDo) return;

                    CURRENT_ACTION = DIVISION;
                    String text = decimalFormat.format(valueOne) + " / ";
                    binding.infoTextView.setText(text);
                    binding.editText.setText(null);

                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean canDo = CheckValuesBeforeOperation();
                    if (!canDo) return;

                    CURRENT_ACTION = SIN;
                    String text = "SIN(" + decimalFormat.format(valueOne) + ")";
                    binding.infoTextView.setText(text);
                    binding.editText.setText(null);

                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String currentEqualsText = binding.infoTextView.getText().toString();
                    if (!currentEqualsText.contains("=")) {

                        if (Double.isNaN(valueOne)) return;

                        if (CURRENT_ACTION != SIN){

                            valueTwo = Double.parseDouble(binding.editText.getText().toString());
                            if (Double.isNaN(valueTwo)) return;
                            binding.editText.setText(null);

                            switch (CURRENT_ACTION) {
                                case ADDITION:
                                    result = valueOne + valueTwo;
                                    break;
                                case SUBTRACTION:
                                    result = valueOne - valueTwo;
                                    break;
                                case MULTIPLICATION:
                                    result = valueOne * valueTwo;
                                    break;
                                case DIVISION:
                                    if (valueTwo != 0) {
                                        result = valueOne / valueTwo;
                                    } else {
                                        binding.editText.setText("На 0 делить нельзя");
                                        result = Double.NaN;
                                    }
                                    break;
                            }

                            if (Double.isNaN(result)) return;

                            String text = binding.infoTextView.getText().toString() + decimalFormat.format(valueTwo) + " = " + decimalFormat.format(result);
                            binding.infoTextView.setText(text);
                            valueOne = Double.NaN;
                            valueTwo = Double.NaN;
                            CURRENT_ACTION = '0';
                        }
                        else{

                            result =Math.sin(Math.toRadians(valueOne));

                            String text = binding.infoTextView.getText().toString() + " = " + decimalFormat.format(result);
                            binding.infoTextView.setText(text);
                            valueOne = Double.NaN;
                            valueTwo = Double.NaN;
                            CURRENT_ACTION = '0';
                        }

                    } else {
                        valueOne = result;
                        result = Double.NaN;
                        Boolean isInteger = (valueOne % 1) == 0;
                        String intText = (int) valueOne + "";
                        String doubleText = valueOne + "";
                        String text = (isInteger ? intText : doubleText) + "";
                        binding.infoTextView.setText(text);
                        binding.editText.setText("");
                        CURRENT_ACTION = '0';
                    }
                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (binding.editText.getText().length() > 0) {
                        CharSequence currentText = binding.editText.getText();
                        binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                    } else {
                        valueOne = Double.NaN;
                        valueTwo = Double.NaN;
                        binding.editText.setText("");
                        binding.infoTextView.setText("");
                    }
                } catch (Exception ex) {
                    SetDefaults();
                }
            }
        });
    }

    private void SetNumber(String number) {
        try {
            String text = binding.editText.getText() + number;
            binding.editText.setText(text);
        } catch (Exception ex) {
            SetDefaults();
        }
    }

    private void SetDefaults() {
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        result = Double.NaN;
        binding.infoTextView.setText("");
        binding.editText.setText("");
    }

    private boolean CheckValuesBeforeOperation() {
        if (Double.isNaN(valueOne)) {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {
                Log.v(e.getMessage(), "error");
            }
        }
        if (Double.isNaN(valueOne)) {
            if (Double.isNaN(result)) return false;
            valueOne = result;
            result = Double.NaN;
        }
        return true;
    }
}
