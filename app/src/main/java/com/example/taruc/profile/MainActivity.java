package com.example.taruc.profile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PROFILE_UPDATE_REQUEST = 1;
    public static final String PROFILE_NAME = "my.edu.tarc.lab22profile.name" ;
    public static final String PROFILE_EMAIL = "my.edu.tarc.lab22profile.email";
    private TextView textViewName, textViewEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
    }

    public void Updateprofile(View view)
    {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, PROFILE_UPDATE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //requestCode = the unique request code that identify an intent call
        //resultCode = result of an intent call; either OK or Cancel
        //data = the actual data returned by an intent call
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == PROFILE_UPDATE_REQUEST)&&(resultCode == RESULT_OK))
        {
            String name = data.getStringExtra(PROFILE_NAME);
            String email = data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.name)+ ":" + name);
            textViewEmail.setText(getString(R.string.email)+ ":" + email);
        }

    }

    public void visitBAIT2073(View view)
    {
        String uri = "http://bait2073.000webhostapp.com/welcome.html";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
    public void showMain(View view)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }
    public void showDial(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"0123456789"));
        startActivity(intent);
    }
    public void showSendTo(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+"seekt@tarc.edu.my"));
        //verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size()>0;
        //start an activity if it's safe
        if(isIntentSafe) {
            startActivity(intent);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause");
    }
}
