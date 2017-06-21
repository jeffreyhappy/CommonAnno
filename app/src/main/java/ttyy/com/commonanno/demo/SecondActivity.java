package ttyy.com.commonanno.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ttyy.commonanno.JinInjector;
import com.ttyy.commonanno.anno.BindLayout2;
import com.ttyy.commonanno.anno.BindView;
import com.ttyy.commonanno.anno.route.BindRoute;

/**
 * author: admin
 * date: 2017/06/21
 * version: 0
 * mail: secret
 * desc: SecondActivity
 */

@BindRoute("jin.test.ui.appsecond")
@BindLayout2("activity_second")
public class SecondActivity extends Activity{

    @BindView
    TextView tv_second;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JinInjector.get().setRClass(R.class).injectActivity(this);

        tv_second.setText("Hello App Second Page");
    }
}
