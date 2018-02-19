package pro.phoenix.workshop.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ANNAS on 2/11/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DbSchema.DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbSchema.CREATE_TABLE_WISH);

        ContentValues values = new ContentValues();

        values.put(WishContract.MyWish.TITLE , "MyApp");
        values.put(WishContract.MyWish.DESCRIPTION, "My only Hope");
        db.insert(DbSchema.TABLE_WISH,null,values);

        values.put(WishContract.MyWish.TITLE , "Camera");
        values.put(WishContract.MyWish.DESCRIPTION, "Amaaaaazing");
        db.insert(DbSchema.TABLE_WISH,null,values);

        values.put(WishContract.MyWish.TITLE , "Phone");
        values.put(WishContract.MyWish.DESCRIPTION, "my friend");
        db.insert(DbSchema.TABLE_WISH,null,values);

        values.put(WishContract.MyWish.TITLE , "travel");
        values.put(WishContract.MyWish.DESCRIPTION, "funny");
        db.insert(DbSchema.TABLE_WISH,null,values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DbSchema.DROP_TABLE_WISH);
        onCreate(db);
    }
}
