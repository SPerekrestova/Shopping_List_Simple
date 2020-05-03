package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    public static final String EXTRA_REPLY =
            "com.example.android.shoppinglist.extra.REPLY";
    private Button itemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void getProductItem(View view) {
        int item = view.getId();
        itemBtn = findViewById(item);
        String productName = (String) itemBtn.getText();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, productName);
        setResult(RESULT_OK, replyIntent);
        Log.d(LOG_TAG, "End SecondActivity");
        finish();
    }
}
