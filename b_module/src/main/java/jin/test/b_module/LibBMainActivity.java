package jin.test.b_module;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ttyy.commonanno.JinInjector;
import com.ttyy.commonanno.anno.BindLayout2;
import com.ttyy.commonanno.anno.BindView;
import com.ttyy.commonanno.anno.OnClick;
import com.ttyy.commonanno.anno.OnClick2;
import com.ttyy.commonanno.anno.route.BindRoute;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: LibBMainActivity
 */

@BindRoute("jin.test.lib_b_main")
@BindLayout2("activity_lib_b_main")
public class LibBMainActivity extends Activity {

    @BindView
    TextView tv_b_jump;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JinInjector.get().setRClass(R.class).injectActivity(this);

        Log.e("Test", "LibBMainActivity");
    }

    @OnClick2("tv_b_jump")
    void onJumpClick(){
        JinInjector.get().buildRouter("jin.test.ui.main").navigate(this);
    }
}
