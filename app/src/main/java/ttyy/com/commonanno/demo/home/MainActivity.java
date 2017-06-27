package ttyy.com.commonanno.demo.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ttyy.commonanno.Injectors;
import com.ttyy.commonanno.anno.BindLayout;
import com.ttyy.commonanno.anno.BindView;
import com.ttyy.commonanno.anno.OnClick;
import com.ttyy.commonanno.anno.OnItemClick;
import com.ttyy.commonanno.anno.OnLongClick;
import com.ttyy.commonanno.anno.route.BindRoute;

import ttyy.com.commonanno.demo.DemoAdapter;
import ttyy.com.commonanno.demo.R;

@BindRoute("jin.test.ui.main")
@BindLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_1)
    ListView lv_1;

    @BindView(R.id.lv_2)
    ListView lv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injectors.get().injectActivity(this);

        lv_1.setAdapter(new DemoAdapter());
        lv_2.setAdapter(new DemoAdapter());

    }

    @OnClick(R.id.tv_app_jump)
    void jumpOnClickAppJump(){
        Injectors.get().buildRouter("jin.test.ui.appsecond?a=1&b=1.1f&c=1.2d&d=str")
                .putInt("a", 2)
                .putFloat("b", 2.1f)
                .putDouble("c", 2.2d)
                .putString("d", "str2")
                .navigate(this);
    }

    @OnClick(R.id.tv_lib_a_jump)
    void jumpOnClickLibAJump(){
        Injectors.get().buildRouter("jin.test.lib_a_main")
                .navigate(this);
    }

    @OnClick(R.id.tv_lib_b_jump)
    void jumpOnClickLibBJump(){
        Injectors.get().buildRouter("jin.test.lib_b_main")
                .navigate(this);
    }

    @OnClick(R.id.tv_lib_c_jump)
    void jumpOnClickLibCJump(){
        Injectors.get().buildRouter("jin.test.lib_c_main")
                .navigate(this);
    }

    @OnClick(R.id.tv_hello)
    void logOnClickHello(){
        Log.e("Test", "onClickHello!");
    }

    @OnLongClick(R.id.tv_hello)
    void logOnLongClickHello(){
        Log.e("Test", "onLongClickHello!");
    }

    @OnItemClick(R.id.lv_1)
    void onLV1ItemClicked(AdapterView parent, View itemview, int position, long id){
        Log.e("Test", "-------onLV1ItemClicked-------");
        Log.e("Test", "parent   -> "+parent);
        Log.e("Test", "itemView -> "+itemview);
        Log.e("Test", "position -> "+position);
        Log.e("Test", "id       -> "+id);
    }

    @OnItemClick(R.id.lv_2)
    void onLV2ItemClicked(AdapterView parent, View itemview, int position, long id){
        Log.e("Test", "-------onLV2ItemClicked-------");
        Log.e("Test", "parent   -> "+parent);
        Log.e("Test", "itemView -> "+itemview);
        Log.e("Test", "position -> "+position);
        Log.e("Test", "id       -> "+id);
    }

}
