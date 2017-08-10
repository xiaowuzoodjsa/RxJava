package arr.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btu_queding;
    private RelativeLayout activity_main;
    private Observer<String> observer;
    private Observable<String> observable;
    private Subscriber<String> subscriber;
    private Action1<String> action;
    private Action1<Throwable> action1;
    private Action0 action0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //观察者
        observer = new Observer<String>() {

             @Override
             public void onCompleted() {
                 Log.i(TAG, "Completed");
             }

             @Override
             public void onError(Throwable e) {
                 Log.i(TAG, "Error");
             }

             @Override
             public void onNext(String s) {
                 Log.i(TAG, s);
             }
         };

        //Observer扩展
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Error");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };

        //被观察者

        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("你好");
                subscriber.onNext("你好!");
                subscriber.onNext("你好!!");
                subscriber.onCompleted();
            }
        });

        action = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, s);
            }
        };
        action1 = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.i(TAG, "Error");
            }
        };
        action0 = new Action0() {
            @Override
            public void call() {
                Log.i(TAG, "Completed");
            }
        };


    }

    private void initView() {
        btu_queding = (Button) findViewById(R.id.btu_queding);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);

        btu_queding.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscriber==null&&subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btu_queding:
               // observable.subscribe(observer);
                //observable.subscribe(subscriber);
                //observable.subscribe(action);
                break;
        }
    }
}
