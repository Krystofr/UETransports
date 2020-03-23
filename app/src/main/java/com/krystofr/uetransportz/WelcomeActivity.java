package com.krystofr.uetransportz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button button;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        dialog = new ProgressDialog(this);
        button = findViewById(R.id.btn_to_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            button.setBackground(getResources().getDrawable(R.drawable.btn_release_transition));
                            button.setTextColor(getResources().getColor(R.color.white));
                        }
                        else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                            button.setBackground(getResources().getDrawable(R.drawable.login_btn_bg));
                            button.setTextColor(getResources().getColor(R.color.gray));
                            moveToNext();
                        }

                        return true;
                    }
                });

            }
        });
    }


    public void moveToNext() {

        dialog.setTitle("Connecting to servers...");
        dialog.setMessage("A minute please");
        dialog.setCancelable(false);
        dialog.show();

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Intent intent = new Intent(WelcomeActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
