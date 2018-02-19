package pro.phoenix.workshop.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ANNAS on 2/11/2018.
 */

public final class WishContract {

    public static final String AUTHORITY="pro.phoenix.wish";
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    public static final class MyWish implements BaseColumns
    {
        public static final String ACTION_PATH="myWish";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(WishContract.CONTENT_URI , ACTION_PATH);
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String[]PROJECTION_ALL = {_ID , TITLE,DESCRIPTION};
        public static final String SORT_ORDER_BY = TITLE +" ASC";
    }
}
