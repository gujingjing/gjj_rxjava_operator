package gjj.rxjava.operator.assist

import android.util.Log
import gjj.rxjava.operator.RxUtil
import rx.Notification
import rx.Observable
import rx.Observer
import rx.functions.Action1
import rx.functions.Func0
import rx.functions.Func1
import rx.functions.Func2
import rx.schedulers.Schedulers
import rx.schedulers.Timestamped
import java.util.concurrent.TimeUnit

/**
 * 作者：l on 2017/8/15 16:09
 * 邮箱：gujj512@163.com
 */
class RxAssist {
    companion object {
        /**
         * delay
         *      延时一段指定的时间，再发送observable数据
         *      * 整体发射时间延长
         * delay(observable)
         *      *: 发射的每一项都会延时
         *      *: 每一项数据都默认使用这个bservable的定时器
         *
         * delaySubscription(long,timeunit)
         *      *:　延时订阅原始的observable
         *      *:　整体的延时订阅
         *
         */
        fun delay() {
            Observable.just(1, 2, 3)
//                    .delay(1,TimeUnit.SECONDS)
//                    .delay { t ->
//                        Observable.create<Int> {
//                            subscriber->
//                            Thread.sleep(1000)
//                            subscriber.onNext(t)
//                            subscriber.onCompleted()
//                        }
//                    }
//                    .delaySubscription(1,TimeUnit.SECONDS)
                    .delaySubscription(object : Func0<Observable<Int>> {
                        override fun call(): Observable<Int> {
                            return Observable.create<Int> {
                                subscriber ->
                                Thread.sleep(1000)
                                subscriber.onNext(1)
                                subscriber.onCompleted()
                            }
                        }
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * doEatch
         *      *: 在observable的对于生命周期之前的时候调用对应代码
         *  doOnNext:在subscriber->onNext之前调用
         *  doOnError:在onError->之前调用
         *  doOnCompleted: 在onComplete->之前调用
         *  doOnTerminate: observable终止的时候调用(无论是否正常终止)
         */
        fun doEatch() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .doOnNext { Log.e(RxUtil.TAG,"doOnNext-onNext") }
//                    .doOnTerminate{Log.e(RxUtil.TAG,"doOnTerminate-doOnTerminate")}
//                    .finallyDo{Log.e(RxUtil.TAG,"finallyDo-finallyDo")}
                    .doOnEach(object : Observer<Int> {
                        override fun onNext(t: Int?) {
                            Log.e(RxUtil.TAG, "doEatch-onNext")
                        }

                        override fun onError(e: Throwable?) {
                            Log.e(RxUtil.TAG, "doEatch-onError")
                        }

                        override fun onCompleted() {
                            Log.e(RxUtil.TAG, "doEatch-onCompleted")
                        }
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * timeout
         *      *: 超过一段时间没有发送数据，抛出异常
         *  timeout(long,timeunit,observable)
         *      *: 超过一段时间，执行默认的observable
         */
        fun timeout() {
            Observable.create<Int> {
                subscriber ->
                Thread.sleep(2000)
                subscriber.onNext(1)
                subscriber.onCompleted()
            }
//                    .timeout(1, TimeUnit.SECONDS)
                    .timeout(1, TimeUnit.SECONDS, Observable.create {
                        subscriber ->
                        subscriber.onNext(-1)
                        subscriber.onCompleted()
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * timestamp
         */
        fun timestamp(){
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .timestamp()
                    .subscribe(object :Action1<Timestamped<Int>>{
                        override fun call(t: Timestamped<Int>?) {
                            Log.e(RxUtil.TAG,""+t?.timestampMillis+"value-"+t?.value)
                        }

                    })
        }
        /**
         * BlokingObservable
         *      *: 使observable整体是阻塞的
         *
         */
        fun blokingObservable(){
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .flatMap { t -> Observable.just("flatmap-change==="+t) }
                    .flatMap({
                        t ->
                        var list = arrayListOf(1, 2, 3)
                        Observable.from(list).delay(100, TimeUnit.MILLISECONDS)
                    })
                    .toBlocking()
                    .subscribe({t->
                        Log.e(RxUtil.TAG,"blokingObservable-"+t)
                    })

        }
        /**
         * toList
         *      *: 让observable将多项数据组合成一个list数据返回
         *  toSortedList
         *      *:　可以排序，默认自然顺序
         */
        fun toList(){
            Observable.from(arrayOf(1, 3, 2, 5, 4, 8, 7, 6))
//                    .flatMap { t -> Observable.just("flatmap-change==="+t) }
                    .concatMap({
                        t ->
                        var list = arrayListOf(3, 1, 2)
                        Observable.from(list)
                    })
//                    .toList()
                    .toSortedList()
//                    .toSortedList(object :Func2<Int,Int,Int>{
//                        override fun call(t1: Int?, t2: Int?): Int {
//                            return t2?.toInt()?:0
//                        }
//                    })
                    .subscribe(object :Action1<List<Int>>{
                        override fun call(t: List<Int>?) {
                            t?.forEach {
                                Log.e(RxUtil.TAG,"toList-"+t)
                            }
                        }

                    }, Action1<Throwable> {
                        error->
                        Log.e(RxUtil.TAG,"toList-"+error.toString())
                    })
        }
        /**
         * toMap
         *      *: 将原始所有数据合并到map中，发送这个map
         *
         */
        fun toMap(){
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .toMap(object :Func1<Int,Int>{
                        override fun call(t: Int): Int {
                            return 10*t
                        }
                    },object :Func1<Int,String>{
                        override fun call(t: Int?): String {
                            return ""+t
                        }

                    })
                    .subscribe(object :Action1<Map<Int,String>>{
                        override fun call(map: Map<Int,String>?) {
                            map?.forEach {
                                (key,value)->
                                Log.e(RxUtil.TAG, "toMap-key-$key-value-$value")
                            }
                        }

                    }, Action1<Throwable> {
                        error->
                        Log.e(RxUtil.TAG,"toMap-"+error.toString())
                    })
        }
    }
}
