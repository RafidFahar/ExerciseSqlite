package com.example.exercisesqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class DisplayContact extends AppCompatActivity {

    private DBHelper mydb;
    EditText nama;
    EditText phone;
    EditText mail;
    EditText alamat;
    int id_To_Update = 0;

    public void run(View view)
    {
        if (nama.getText().toString().equals("") || phone.getText().toString().equals("") || mail.getText().toString().equals("") || alamat.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Data Harus Diisi Semua !", Toast.LENGTH_LONG).show();
        }if (mail.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Data Email Haarus Benar !", Toast.LENGTH_LONG).show();
        }
        else {
            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), mail.getText().toString(), alamat.getText().toString());
            Toast.makeText(getApplicationContext(),
                    "Insert Data Berhasil", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        nama = (EditText) findViewById(R.id.ETnama);
        phone = (EditText) findViewById(R.id.ETnoTelp);
        mail = (EditText) findViewById(R.id.ETemail);
        alamat = (EditText) findViewById(R.id.ETalamat);

        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String nam =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACT_COLUMN_NAMA));
                String phon =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACT_COLUMN_PHONE));
                String emaile =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACT_COLUMN_EMAIL));
                String lamat =
                        rs.getString(rs.getColumnIndex(DBHelper.CONTACT_COLUMN_ALAMAT));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);
                nama.setText((CharSequence)nam);
                nama.setFocusable(false);
                nama.setClickable(false);
                phone.setText((CharSequence)phon);
                phone.setFocusable(false);
                phone.setClickable(false);
                mail.setText((CharSequence)emaile);
                mail.setFocusable(false);
                mail.setClickable(false);
                alamat.setText((CharSequence)lamat);
                alamat.setFocusable(false);
                alamat.setClickable(false);
            }
        }
    }

}
