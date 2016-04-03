package com.zapata.yerson.actividadmodulo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    EditText eExp,ePra,ePro;
    Button bSave,bLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        eExp =(EditText) findViewById(R.id.epExp);
        ePra =(EditText) findViewById(R.id.epPrac);
        ePro =(EditText) findViewById(R.id.epProy);
        bSave=(Button) findViewById(R.id.bCalcular);
        bLimpiar=(Button) findViewById(R.id.bLimpiar);

        Bundle extras =getIntent().getExtras(); //para poder acceder a las etiquetas (se pueden cargar muchas variables sin ningun tipo de formato)

        eExp.setText(String.valueOf(extras.getInt("ppExp")));
        ePra.setText(String.valueOf(extras.getInt("pprac")));
        ePro.setText(String.valueOf(extras.getInt("pProy")));

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check1 = eExp.getText().toString(), check2 = ePro.getText().toString(), check3 = ePra.getText().toString();
                if (check1.equals("") || check2.equals("") || check3.equals("")) {
                    Toast.makeText(SettingActivity.this, "Ningun campo debe estar vacio", Toast.LENGTH_LONG).show();
                } else {
                    double exp = Double.parseDouble(eExp.getText().toString());
                    double pro = Double.parseDouble(ePra.getText().toString());
                    double lab = Double.parseDouble(ePro.getText().toString());
                    if ((exp + pro + lab) == 100) {
                        Intent i = new Intent();
                        i.putExtra("prPro", ePro.getText().toString());
                        i.putExtra("prExp", eExp.getText().toString());
                        i.putExtra("prPrac", ePra.getText().toString());
                        setResult(RESULT_OK, i);
                        finish();
                    } else {
                        Toast.makeText(SettingActivity.this,R.string.error2, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eExp.setText("0");
                ePra.setText("0");
                ePro.setText("0");


            }
        });

    }
}
