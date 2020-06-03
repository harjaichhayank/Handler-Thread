package com.example.HandlerThread;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Use Simple Worker object to see the difference in the management of Handler Threads
    private Worker Worker;
    private TextView textView;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.obj.toString());
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.TextView);
        Worker = new Worker();

        Worker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "Task 1 is Completed";
                handler.sendMessage(message);
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "Task 2 is Completed";
                handler.sendMessage(message);
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "Task 3 is Completed";
                handler.sendMessage(message);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Worker.quit();
    }
}
