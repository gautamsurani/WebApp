package tech.fraction.webapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



import tech.fraction.webapp.R;

public class ForgotPasswordActivity extends AppCompatActivity {


TextView btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: onRippleViewClick


                Toast.makeText(ForgotPasswordActivity.this,"testing ripple effect",Toast.LENGTH_LONG).show();
            }
        });

    }
}




