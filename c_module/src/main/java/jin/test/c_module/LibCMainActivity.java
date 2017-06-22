package jin.test.c_module;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ttyy.commonanno.JinInjector;
import com.ttyy.commonanno.anno.BindLayout2;
import com.ttyy.commonanno.anno.route.BindRoute;


/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: LibAMainActivity
 */

@BindRoute("jin.test.lib_c_main")
@BindLayout2("activity_lib_c_main")
public class LibCMainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JinInjector.get().setRClass(R.class).injectActivity(this);

        Log.e("Test", "LibCMainActivity");

    }

}
