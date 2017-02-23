package comqq.example.asus_pc.kuaidiapi;

import android.content.SyncStatusObserver;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private EditText edt;
    private Button btn;
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private String com;
    private String no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initSpinner();

    }

    private void initSpinner() {
        data_list = new ArrayList<String>();
        data_list.add("顺丰");
        data_list.add("申通");
        data_list.add("圆通");
        data_list.add("韵达");
        data_list.add("天天");
        data_list.add("EMS") ;
        data_list.add("中通");
        data_list.add("京东快递");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);


    }

    private void initView() {
        spinner= (Spinner) this.findViewById(R.id.spinner);
        edt= (EditText) findViewById(R.id.edt);
        txt= (TextView) findViewById(R.id.txt);
        btn= (Button) findViewById(R.id.btn);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            com = "sf";
                            break;
                        case 1:
                            com = "sto";
                            break;
                        case 2:
                            com = "yt";
                            break;
                        case 3:
                            com = "yd";
                            break;
                        case 4:
                            com = "tt";
                            break;
                        case 5:
                            com = "ems";
                            break;
                        case 6:
                            com = "zto ";
                            break;
                        case 7:
                            com = "jd";
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    no = String.valueOf(edt.getText().toString());

                    if (no.length()==0) {
                        return;
                    } else {
                        chaxun();
                    }
                }
            });
        }
//426856717652
    private void chaxun() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().get()
                .url("http://v.juhe.cn/exp/index?key=88a2f404586656a2eed65964c64fa569&com="+com+"&no="+no)
                .header("key","88a2f404586656a2eed65964c64fa569")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str=response.body().string();
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(str);
                    }
                });
            }
        });
    }
}
