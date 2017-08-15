package gjj.rxjava.operator

import android.util.Log
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * 作者：l on 2017/8/14 18:00
 * 邮箱：gujj512@163.com
 */
class RxUtil {

    companion object {

        var TAG = "RxUtil-"

        /**
         * defultAction1
         */
        fun getDefultAction1(): Action1<Any> {

            return Action1 {

                t: Any? ->
                Log.e(TAG, t?.toString()?:"")

                //list
//                if (null != t as? List<*>) {//list
//                    var list=t as? List<Any>
//                    if(list!=null){
//                        for ((index, value) in list.withIndex()) {
//                            Log.e(TAG, value.toString())
//                        }
//                    }
//                } else if (null != t as? Array<*>) {//array
//                    var array=t as? Array<*>
//                    if(array!=null){
//                        for ((index, value) in array.withIndex()) {
//                            Log.e(TAG, value.toString())
//                        }
//                    }
//                }else{
//                    Log.e(TAG, t.toString())
//                }

            }
        }

        /**
         * defultErrorAction1
         */
        fun getDefultErrorAction1(): Action1<Throwable> {

            return Action1 {
                error ->
                Log.e(TAG, error.toString())
            }
        }

        /**
         * defultComplete
         */
        fun getDefultCompleteAction0(): Action0 {

            return Action0 {
                Log.e(TAG, "complete")
            }
        }
        /**
         * timerObservable
         */
        fun getTimerObservable():Observable<Int>{
            return Observable.create<Int> {
                subscriber ->
                for (i in 1..5) {
                    subscriber.onNext(i)
                    Thread.sleep(200)
                }

                subscriber.onCompleted()
            }
                    .subscribeOn(Schedulers.io())
        }
    }
}