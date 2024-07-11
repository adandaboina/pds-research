package com.mastercard.openbanking.connect.connectPDS;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import financial.atomic.transact.Config;
import financial.atomic.transact.Transact;
import financial.atomic.transact.receiver.TransactBroadcastReceiver;
import kotlin.collections.CollectionsKt;

public class ConnectAtomic {

    private Context context;
    private String publicToken;
    private ConnectAtomicEventListener eventListener;

    public ConnectAtomic(Context context, String publicToken, ConnectAtomicEventListener eventListener) {
        this.context = context;
        this.publicToken = publicToken;
        this.eventListener = eventListener;
    }

    public void startAtomicSDK() {
        eventListener.onLoad();
        Config config = new Config(publicToken, CollectionsKt.listOf(new Config.Task(Config.Product.DEPOSIT, (Config.Distribution) null, (Config.Product) null, (List) null)));
        Transact.Companion.registerReceiver(context, new TransactBroadcastReceiver() {
            public void onClose(@NotNull JSONObject data) {
                eventListener.onClose(data);
            }

            public void onFinish(@NotNull JSONObject data) {
                eventListener.onFinish(data);
            }

            public void onInteraction(@NotNull JSONObject data) {
                eventListener.onInteraction(data);
            }
        });
        Transact.Companion.present(context, config);
    }

}