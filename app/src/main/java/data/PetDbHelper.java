package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bukola_omotoso on 01/10/16.
 */
public class  PetDbHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pets.db";
    public static final String CREATE_PET_TABLE =
            "CREATE TABLE "+ PetContract.PetEntry.TABLE_NAME +"("+
                    PetContract.PetEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PetContract.PetEntry.COLUMN_PET_NAME+" TEXT NOT NULL, " +
                    PetContract.PetEntry.COLUMN_PET_BREED+ " TEXT, " +
                    PetContract.PetEntry.COLUMN_GENDER+ " INTEGER NOT NULL, " +
                    PetContract.PetEntry.COLUMN_WEIGHT+" INTEGER DEFAULT 0)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS pets";

    public PetDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PET_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion)    {
       db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
        onDowngrade(db, oldVersion, newVersion);
    }




}
