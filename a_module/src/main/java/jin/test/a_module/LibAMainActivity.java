package jin.test.a_module;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ttyy.commonanno.Injectors;
import com.ttyy.commonanno.anno.BindLayout2;
import com.ttyy.commonanno.anno.OnClick2;
import com.ttyy.commonanno.anno.route.BindRoute;

import test.amodule.R;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: LibAMainActivity
 */

@BindRoute("jin.test.lib_a_main")
@BindLayout2("activity_lib_a_main")
public class LibAMainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injectors.get().setRClass(R.class).injectActivity(this);

        Log.e("Test", "LibAMainActivity");

    }

    @OnClick2("tv_lib_a")
    void onClick(){
        Injectors.get().buildRouter("jin.test.lib_b_main").navigate(this);
    }

}
