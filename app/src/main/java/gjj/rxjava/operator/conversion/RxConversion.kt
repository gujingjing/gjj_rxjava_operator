package gjj.rxjava.operator.conversion

import android.util.Log
import gjj.rxjava.operator.RxUtil
import rx.Observable
import rx.functions.Action1

/**
 * 作者：l on 2017/8/14 17:59
 * 邮箱：gujj512@163.com
 */
class RxConversion{

    companion object {
        var TAG="RxConversion"

        /**
         * buffer
         *      buffer(3)  三个为一体，打包发送
         *      buffer(3,4)
         *              第一个参数为count:几个数据作为一个打包
         *              第二个参数为跳跃:跳跃第一个值
         */
        fun buffer(){
            Observable.just(1,2,3,4,5,6,7,8)
//                    .buffer(3)
                    .buffer(3,1)
                    .subscribe(Action1<List<Int>> {
                        integers->
                        integers.forEach {
                            integer->
                            Log.e(TAG,""+integer)
                        }

                        Log.e(TAG,"------------------------------------")

                    }, RxUtil.getDefultErrorAction1(), RxUtil.getDefultCompleteAction0())
        }
    }
}
