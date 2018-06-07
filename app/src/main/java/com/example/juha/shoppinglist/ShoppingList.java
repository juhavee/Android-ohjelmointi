package com.example.juha.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingList extends AppCompatActivity {

    public static Set<String> shoppingItems = new HashSet<String>();
    ArrayAdapter<String> adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnShopping:
                startActivity(new Intent(ShoppingList.this, NewShoppingItem.class));
                return true;
                case R.id.mnInstruction:
                startActivity(new Intent(ShoppingList.this, Instructions.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);
        Set<String> blaah = mySharedPreferences.getStringSet("shoppingItems", new HashSet<String>());
        shoppingItems.addAll(blaah);
        setContentView(R.layout.activity_shopping_list);

        ListView shoppingItemList = findViewById(R.id.shopping_list);

        if (shoppingItems != null && shoppingItems.size() > 0) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shoppingItems.toArray(new String[1]));
            shoppingItemList.setAdapter(adapter);
        }

    }

    public void naytaToast(String teksti) {
        int aika = Toast.LENGTH_LONG;
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, teksti, aika);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putStringSet("shoppingItems", shoppingItems);
        editor.apply();

    }
}
