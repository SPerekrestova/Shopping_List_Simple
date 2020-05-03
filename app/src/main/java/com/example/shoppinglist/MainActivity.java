package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;
    private int textViewCount = 10;
    private TextView[] textViewArray = new TextView[textViewCount];
    private EditText mStoreName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewArray[0] = findViewById(R.id.textView_1);
        textViewArray[1] = findViewById(R.id.textView_2);
        textViewArray[2] = findViewById(R.id.textView_3);
        textViewArray[3] = findViewById(R.id.textView_4);
        textViewArray[4] = findViewById(R.id.textView_5);
        textViewArray[5] = findViewById(R.id.textView_6);
        textViewArray[6] = findViewById(R.id.textView_7);
        textViewArray[7] = findViewById(R.id.textView_8);
        textViewArray[8] = findViewById(R.id.textView_9);
        textViewArray[9] = findViewById(R.id.textView_10);
        mStoreName = findViewById(R.id.store_location);
        // Restore the saved state.
        // See onSaveInstanceState() for what gets saved.
        if (savedInstanceState != null) {
            for (int i = 0; i < textViewCount; i++) {
                boolean isVisible =
                        savedInstanceState.getBoolean("reply_visible" + i);
                // Show both the header and the message views. If isVisible is
                // false or missing from the bundle, use the default layout.
                if (isVisible) {
                    textViewArray[i].setText(savedInstanceState
                            .getString("reply_text" + i));
                    textViewArray[i].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                for (int i = 0; i < textViewCount; i++) {
                    if ((textViewArray[i].getText().length()) == 0) {
                        textViewArray[i].setText(reply);
                        textViewArray[i].setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // If the heading is visible, message needs to be saved.
        // Otherwise we're still using default layout.
        for (int i = 0; i < textViewCount; i++) {
            if (textViewArray[i].getVisibility() == View.VISIBLE) {
                outState.putBoolean("reply_visible" + i, true);
                outState.putString("reply_text" + i, textViewArray[i].getText().toString());
            }
        }
    }

    public void findStore(View view) {
        String store = mStoreName.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + store);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}
