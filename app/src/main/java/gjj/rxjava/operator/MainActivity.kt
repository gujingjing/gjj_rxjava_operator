package gjj.rxjava.operator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gjj.rxjava.operator.creaate.RxCreate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxCreate.create()
    }
}
