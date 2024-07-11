package com.mastercard.openbanking.connect.demo;

import android.util.Log;

import com.mastercard.openbanking.connect.EventHandler;
import com.mastercard.openbanking.connect.connectPDS.ConnectAtomicEventListener;

import org.json.JSONObject;

public class ConsoleConnectAtomicEventHandler implements ConnectAtomicEventListener {
    private static final String TAG = "ConsoleEventHandler";

    @Override
    public void onLoad() {
        Log.i(TAG, ">>> ConsoleEventHandler: Received Loaded event");
    }

    @Override
    public void onClose(JSONObject closeEvent) {
        Log.i(TAG, ">>> ConsoleEventHandler: Received Closed event");
    }

    @Override
    public void onFinish(JSONObject finishEvent) {
        Log.i(TAG, ">>> ConsoleEventHandler: Received Finish event\n>>>>>> " + finishEvent.toString());
    }

    @Override
    public void onInteraction(JSONObject data) {
        Log.i(TAG, ">>> ConsoleEventHandler: Received onInteraction event\n>>>>>> " + data.toString());
    }
}
