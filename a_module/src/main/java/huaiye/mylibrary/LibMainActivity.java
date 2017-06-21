package huaiye.mylibrary;

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
 * desc: LibMainActivity
 */

@BindRoute("jin.test.libmain")
@BindLayout2("activity_lib_main")
public class LibMainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JinInjector.get().setRClass(R.class).injectActivity(this);

        int id = -1;
        try {
            Class layout = Class.forName("huaiye.mylibrary.R$layout");
            id = (int) layout.getField("activity_lib_main").get(layout);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Log.e("Test", "id "+id+" layout.id "+R.layout.activity_lib_main);

        Log.e("Test", "LibMainActivity");

    }
}
