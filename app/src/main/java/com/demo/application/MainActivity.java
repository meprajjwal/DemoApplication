package com.demo.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MySharedPrefs" ;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing sharedPref with own file
        sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //to use the default shared preference file
        //sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void saveText(View view){
        // to write the code to save text to the local storage
        // to toast the text entered
        // to log the text entered to logcat

        Context context = getApplicationContext();
        //four places to store data : shared preferences, internal storage, external storage, sqlite db
        EditText editTextToSave = (EditText) findViewById(R.id.editTextToSave);
        String message = editTextToSave.getText().toString();

        //writing data to shared preference

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Name", "DemoApplication");
        Log.d("Name", "DemoApplication");
        editor.putString("Message", message);
        Log.d("Message", message);
        editor.commit();


        Toast.makeText(getApplicationContext(), "Message Saved to shared prefe s!!" , Toast.LENGTH_LONG).show();
    }

    public void readMessage(View view) {
        //reading from shared preference
        String messageInSharedPref = new String();
        Map<String, ?> sharedPrefData = sharedPref.getAll();
        for (Map.Entry<String,?> msg : sharedPrefData.entrySet())
            messageInSharedPref += "Key = " + msg.getKey() + ", Value = " + msg.getValue() + "\n";
        Toast.makeText(getApplicationContext(), messageInSharedPref , Toast.LENGTH_LONG).show();
    }

    //enable root and emulator detection

}
