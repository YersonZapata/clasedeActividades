package com.zapata.yerson.guiaturistica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.hotels:
                Intent h =new Intent(this, HotelsActivity.class);
                startActivity(h);
                break;
            case R.id.bares:
                Intent b =new Intent(this, BarsActivity.class);
                startActivity(b);
                break;
            case R.id.sitios:
                Intent s =new Intent(this, SitiosActivity.class);
                startActivity(s);
                break;
            case R.id.demografia:
                Intent d =new Intent(this, DemografiaActivity.class);
                startActivity(d);
                break;
            case R.id.acercade:
                Intent ac =new Intent(this, AboutActivity.class);
                startActivity(ac);
                break;
            default:
                Toast.makeText(MainActivity.this,"Se genero un error"+id,Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
