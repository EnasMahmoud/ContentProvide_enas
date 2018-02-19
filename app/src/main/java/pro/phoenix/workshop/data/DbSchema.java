package pro.phoenix.workshop.data;

import android.provider.BaseColumns;

/**
 * Created by ANNAS on 2/11/2018.
 */

public interface DbSchema {
    String DB_NAME = "wish.db";
    String TABLE_WISH="wish";

    String CREATE_TABLE_WISH =
            "CREATE TABLE " + TABLE_WISH + " ("+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+WishContract.MyWish.TITLE + " TEXT,"+ WishContract.MyWish.DESCRIPTION + " TEXT)";

    String DROP_TABLE_WISH = "DROP TABLE IF EXISTS "+ TABLE_WISH ;

}
