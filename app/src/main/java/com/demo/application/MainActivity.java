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

import java.io.FileInputStream;
import java.io.FileOutputStream;
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


        try {

            //writing data to shared preference

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Name", "DemoApplication");
            Log.d("Name", "DemoApplication");
            editor.putString("Message", message);
            Log.d("Message", message);
            editor.commit();

            //writing to a file in internal storage


            FileOutputStream fOut = openFileOutput("DemoAppFile",MODE_PRIVATE); //DemoAppFile is the filename to save data to
            fOut.write(message.getBytes());
            fOut.close();

            Toast.makeText(getBaseContext(),"Message Saved to file and shared pref",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    public void readMessage(View view) {

        try {
            //reading from shared preference

            String messageInSharedPref = new String();
            Map<String, ?> sharedPrefData = sharedPref.getAll();
            for (Map.Entry<String,?> msg : sharedPrefData.entrySet())
                messageInSharedPref += "Key = " + msg.getKey() + ", Value = " + msg.getValue() + "\n";

            //reading from file saved in internal storage
            FileInputStream fin = openFileInput("DemoAppFile");
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }

            Toast.makeText(getBaseContext(),"Shared pref read : \n"+ messageInSharedPref + "\nFile read : \n " + temp ,Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
        }
    }

    //enable root and emulator detection

}
