package com.example.munsamonth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class CreationDialog extends AppCompatActivity {
    private Button backButton, confirmButton;
    private EditText nameBox, intervalBox, moneyBox, hpd, dpw, wpm, mpy;
    private ToggleButton toggleButton;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_dialog);

        backButton = findViewById(R.id.backButton);
        confirmButton = findViewById(R.id.confirmButton);
        nameBox = findViewById(R.id.nameBox);
        intervalBox = findViewById(R.id.intervalBox);
        moneyBox = findViewById(R.id.moneyBox);
        hpd = findViewById(R.id.hpd);
        dpw = findViewById(R.id.dpw);
        wpm = findViewById(R.id.wpm);
        mpy = findViewById(R.id.mpy);
        toggleButton = findViewById(R.id.toggleButton);
        spinner = findViewById(R.id.spinner);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("STATE", "MAINMENU");
                finish();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("STATE", "CONFIRM");
                FinanceProject newProject = new FinanceProject("New Project");
                MoneyPeriod.PERIOD period;
                switch(spinner.getSelectedItem().toString()) {
                    case "hours":
                        period = MoneyPeriod.PERIOD.HOURLY;
                        break;
                    case "days":
                        period = MoneyPeriod.PERIOD.DAILY;
                        break;
                    case "weeks":
                        period = MoneyPeriod.PERIOD.WEEKLY;
                        break;
                    case "months":
                        period = MoneyPeriod.PERIOD.MONTHLY;
                        break;
                    case "years":
                        period = MoneyPeriod.PERIOD.YEARLY;
                        break;
                    default:
                        Log.d("ERROR", "PERIOD SWITCH ERROR");
                        return;
                }
                String transactionName = nameBox.getText().toString();
                double amount = Double.parseDouble(moneyBox.getText().toString());
                int interval = Integer.parseInt(intervalBox.getText().toString());

                int hrs = Integer.parseInt(hpd.getText().toString());
                int days = Integer.parseInt(dpw.getText().toString());
                int weeks = Integer.parseInt(wpm.getText().toString());
                int months = Integer.parseInt(mpy.getText().toString());
                if(toggleButton.getText().equals("EXPENSE")) {
                    newProject.addExpense(transactionName, period, amount, interval, hrs, days, weeks, months);
                } else {
                    newProject.addIncome(transactionName, period, amount, interval, hrs, days, weeks, months);
                }
                GlobalData.projectList.add(newProject);
                finish();
            }
        });
    }
}