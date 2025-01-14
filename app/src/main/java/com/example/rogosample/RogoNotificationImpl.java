package com.example.rogosample;

import android.util.Log;


import rogo.iot.module.rogocore.sdk.handler.NotificationHandler;

public class RogoNotificationImpl implements NotificationHandler {
    @Override
    public void subcribeTopic(String s) {

    }

    @Override
    public void unsubcribeTopic(String s) {

    }
//    private final String TAG = "RogoNotificationImpl";
//
//    @Override
//    public void subcribeTopic(String topic) {
//        Log.d(TAG, "subcribeTopic: " + topic);
//        if (topic != null) {
//            FirebaseMessaging.getInstance().subscribeToTopic(topic)
//                    .addOnCompleteListener(task -> {
//
//
//                    });
//        }
//    }
//
//    @Override
//    public void unsubcribeTopic(String topic) {
//
//    }
}
