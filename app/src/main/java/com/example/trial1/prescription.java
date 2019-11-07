package com.example.trial1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class prescription extends AppCompatActivity
{

    DatabaseP d;
    EditText e1;
    String e2;
    EditText e3;
    EditText e4;
    EditText e5;
    EditText e7;
    EditText e8;
    EditText n1;
    EditText n2;
    EditText n3;
    EditText m1;
    EditText m2;
    EditText m3;
    ImageButton b1;
    TextView t;
    String timestamp;
    //String e6
    MultiAutoCompleteTextView mv1,mv2,mv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        d = new DatabaseP(this);
        e1 = (EditText) findViewById(R.id.name);
        e3 = (EditText) findViewById(R.id.opd);
        e4 = (EditText) findViewById(R.id.age);
        e5 = (EditText) findViewById(R.id.complaint);
        m1 = (EditText) findViewById(R.id.medicine);
        m2 = (EditText) findViewById(R.id.medicine2);
        m3 = (EditText) findViewById(R.id.medicine3);
        n1 = (EditText) findViewById(R.id.n1);
        n2 = (EditText) findViewById(R.id.n2);
        n3 = (EditText) findViewById(R.id.n3);
        e7 = (EditText) findViewById(R.id.tests);
        e8 = (EditText) findViewById(R.id.doc);
        b1 = (ImageButton) findViewById(R.id.save);

        mv1 = (MultiAutoCompleteTextView)findViewById(R.id.m1);
        String medArr[] = getResources().getStringArray(R.array.med_array);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, medArr);
        mv1.setAdapter(arrayAdapter);
        mv1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        mv2 = (MultiAutoCompleteTextView) findViewById(R.id.m2);
        mv2.setAdapter(arrayAdapter);
        mv2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        mv3 = (MultiAutoCompleteTextView) findViewById(R.id.m3);
        mv3.setAdapter(arrayAdapter);
        mv3.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        //e6 = mv1.getText().toString()+" "+n1.getText().toString()+" "+m1.getText().toString() ;
        //+ mv2.getText().toString()+" "+n2.getText().toString()+" "+m2.getText().toString() + mv3.getText().toString()+" "+n3.getText().toString()+" "+m3.getText().toString();

        timestamp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss'Z'").format(new Timestamp(System.currentTimeMillis()));
        e2 = timestamp;

        t = (TextView) findViewById(R.id.date);
        t.setText(timestamp);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2;
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = "Medicine: "+mv1.getText().toString()+" Quantity: "+n1.getText().toString()+" Notes: "+m1.getText().toString() + "\nMedicine: "+ mv2.getText().toString()+" Quantity: "+n2.getText().toString()+" Notes:"+m2.getText().toString() +"\nMedicine: "+ mv3.getText().toString()+" Quantity:"+n3.getText().toString()+" Notes:"+m3.getText().toString();
                String s7 = e7.getText().toString();
                String s8 = e8.getText().toString();

                if (s1.equals("") || s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("") ||s8.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean insert  = d.insert(s1,s2,s3,s4,s5,s6,s7,s8);
                    if (insert == true)
                    {
                        Toast.makeText(getApplicationContext(), "sucessfully saved", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Could not be saved ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void onButtonClick(View v)
    {
        if (v.getId()== R.id.home)
        {
            Intent i=new Intent(prescription.this,firstpage.class);
            startActivity(i);
        }

    }

    public void onButton2Click(View v)
    {
        if (v.getId()== R.id.logout)
        {
            Intent i=new Intent(prescription.this,MainActivity.class);
            startActivity(i);
        }

    }

    public void onButton5Click(View v)
    {
        if (v.getId()== R.id.exam)
        {
            Intent i=new Intent(prescription.this,examination.class);
            startActivity(i);
        }

    }
    public void onButton1Click(View v)
    {
        if (v.getId()== R.id.addappointment)
        {
            Intent i=new Intent(prescription.this,addappointment.class);
            startActivity(i);
        }

    }


}