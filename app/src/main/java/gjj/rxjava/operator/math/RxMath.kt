package gjj.rxjava.operator.math

import android.drm.DrmStore
import gjj.rxjava.operator.RxUtil
import rx.Observable
import rx.functions.Action
import rx.functions.Action2
import rx.functions.Func0

/**
 * 作者：l on 2017/8/15 11:16
 * 邮箱：gujj512@163.com
 */
class RxMath {
    companion object {
        /**
         * scan
         *      *: 数据的每一项都应用一个函数,然后连续发送结果
         *      1.每一项数据结果都会传递到下一项数据的函数中
         *
         */
        fun scan() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .scan(object : Function2<Int, Int, Int> {
                        override fun invoke(p1: Int, p2: Int): Int {
                            return p1 + p2
                        }
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * count
         *      *: 将原来数据源转化为值发送一个值的observable,发送的数据为数据源发送消息的个数
         *      统计observable发送了多少数据
         *      1.只会发送统计的个数
         */
        fun count() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .count()
                    .countLong()
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * concat
         *      *: 合并多个observable
         *      1.有序的，只有当第一个observable结束才开始监听第二个
         */
        fun concat() {
            Observable.concat(Observable.just(2), Observable.just(1))
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * reduce
         *      *: 和scan 不同的是，scan每次应用函数之后，都会发送数据,reduce只会发送最后的结果
         *      *: 按照顺序对发送的每一个数据应用,并发送最终的值
         *      1.最后只返回一个最终结果的数据
         *
         */
        fun reduce() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .reduce(object : Function2<Int, Int, Int> {
                        override fun invoke(p1: Int, p2: Int): Int {
                            return p1 + p2
                        }
                    })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * collect
         */
//        fun collect() {
//            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
//                    .collect({
//                        1
//                    }, { t1, t2 ->
//                        t1 + t2
//                    })
//                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
//        }
    }
}
