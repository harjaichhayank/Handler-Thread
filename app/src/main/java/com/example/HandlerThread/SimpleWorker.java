package com.example.HandlerThread;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SimpleWorker extends Thread {

    private static final String TAG = "SimpleWorker";
    private AtomicBoolean alive = new AtomicBoolean(true);
    private ConcurrentLinkedDeque<Runnable> taskQueue = new ConcurrentLinkedDeque<>();

    public SimpleWorker() {
        start();
    }

    @Override
    public void run() {
        while(alive.get()){
            Runnable task = taskQueue.poll();
            if (task != null){
                task.run();
            }
        }
        Log.i(TAG, "Simple Worker Terminated");
    }

    public SimpleWorker execute(Runnable task){
        taskQueue.add(task);
        return this;
    }

    public void quit(){
        alive.set(false);
    }
}
