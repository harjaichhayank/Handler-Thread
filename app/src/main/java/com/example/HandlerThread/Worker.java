package com.example.HandlerThread;

import android.os.Handler;
import android.os.HandlerThread;

class Worker extends HandlerThread {

    private Handler handler;
    private static final String TAG = "Worker";

    Worker() {
        super(TAG);
        start();
        handler = new Handler(getLooper());
    }

    Worker execute(Runnable task){
        handler.post(task);
        return this;
    }
}
