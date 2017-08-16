package gjj.rxjava.operator.create

import gjj.rxjava.operator.RxUtil
import rx.Observable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * 作者：l on 2017/8/14 15:43
 * 邮箱：gujj512@163.com
 */
class RxCreate {
    companion object {
        private var TAG = "RxCreate"
        /**
         * 创建一个observable
         * 注意: 在create中函数调用发送消息时候，检查，是否有观察者，没有不发送消息，减少资源消耗
         * observer.isUnSubscribed()
         */
        fun create() {
            Observable.create(Observable.OnSubscribe<String> { t ->
                if (!(t?.isUnsubscribed ?: true)) {
                    try {
                        for (i in 1..10) {
                            t?.onNext("" + i)
                        }
                        t?.onCompleted()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        t?.onError(e)
                    }

                }
            })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        fun defer(){
            Observable.defer { Observable.just("") }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * empty:创建一个不发送数据，但是正常终止的observable
         * never:创建一个不发送数据，也不终止的observable
         * throw:创建一个不发送数据，以error终止的observable
         * 这三种一一种为例子
         */
        fun empty(){
            Observable.empty<String>()
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * from 将数组或者对象，生成新的observable发送出去
         */
        fun from(){
            Observable.from(arrayOf(1,2,3,4,5))
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())

        }
        /**
         * Interval:
         *      固定时间发送数据
         *      初始值为0
         *      无线递增发送数据
         */
        fun interval(){
            Observable.interval(1,TimeUnit.SECONDS)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * just
         *      发送单个对象
         *      参数可选，1-10
         *      按照参数列表发送数据
         */
        fun just(){
            Observable.just(1,2,3,4,5,6,7,8,9,10)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * range: 发送整数范围内的有序序列
         *      第一个参数:整数的起始数
         *      第二个参数:一共要发送几个数据
         */
        fun range(){
            Observable.range(3,3)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * repeat
         *      重复发送源数据源,重复次数可设置
         *      repeat() 表示无限循环
         *      repeat(5):表示循环5次
         *
         * 其他循环操作符
         *      满足条件循环
         *      repeatWhen()
         *      doWhile()
         *      whileDo()
         */
        fun repeat(){
            Observable.just(1)
                    .repeat(5)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
        /**
         * timer
         *      在一定延时后发送一条数据
         */
        fun timer(){
            Observable.timer(1,TimeUnit.SECONDS)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
    }
}
