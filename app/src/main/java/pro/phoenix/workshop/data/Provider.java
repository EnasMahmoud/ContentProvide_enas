package pro.phoenix.workshop.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by ANNAS on 2/11/2018.
 */

public class Provider extends ContentProvider {

    private DbHelper myHelper = null;

    private static final int WISH_LIST=1;
    private static final int WISH_ID = 2;

    private static final UriMatcher MATCHER;

    static {
        MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        MATCHER.addURI(WishContract.AUTHORITY , WishContract.MyWish.ACTION_PATH , WISH_LIST );
        MATCHER.addURI(WishContract.AUTHORITY , WishContract.MyWish.ACTION_PATH + "/#" , WISH_ID);
    }

    @Override
    public boolean onCreate() {
        myHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (MATCHER.match(uri))
        {
            case WISH_LIST:
                builder.setTables(DbSchema.TABLE_WISH);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder=WishContract.MyWish.SORT_ORDER_BY;
                break;


            case WISH_ID:
                builder.setTables(DbSchema.TABLE_WISH);
                builder.appendWhere(WishContract.MyWish._ID+" = " + uri.getLastPathSegment());

            default:
                throw new IllegalArgumentException("Unsupport URI: "+ uri);

        }

        Cursor cursor=builder.query(sqLiteDatabase , projection , selection , selectionArgs , null , null , sortOrder );

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        if (MATCHER.match(uri) != WISH_LIST )
            throw new IllegalArgumentException("Unsupport URI for insertion: "+ uri);

        else
        {
            SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
            long id=sqLiteDatabase.insert(DbSchema.TABLE_WISH,null , values);
            return getUriForId(id,uri);
        }

    }

    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            return itemUri;
        }
        throw new SQLException(
                "Problem while inserting into uri: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        int delCount = 0;
        switch (MATCHER.match(uri))
        {
            case WISH_LIST:
                delCount = sqLiteDatabase.delete(DbSchema.TABLE_WISH,selection,selectionArgs);
                break;

            case WISH_ID:
                String idStr = uri.getLastPathSegment();
                String delWhere = WishContract.MyWish._ID +" = "+idStr ;
                if (!TextUtils.isEmpty(selection))
                {
                    delWhere += " and "+ selection;
                }
                delCount=sqLiteDatabase.delete(DbSchema.TABLE_WISH,delWhere,selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);

        }
        return delCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase SQLDb = myHelper.getWritableDatabase();
        int updateCount = 0;

        switch (MATCHER.match(uri))
        {
            case WISH_LIST:
                updateCount=SQLDb.update(DbSchema.TABLE_WISH , values , selection , selectionArgs);
                break;
            case WISH_ID:
                String idStr = uri.getLastPathSegment();
                String where = WishContract.MyWish._ID + " = " + idStr ;
                if (!TextUtils.isEmpty(selection))
                {
                    where += " and " + selection;
                }
                updateCount = SQLDb.update(DbSchema.TABLE_WISH,values,where,selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Error in update URI: "+ uri);
        }
        return updateCount;
    }
}
