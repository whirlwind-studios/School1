package whirlwind.com.school1.activity;

import android.os.Bundle;

import whirlwind.com.school1.R;
import whirlwind.com.school1.backend.Credentials;
import whirlwind.com.school1.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!Credentials.isSignedIn())
            Credentials.autoSignin(this);
    }
}