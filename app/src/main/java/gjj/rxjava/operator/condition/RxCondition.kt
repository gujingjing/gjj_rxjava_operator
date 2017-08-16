package gjj.rxjava.operator.condition

import gjj.rxjava.operator.RxUtil
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * 作者：l on 2017/8/16 14:37
 * 邮箱：gujj512@163.com
 */
class RxCondition {
    companion object {
        /**
         * all
         *      *:判断源数据中发送的所有数据是否都满足条件
         *  返回一个booble值
         */
        fun all() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .all {
                        t ->
                        t > 0
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * contains
         *      *: 源数据中是否包含某一个值
         *  返回一个booble值
         */
        fun contains() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .contains(9)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * exists
         *      判断源数据是否有某一项满足条件
         */
        fun exists() {
            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .exists {
                        t ->
                        t > 5
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * skipUntil
         *      *: 前面的数据一直跳过，不发送数据，知道observable2发送数据过来，才开始发送原来observable1中的数据
         */
        fun skipUntil() {
            var observable1 = RxUtil.getTimerObservable()
            var observable2 = RxUtil.getTimerObservable().delay(1, TimeUnit.SECONDS)

            observable1
                    .skipUntil(observable2)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * skipWhile
         *      *: 一直跳过源数据，知道，某一个条件不满足
         */
        fun skipWhile() {

            Observable.from(arrayOf(1, 2, 3, 4, 5, 6, 7, 8))
                    .skipWhile {
                        t ->
                        t < 6
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * takeUntil
         *      *: 一直在发送源数据，知道某个条件，停止发送
         *      *: 一直在监听发送源数据，知道第二个observable发送数据，停止
         *
         * takeWhile
         *       *: 一直在发送源数据，知道某个条件不满足，停止发送
         */
        fun takeUtil() {
            var observable1 = RxUtil.getTimerObservable()
            var observable2 = RxUtil.getTimerObservable().delay(1, TimeUnit.SECONDS)

            observable1
//                    .takeUntil {
//                        t->
//                            t>5
//                    }
//                    .takeUntil(observable2)
                    .takeWhile {
                        t ->
                        t > 5
                    }
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

    }
}
