package whirlwind.com.school1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import whirlwind.com.school1.Backend.Credentials;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!Credentials.isSignedIn())
            Credentials.autoSignin(this);
    }
}
