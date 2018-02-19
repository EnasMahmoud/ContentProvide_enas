package pro.phoenix.workshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pro.phoenix.workshop.data.DbHelper;
import pro.phoenix.workshop.data.DbSchema;
import pro.phoenix.workshop.data.WishContract;

public class AddNewActivity extends AppCompatActivity {

    EditText titleET , desET ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        titleET= findViewById(R.id.titleEditText);
        desET=findViewById(R.id.descriptionEditText);
    }

    public void addWish(View view) {

        //Toast.makeText(this , "you click button" ,Toast.LENGTH_LONG ).show();

        if (titleET.getText().toString().equals("") || desET.getText().toString().equals(""))
        {
            Toast.makeText(this , "Complete Your Data" ,Toast.LENGTH_LONG ).show();
        }

        else
        {
            Toast.makeText(this , "Add success" ,Toast.LENGTH_LONG ).show();

            DbHelper myHelper = new DbHelper(this);
            SQLiteDatabase sqlLiteDB = myHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(WishContract.MyWish.TITLE , titleET.getText().toString());
            values.put(WishContract.MyWish.DESCRIPTION, desET.getText().toString());
            sqlLiteDB.insert(DbSchema.TABLE_WISH,null,values);

            Intent i = new Intent(AddNewActivity.this , MainActivity.class);
            startActivity(i);

        }





    }
}
