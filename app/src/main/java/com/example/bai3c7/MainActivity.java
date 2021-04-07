package com.example.bai3c7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnDraw;
    EditText edtNumber;
    LinearLayout llRandomNumber, llPrimeNumber;
    int n = 0;
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    private boolean Prime(int n)
    {
        if(n<2)
        {
            return false;
        }
        for(int i=2;i<=Math.sqrt(n);i++)
        {
            if(n%i==0)
            {
                return false;
            }
        }
        return true;
    }

    private void addEvents() {
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = Integer.parseInt(edtNumber.getText().toString());
                ButtonTask task = new ButtonTask();
                task.execute(n);
            }
        });
    }

    private void addControls() {
        btnDraw = findViewById(R.id.btnDraw);
        edtNumber = findViewById(R.id.edtNumber);
        llPrimeNumber = findViewById(R.id.llPrimeNumber);
        llRandomNumber = findViewById(R.id.llRandomNumber);
    }
    class ButtonTask extends AsyncTask<Integer,Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            llRandomNumber.removeAllViews();
            llPrimeNumber.removeAllViews();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[0];
            Button btn = new Button(MainActivity.this);
            btn.setText(value+"");
            btn.setLayoutParams(layoutParams);
            llRandomNumber.addView(btn);
            Button btnPrime = new Button(MainActivity.this);
            if(Prime(value))
            {
                btnPrime.setText(value+"");
                llPrimeNumber.addView(btnPrime);
            }
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int n = integers[0];
            for(int i=0;i<n;i++)
            {
                int value = random.nextInt(100);
                publishProgress(value);
                SystemClock.sleep(100);
            }
            return null;
        }
    }
}