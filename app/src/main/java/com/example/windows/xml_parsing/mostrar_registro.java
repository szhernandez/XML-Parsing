package com.example.windows.xml_parsing;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class mostrar_registro extends Activity {

    static final String KEY_ID = "id_materia";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_PROFESOR = "profesor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registro);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous intent
        String idmateria = in.getStringExtra(KEY_ID);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String profesor = in.getStringExtra(KEY_PROFESOR);

        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.lblidmateria);
        TextView lblCost = (TextView) findViewById(R.id.lblnombre);
        TextView lblDesc = (TextView) findViewById(R.id.lblprofesor);

        lblName.setText(idmateria);
        lblCost.setText(nombre);
        lblDesc.setText(profesor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mostrar_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }
}
