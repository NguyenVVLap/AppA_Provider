package vn.com.lap.appaprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    static final String URI = "content://vn.com.lap.appaprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        EditText et_Id = findViewById(R.id.editText_Id);
        EditText et_Name = findViewById(R.id.editText_Name);
        EditText et_Unit = findViewById(R.id.editText_Unit);
        EditText et_MadeIn = findViewById(R.id.editText_MadeIn);
        GridView gv_product = findViewById(R.id.gridView_ProductList);

        Button bt_Save = findViewById(R.id.button_Save);
        bt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id", et_Id.getText().toString());
                values.put("name", et_Name.getText().toString());
                values.put("unit", et_Unit.getText().toString());
                values.put("madein", et_MadeIn.getText().toString());

                Uri product = Uri.parse(URI);
                Uri insert_uri = getContentResolver().insert(product, values);

                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            }
        });

        Button bt_Select = findViewById(R.id.button_Select);
        bt_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> string_list = new ArrayList<>();
                Uri product = Uri.parse(URI);
                Cursor cursor = getContentResolver().query(product, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        string_list.add(cursor.getInt(0) + "");
                        string_list.add(cursor.getString(1) );
                        string_list.add(cursor.getString(2) );
                        string_list.add(cursor.getString(3) );

                    } while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_list_item_1, string_list);
                    gv_product.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Không có kết quả", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}