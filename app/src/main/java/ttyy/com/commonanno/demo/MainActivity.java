package ttyy.com.commonanno.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ttyy.commonanno.JinInjector;
import com.ttyy.commonanno.anno.BindLayout;
import com.ttyy.commonanno.anno.BindView;
import com.ttyy.commonanno.anno.OnClick;
import com.ttyy.commonanno.anno.OnItemClick;
import com.ttyy.commonanno.anno.OnLongClick;

@BindLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @BindView(R.id.tv_jump)
    TextView tv_jump;

    @BindView(R.id.lv_1)
    ListView lv_1;

    @BindView(R.id.lv_2)
    ListView lv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JinInjector.get().injectActivity(this);

        lv_1.setAdapter(new DemoAdapter());
        lv_2.setAdapter(new DemoAdapter());

    }

    @OnClick(R.id.tv_jump)
    void jumpOnClickJump(){

    }

    @OnLongClick(R.id.tv_jump)
    void logOnClickJump(){
        Log.e("Test", "onLongClickJum[!");
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
