package pro.phoenix.workshop;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import pro.phoenix.workshop.data.WishAdapter;
import pro.phoenix.workshop.data.WishContract;

public class MainActivity extends AppCompatActivity {

    private Cursor mCursor;
    private ListView wishList;
    private WishAdapter wishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishList = findViewById(R.id.wishList);
        mCursor = getContentResolver().query(WishContract.MyWish.CONTENT_URI ,WishContract.MyWish.PROJECTION_ALL,null,null,WishContract.MyWish.SORT_ORDER_BY  );
        wishAdapter = new WishAdapter(this , mCursor);
        wishList.setAdapter(wishAdapter);

    }

    public void addNewWish(View view) {
        Intent i = new Intent(MainActivity.this , AddNewActivity.class);
        startActivity(i);
    }
}
