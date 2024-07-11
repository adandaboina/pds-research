package com.mastercard.openbanking.connect.connectPDS;

import org.json.JSONObject;

public interface ConnectAtomicEventListener {

    void onLoad();
    void onClose(JSONObject closeEvent);
    void onFinish(JSONObject finishEvent);
    void onInteraction(JSONObject data);
}
