package pro.phoenix.workshop.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import pro.phoenix.workshop.R;

/**
 * Created by ANNAS on 2/18/2018.
 */

public class WishAdapter extends CursorAdapter {
    public WishAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_wish , parent ,false );
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView titleWish = view.findViewById(R.id.wishTitleTV);
        TextView desWish = view.findViewById(R.id.wishDesTV);

        titleWish.setText(cursor.getString(cursor.getColumnIndex(WishContract.MyWish.TITLE)));
        desWish.setText(cursor.getString(cursor.getColumnIndex(WishContract.MyWish.DESCRIPTION)));
    }
}
