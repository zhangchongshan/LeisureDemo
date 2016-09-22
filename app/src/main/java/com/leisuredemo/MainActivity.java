package com.leisuredemo;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener ,GestureDetector.OnGestureListener, View.OnTouchListener {

    private Button button1;
    private TextView textView;
    private RelativeLayout relativeLayout;
    GestureDetector gestureDetector;

    Handler handler=new Handler(){
        @Override
        public  void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    textView.setText("哈哈，我是handler更新UI");
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1= (Button) findViewById(R.id.button1);
        textView= (TextView) findViewById(R.id.textView);
        relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        gestureDetector=new GestureDetector(this,this);
        relativeLayout.setOnTouchListener(MainActivity.this);
        relativeLayout.setClickable(true);
        relativeLayout.setLongClickable(true);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.button1:

                Toast.makeText(this,"1111111111111111",Toast.LENGTH_LONG).show();


                Thread t = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Log.e("zzzzzzzzzzzzzzzzzzzz","开启线程");

                        try {
                            Message message=new Message();
                            message.what=1;
                            message.obj=true;
                            handler.sendMessage(message);
                            Thread.sleep(2000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                };
                t.start();
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return relativeLayout.onTouchEvent(event);
    }

}
