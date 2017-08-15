package gjj.rxjava.operator.filter

import android.os.CountDownTimer
import android.util.Log
import gjj.rxjava.operator.RxUtil
import rx.Observable
import rx.Subscriber
import rx.functions.Action1
import rx.functions.Func1
import java.util.concurrent.TimeUnit

/**
 * 作者：l on 2017/8/15 11:55
 * 邮箱：gujj512@163.com
 */
class RxFilter {
    companion object {
        /**
         * debounce
         *      *: 在过了一段时间内还没有发送数据时，发送数据
         *      *: 可以过滤掉发送数据频率过快的数据
         *   注意
         *      *: 中间频率过快的数据的oncomplete()方法还是会调用
         *      *:oncomplete()不会被过滤掉
         */
        fun debounce() {

            Observable.create<Int> {
                subscriber ->
                object : CountDownTimer(4000, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        //每0.5秒钟发送数据
                        subscriber.onNext(1)
                    }

                    override fun onFinish() {

                    }

                }.start()
            }
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())

        }

        /**
         * distinct
         *      *: 抑制过滤，过滤重复数据,只允许还没有发送过的数据通过
         *    distinct()
         *      *: 单纯的比较数值是否相同
         *     distinct(Func1))
         *      *: 生成的key不能重复
         *
         */
        fun distinct() {
            Observable.just("112", "113", "211", "122", "234", "321", "331", "211", "43")
//                    .distinct()
                    .distinct { t ->
                        //第一字母不能重复
                        t.toCharArray()[0].toString()
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * distinctUntilChange
         *      distinctUntilChanged()
         *          * 比较数值和前一个数值是否相同
         *      distinctUntilChanged(Func1)
         *          * 生成key，值比较key是否相同
         */
        fun distinctUntilChange() {
            Observable.just(1, 2, 1, 3, 1, 1, 3, 4, 4, 3)
//                    .distinctUntilChanged()
                    .distinctUntilChanged({
                        t ->
                        //第一字母不能重复
                        t.toString().toCharArray()[0].toString()
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * element
         *      *:指定发射那一条数据
         *      1.初始第一个index位置为:0
         *      2.index为负值或者大于源数据个数，抛出异常
         *
         *   elementAtOrDefault(index,defaultValue)
         *      1.第二个参数为数据源的默认值
         */
        fun element() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .elementAt(3)
                    .elementAtOrDefault(9, -1)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * filter
         *      *: 过滤不符合要求的
         *      1.返回true表示通过，可以发送消息
         */
        fun filter() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .filter {
                        t ->
                        t > 5
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * ofType
         *      *:只返回对应类型的参数
         */
        fun ofType() {
            Observable.from(arrayOf(1, "2", "3", 4, 5, 6, "7", 8))
                    .ofType(String.javaClass)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * first
         *      *: 只发射第一项或者满足条件的第一项数据
         * first()
         *      *: 默认发送的是第一项
         *      *: 没有满足的抛出异常
         * first(func1)
         *      *: 返回满足条件的第一项
         *      *: 没有满足的抛出异常
         *
         * firstOrDefault(defultValue)
         *      * :没有满足的返回默认值
         * firstOrDefault(defultValue,func1)
         *      * :没有满足的返回默认值
         *
         * takeFirst(func1)
         *      *: 和first类似
         *      *: 没有满足的数值，返回空的observable
         *          *onnext()不执行
         *          *onComplete()执行
         */
        fun first() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .first()
//                    .first {
//                        t ->
//                            t > 4
//                    }

//                    .firstOrDefault(-1)
//                    .firstOrDefault(-1, {
//                        t ->
//                            t > 4
//                    })

                    .takeFirst {
                        t ->
                        t > 4
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * single
         *      *: 在数据结束之前有且只能发送一条数据，否则抛出异常
         *      *: 与first类似
         *      *: 如果在源数据完成之前，不是恰巧发送一条数据，抛出异常
         *
         * single(func1)
         *      *: 发射满足条件的单个值，如果有多个满足条件，抛出异常
         *
         * singleOrDefault(defaultValue)
         *      *: 没有满足条件，返回默认值
         *      *: 有多个满足条件的值，抛出异常
         *
         * singleOrDefault(defaultValue,func1)
         *      *: 没有满足条件，返回默认值
         *      *: 有多个满足条件的值，抛出异常
         */
        fun single() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .single()
//                    .single {
//                        t ->
//                            t == 8
//                    }

                    .singleOrDefault(-1)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * ignoreElements
         *      *: 终止源数据所有数据的发送，只允许onComplete()、或者onError()
         *
         *  使用情况:
         *      *: 不关心发送数据的处理，只关心数据结束的结果
         */
        fun ignoreElements(){
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .ignoreElements()
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * sample
         *    sample(long,timeUnit)
         *      *: 定时间的采样，默认发送采样的最后一条数据
         *    throttleLast()
         *      *: 采样的最后一条数据
         *    throttleFirst()
         *      *: 采样的第一条数据
         */
        fun sample(){

            var observable=Observable.create<String> {
                subscriber->
                //每一秒钟发射一次
                object :CountDownTimer(3000,1000){
                    override fun onFinish() {
                        subscriber.onCompleted()
                    }
                    override fun onTick(millisUntilFinished: Long) {
                        subscriber.onNext(""+millisUntilFinished)
                    }

                }
            }
            Observable.create<Int> {
                subscriber->
                for (i in 1..40){
                    subscriber.onNext(i)
                    Thread.sleep(200)
                }
                subscriber.onCompleted()
            }
//                    .sample(1000,TimeUnit.MILLISECONDS)
//                    .throttleLast(1000,TimeUnit.MILLISECONDS)
                    .throttleFirst(1000,TimeUnit.MILLISECONDS)
//                    .sample(observable)
//                    .sample(Observable.create(object :Observable.OnSubscribe<String>{
//                        override fun call(t: Subscriber<in String>?) {
//                            observable.subscribe({
//                                t?.onNext("-----")
//                            },{
//                                error->
//                                Log.e(RxUtil.TAG,error.toString())
//                            },{
//                                Log.e(RxUtil.TAG,"sample-complete")
//                            })
//                        }
//                    }))

                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * skip
         *      skip(count)
         *      *: 跳跃钱几个参数,保留后面所有值
         *      skip(long,timeunit)
         *      *: 跳过前几秒数据
         */
        fun skip(){
            Observable.create<Int> {
                subscriber->
                for (i in 1..40){
                    subscriber.onNext(i)
                    Thread.sleep(200)
                }
                subscriber.onCompleted()
            }
//                    .skip(5)
                    .skip(2,TimeUnit.SECONDS)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

    }
}
