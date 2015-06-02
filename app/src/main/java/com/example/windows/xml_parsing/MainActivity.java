package com.example.windows.xml_parsing;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

    // All static variables
    static final String URL = "http://app.szhernandez.dx.am/materias.xml";
    // XML node keys
    static final String KEY_MATERIA = "materia"; // Nodo padre
    static final String KEY_ID = "id_materia";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_PROFESOR = "profesor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_MATERIA);
        // Ciclo para obtener todos los nodos del XML
        for (int i = 0; i < nl.getLength(); i++) {
            // Creando un nuevo HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // Agregando nodos hijos-- HashMap key => value
            map.put(KEY_ID, "ID: " +parser.getValue(e, KEY_ID));
            map.put(KEY_NOMBRE,"Materia: " + parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_PROFESOR, "Profesor: " + parser.getValue(e, KEY_PROFESOR));


            // Agregando HashList al ArrayList
            menuItems.add(map);
        }
        // Agregando menuItems al ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.list_item,
                new String[] { KEY_ID, KEY_NOMBRE, KEY_PROFESOR }, new int[] {
                R.id.tv_idmateria, R.id.tv_nombre, R.id.tv_profesor });

        setListAdapter(adapter);

        // Seleccionando un solo elemento del listView
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Obteniendo los valores para el elemento seleccionado
                String idmateria = ((TextView) view.findViewById(R.id.tv_idmateria)).getText().toString();
                String nombre = ((TextView) view.findViewById(R.id.tv_nombre)).getText().toString();
                String profesor = ((TextView) view.findViewById(R.id.tv_profesor)).getText().toString();

                // Comenzando el nuevo intento, agregando los valores obtenidos del elemento del ListView
                Intent in = new Intent(getApplicationContext(), mostrar_registro.class);
                in.putExtra(KEY_ID, idmateria);
                in.putExtra(KEY_NOMBRE, nombre);
                in.putExtra(KEY_PROFESOR, profesor);
                startActivity(in);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }
}
