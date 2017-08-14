package gjj.rxjava.operator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gjj.rxjava.operator.creaate.RxCreate

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
        RxCreate.timer()

    }
}
