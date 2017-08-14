package gjj.rxjava.operator;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * 作者：l on 2017/8/14 18:31
 * 邮箱：gujj512@163.com
 */
public class Test {

    public void bufferTest(){

        Observable.just(1,2,3,4,5,6,7)
                .buffer(3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        for (int i=0;i<integers.size();i++){
                            Log.e("",""+i);
                        }
                    }
                });

        Integer[]arrays={1,2,3};
        Observable.from(arrays)
//                .buffer(3)
                .buffer(Observable.just(1))
                .subscribe(integers -> {
                    for (int i=0;i<integers.size();i++){
                        Log.e("",""+i);
                    }
                }, throwable -> {});
    }
}
