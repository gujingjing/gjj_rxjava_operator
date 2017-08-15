package gjj.rxjava.operator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gjj.rxjava.operator.conversion.RxConversion
import gjj.rxjava.operator.creaate.RxCreate
import gjj.rxjava.operator.filter.RxFilter
import gjj.rxjava.operator.math.RxMath

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //rx创建
//        RxCreate.create()
//        RxCreate.defer()
//        RxCreate.empty()
        //from
//        RxCreate.from()
//        RxCreate.interval()
//        RxCreate.just()
//        RxCreate.range()
//        RxCreate.repeat()
//        RxCreate.timer()

        //conversion
//        RxConversion.buffer()
//        RxConversion.flatMap()
//        RxConversion.concatMap()
//        RxConversion.switchMap()
//        RxConversion.map()
//        RxConversion.cast()

        //math
//        RxMath.scan()
//        RxMath.count()
//        RxMath.concat()
//        RxMath.reduce()
//        RxMath.collect()

        //filter
//        RxFilter.debounce()
//        RxFilter.distinct()
//        RxFilter.distinctUntilChange()
//        RxFilter.element()
//        RxFilter.filter()
//        RxFilter.ofType()
//        RxFilter.first()
//        RxFilter.single()
//        RxFilter.ignoreElements()
//        RxFilter.sample()
        RxFilter.skip()
    }
}
