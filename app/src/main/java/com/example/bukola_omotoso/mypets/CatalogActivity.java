package com.example.bukola_omotoso.mypets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import data.PetContract;
import data.PetDbHelper;

public class CatalogActivity extends AppCompatActivity {
    private TextView petTextView;
    PetDbHelper petDbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        petTextView = (TextView)findViewById(R.id.pet_textview);
        //this.deleteDatabase("pets.db");
        displayDatabaseInfo();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void displayDatabaseInfo(){

        petDbHelper = new PetDbHelper(this);
        SQLiteDatabase database = petDbHelper.getReadableDatabase();
        //String selection = PetContract.PetEntry.COLUMN_GENDER + " =?";
        //String [] selectionArgs =  new String[] {PetContract.PetEntry.GENDER_FEMALE+""};
        //Cursor cursor = database.rawQuery("SELECT * FROM "+ PetContract.PetEntry.TABLE_NAME,null);
        String[] projection = {PetContract.PetEntry._ID,PetContract.PetEntry.COLUMN_PET_NAME, PetContract.PetEntry.COLUMN_PET_BREED, PetContract.PetEntry.COLUMN_GENDER, PetContract.PetEntry.COLUMN_WEIGHT};
        Cursor cursor = database.query(PetContract.PetEntry.TABLE_NAME,projection,null,null,null,null,null);
        petTextView.setText("The pets table contains "+cursor.getCount()+" pets\n\n");
        petTextView.append("id-name-breed-gender-weight\n");
        try {
            int idIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
            int nameIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
            int breedIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
            int genderIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_GENDER);
            int weightIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_WEIGHT);
            //petTextView.setText("Number of rows in the pet database: "+cursor.getCount());
           while (cursor.moveToNext())  {
               int id = cursor.getInt(idIndex);
               String name = cursor.getString(nameIndex);
               String breed = cursor.getString(breedIndex);
               int gender = cursor.getInt(genderIndex);
               int weight  = cursor.getInt(weightIndex);
               String s = id+"-"+name+"-"+breed+"-"+gender+"-"+weight+"\n";
               petTextView.append(s);
           }


        }
        finally {
            cursor.close();
        }

    }

    public void insertDummyValues()  {
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME, "Garfield");
        values.put(PetContract.PetEntry.COLUMN_PET_BREED,"America Breed");
        values.put(PetContract.PetEntry.COLUMN_GENDER, PetContract.PetEntry.GENDER_MALE);
        values.put(PetContract.PetEntry.COLUMN_WEIGHT,14);

        petDbHelper = new PetDbHelper(this);
        SQLiteDatabase database =  petDbHelper.getWritableDatabase();
        long newRowId = database.insert(PetContract.PetEntry.TABLE_NAME,null,values);
        Log.i("ROW_COUNT",newRowId+" ");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId())   {
            case R.id.insert_dummy_data:
                insertDummyValues();
                return true;
            case R.id.delete_all_entry:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
