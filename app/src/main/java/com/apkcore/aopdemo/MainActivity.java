package com.apkcore.aopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.apkcore.aopdemo.hook.HookTrace;

/**
 * 参考https://juejin.im/post/5a322cd06fb9a0450a675e2d#heading-0
 * https://www.jianshu.com/p/2779e3bb1f14
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AOP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @HookTrace(beforeMethod = "method1",afterMethod = "method2")
    public void doClick(View view){
        Log.d(TAG,"doClick()");
    }

    private void method1() {
        Log.d(TAG,"method1() is called before initData()");
    }

    private void method2() {
        Log.d(TAG,"method2() is called after initData()");
    }
    //    @BehaviorTrace(value = "测试", type = 1)
//    public void doClick(View view) {
//        SystemClock.sleep(3000);
//        Log.d(TAG, "doClick: ");
//    }

//    @SafeTrace
//    public void doClick(View view) {
//        String s = null;
//        Log.d(TAG, "doClick: " + s.length());
//    }

//    @AsyncTrace
//    public void doClick(View view) {
//        Log.d(TAG, Thread.currentThread().getName());
//    }
}
