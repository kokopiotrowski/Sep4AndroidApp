package com.example.sep4androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrefrencesActivity extends AppCompatActivity {

    TextView showRooms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_preferences);
        showRooms = findViewById(R.id.showRoomsButton);
        registerForContextMenu(showRooms);   //This function registers the view and allows us to display a context menu
    }

   @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.nav_prefrences,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item){

    switch (item.getItemId()){
        case R.id.itemAllRooms:
            Toast.makeText(this,"all rooms",Toast.LENGTH_SHORT).show();
            return true;
        case R.id.itemBedroom:
            Toast.makeText(this,"Bedroom",Toast.LENGTH_SHORT).show();
            return true;
        case R.id.itemLivingroom:
            Toast.makeText(this,"livingrooms",Toast.LENGTH_SHORT).show();
            return true;
        default:
            return super.onContextItemSelected(item);
        }
    }
}


