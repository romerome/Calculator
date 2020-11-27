package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class CalcActivity extends AppCompatActivity {
    // Форматирование денежных сумм и процентов
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double amount = 0.0; //Сумма счёта
    private double percent = 0.15; // Процент чаевых по умолчанию
    private EditText et_amount; // Поле для ввода суммы счёта
    private SeekBar sb_percent; // Ползунок для процентов
    private TextView tv_percent; // Поле для значения процента
    private TextView tv_tip; // Поле для суммы чаевых
    private TextView tv_total; // Поле для итоговой суммы

    private Calc tipCalc = new Calc();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity);
        et_amount = findViewById(R.id.et_amount);
        sb_percent = findViewById(R.id.sb_percent);
        tv_percent = findViewById(R.id.tv_percent);
        tv_tip = findViewById(R.id.tv_tip);
        tv_total = findViewById(R.id.tv_total);
        // Для текстовых полей задаём первоначальные значения
        tv_tip.setText("0.0");
        tv_total.setText("0.0");
        et_amount.addTextChangedListener(amountTextWatcher);
        sb_percent.setOnSeekBarChangeListener(sbListener);
    }

    // Интерфейс слушателя изменений текста в EditText
    private final TextWatcher
            amountTextWatcher = new
            TextWatcher() {
                // Вызывается при изменении пользователем величины счёта
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    amount = Double.parseDouble(s.toString());
                    // Обновление полей с чаевыми и общей суммой
                    tv_tip.setText(Double.toString(tipCalc.calculateTip(amount,percent)));
                }
                @Override public void afterTextChanged(Editable s) { }

                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            };
    // Интерфейс слушателя изменений состояние SeekBar
    private final SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener() {
        // Обновление процента чаевых и итоговой суммы
        @Override public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
            percent = progress / 100.0; // Назначение процента чаевых
            // Вычисление чаевых и общей суммы. Вывод их на экран.
            tv_percent.setText(percentFormat.format(percent));
            tv_tip.setText(currencyFormat.format(tipCalc.calculateTip(amount, percent)));
            tv_total.setText(currencyFormat.format(tipCalc.calculateTotal(amount, percent)));
        }
        @Override public void onStartTrackingTouch(SeekBar seekbar) {

        }
        @Override public void   onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
