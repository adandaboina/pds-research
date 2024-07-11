package com.mastercard.openbanking.connect.demo;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mastercard.openbanking.connect.Connect;
import com.mastercard.openbanking.connect.EventHandler;
import com.mastercard.openbanking.connect.connectPDS.ConnectAtomic;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText mEditConnectUrl;

    private RadioGroup radioGroup;
    private TextView textViewTitle;
    private EditText editConnectUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add click listener for EventListener
        Button mStartButtonEventHandler = findViewById(R.id.startButtonEventHandler);

        mStartButtonEventHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(new ConsoleEventHandler());
            }
        });

        this.mEditConnectUrl = findViewById(R.id.editConnectUrl);

        radioGroup = findViewById(R.id.radioGroupConnectFlow);
        textViewTitle = findViewById(R.id.textView);
        editConnectUrl = findViewById(R.id.editConnectUrl);

        textViewTitle.setText(R.string.demo_app_title_connect_pds);
        editConnectUrl.setHint(R.string.demo_app_edittext_hint_connect_pds);

        setupRadioButtonEventHandlers();
    }

    private void launchActivity(EventHandler eventHandler) {
        String url = getEditConnectUrl();

        if(url.length() > 0) {
            // Null out text so we can repeat with new link after Connect Activity closes.
            mEditConnectUrl.setText("");

            Log.i(TAG, ">>> Launching Connect activity");

//            Connect.start(this, url, eventHandler);
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonRegularConnectFlow) {
                Connect.start(this, url,"https://acmelending.net", eventHandler);
//                Connect.start(this, url, eventHandler);
            }
            else {
                ConnectAtomic connectAtomic = new ConnectAtomic(this, url,new ConsoleConnectAtomicEventHandler());
                connectAtomic.startAtomicSDK();
            }

        }
    }

    private String getEditConnectUrl() {
        String rawUrl = this.mEditConnectUrl.getText().toString();
        return rawUrl.replace("localhost:", "10.0.2.2:");
    }
    private void setupRadioButtonEventHandlers() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                handleRadioButtonSelection(checkedId);
            }
        });
    }

    private void handleRadioButtonSelection(int checkedId) {

        RadioButton radioButton = findViewById(checkedId);
        if (radioButton == null) return;

        if (checkedId == R.id.radioButtonRegularConnectFlow) {

            textViewTitle.setText(R.string.demo_app_title_connect);
            editConnectUrl.setHint(R.string.demo_app_edittext_hint_connect);

        } else if (checkedId == R.id.radioButtonConnectPDSFlow) {

            textViewTitle.setText(R.string.demo_app_title_connect_pds);
            editConnectUrl.setHint(R.string.demo_app_edittext_hint_connect_pds);
        }
    }

}
