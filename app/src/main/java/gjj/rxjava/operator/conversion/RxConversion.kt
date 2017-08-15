package gjj.rxjava.operator.conversion

import android.util.Log
import gjj.rxjava.operator.RxUtil
import rx.Observable
import rx.functions.Action1
import rx.functions.Func1
import java.util.concurrent.TimeUnit
import java.util.function.Function

/**
 * 作者：l on 2017/8/14 17:59
 * 邮箱：gujj512@163.com
 */
class RxConversion {

    companion object {
        var TAG = "RxConversion"

        /**
         * buffer
         *      buffer(3)  三个为一体，打包发送
         *      buffer(3,4)
         *              第一个参数为count:几个数据作为一个打包
         *              第二个参数为跳跃:跳跃第一个值
         */
        fun buffer() {
            Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
//                    .buffer(3)
                    .buffer(3, 4)
                    .subscribe(Action1<List<Int>> {
                        integers ->
                        integers.forEach {
                            integer ->
                            Log.e(TAG, "" + integer)
                        }

                        Log.e(TAG, "------------------------------------")

                    }, RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * flatMap
         *      * 合并所有产生的observables，产生的自己数据列不能保证顺序
         *      1.通过一个指定的函数将源数据源变化为其他数据
         *      2.新建一个observable发送变化后的数据源
         *      3.merge合并所有产生的observable->放入新的observable一起发送出去
         *      4.发送的顺序是无序的
         * flatMap(function1,maxCount)
         *      1.第二个参数:从源数据最大同时订阅个数，当达到最大限制，会等待其中一个终止在订阅
         */
        fun flatMap() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .flatMap { t -> Observable.just("flatmap-change==="+t) }
                    .flatMap({
                        t ->
                        var list = arrayListOf(1, 2, 3)
                        Observable.from(list).delay(100, TimeUnit.MILLISECONDS)
                    }, 3)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * concatMap
         *      * 按照次序连接生成的observables,然后产生自己的数据列
         *      1.和flatMap操作符类似
         *      2.不同的是，严格按照源数据的顺序发送数据源
         */
        fun concatMap() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .flatMap { t -> Observable.just("flatmap-change==="+t) }
                    .concatMap({
                        t ->
                        var list = arrayListOf(t, t, t)
                        Observable.from(list).delay(100, TimeUnit.MILLISECONDS)
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * switchMap
         *      只监听当前的数据
         */
        fun switchMap() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .switchMap({
                        t ->
                        var list = arrayListOf(t, t, t)
                        Observable.from(list).delay(100, TimeUnit.MILLISECONDS)
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * map
         *      1.根据你指定的函数将源数据源转化为另一种类型
         */
        fun map() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .map {
                        number ->
                        "" + number
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * cast
         *      将源数据源强行转化为一种类型
         */
        fun cast() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .cast(String.javaClass)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

    }
}
