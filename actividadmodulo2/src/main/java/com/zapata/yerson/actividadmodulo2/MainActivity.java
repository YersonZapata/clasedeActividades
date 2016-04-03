package com.zapata.yerson.actividadmodulo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, eProy, eNotaFinal, eLab;
    TextView tExpo, tProy, tLab,pA,pO,pE;
    Button bCalcular, bLimpiar;
    Integer PorExp = 15, PorProy = 35, PorLab = 50;
    private Locale miLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tExpo = (TextView) findViewById(R.id.tExp);
        tProy = (TextView) findViewById(R.id.tPro);
        tLab = (TextView) findViewById(R.id.tPrac);
        pE = (TextView) findViewById(R.id.pE);
        pA = (TextView) findViewById(R.id.pA);
        pO = (TextView) findViewById(R.id.pO);
        eExpo = (EditText) findViewById(R.id.eExp);
        eProy = (EditText) findViewById(R.id.eProy);
        eLab = (EditText) findViewById(R.id.ePrac);
        eNotaFinal = (EditText) findViewById(R.id.eFinal);
        bCalcular = (Button) findViewById(R.id.bCalcular);
        bLimpiar =(Button) findViewById(R.id.bLimpiar);

        pE.setText("(" + PorExp + "%):");
        pO.setText("(" + PorProy + "%): ");
        pA.setText("(" + PorLab + "%): ");


        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check1 = eExpo.getText().toString(), check2 = eLab.getText().toString(), check3 = eProy.getText().toString();
                if (check1.equals("") || check2.equals("") || check3.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                } else {

                    double exp = Double.parseDouble(eExpo.getText().toString());
                    double pro = Double.parseDouble(eProy.getText().toString());
                    double lab = Double.parseDouble(eLab.getText().toString());
                    if ((exp >= 0 && exp <= 5) && (pro >= 0 && pro <= 5) && (lab >= 0 && lab <= 5)) {
                        double nFinal = (PorExp * exp + PorProy * pro + PorLab * lab) / 100;
                        DecimalFormat df= new DecimalFormat("0.0");
                        eNotaFinal.setText(String.valueOf(df.format(nFinal)));
                    } else {
                        Toast.makeText(MainActivity.this, R.string.aviso, Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eExpo.setText("0");
                eLab.setText("0");
                eProy.setText("0");
                eNotaFinal.setText("0");

            }
        });
    }

    @Override //Para mostrar el menu en esta activiad
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override //Logica al seleccionar un elemento del menu
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemConfigurar) {
          //  Toast.makeText(this, "Ha precionada configurar", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, SettingActivity.class); //Lineas para abrir otra actividad
            // i.putExtra("pProy",35);
            // i.putExtra("ppExp",15);
            // i.putExtra("pprac",50);

            i.putExtra("pProy", PorProy);
            i.putExtra("ppExp", PorExp);
            i.putExtra("pprac", PorLab);
            //startActivity(i);
            startActivityForResult(i, 1234);
        }
        if(id==R.id.acercade){
            Toast.makeText(this, R.string.producidopor, Toast.LENGTH_LONG).show();
        }
        if(id==R.id.cambiar){

            if (Locale.getDefault().getLanguage().equals("en")){
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "es").commit();
                miLocale = new Locale("es");
                Locale.setDefault(miLocale);
                Configuration config = getBaseContext().getResources().getConfiguration();
                config.locale=miLocale;
                Toast.makeText(this, "Español", Toast.LENGTH_LONG).show();
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }else{
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
                miLocale = new Locale("en");
                Locale.setDefault(miLocale);
                Configuration config = getBaseContext().getResources().getConfiguration();
                config.locale=miLocale;
                Toast.makeText(this, "English", Toast.LENGTH_LONG).show();
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK) {

            PorProy = Integer.parseInt(data.getExtras().getString("prPro"));
            PorExp = Integer.parseInt(data.getExtras().getString("prExp"));
            PorLab = Integer.parseInt(data.getExtras().getString("prPrac"));
            //Actualizar los textView
            pE.setText("(" + PorExp + "%):");
            pO.setText("(" + PorProy + "%): ");
            pA.setText("(" + PorLab + "%): ");

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        //super.onCreate(savedInstanceState, persistentState);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();
        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
