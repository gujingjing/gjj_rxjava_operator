package gjj.rxjava.operator.combine

import gjj.rxjava.operator.RxUtil
import rx.Observable
import rx.functions.Func2
import rx.schedulers.Schedulers

/**
 * 作者：l on 2017/8/15 15:37
 * 邮箱：gujj512@163.com
 */
class RxCombine {
    companion object {
        /**
         * merge
         *      *: 合并多个observable的发射产物
         *      *: 无序的，发送顺序不确定
         * concat
         *      *合并observable
         *      *有序的
         */
        fun merge() {
            var observable1 = RxUtil.getTimerObservable()
            var observable2 = RxUtil.getTimerObservable()

            Observable.merge(observable1, observable2)
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }

        /**
         * zip
         *      *: 将多个observables 得到最终结果,和并成一个observable发送
         *      *: 返回个数以最少的observable为主
         *
         */
        fun zip() {
            var observable1 = Observable.just(1, 2, 3, 4)
            var observable2 = Observable.just("5", "6", "7", "8", "9", "10")

            Observable.zip(observable1, observable2, Func2<Int, String, Int> {
                t1, t2 ->
                    t1+t2.toInt()
            })
                    .subscribe(RxUtil.getDefultAction1(), RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
    }
}
