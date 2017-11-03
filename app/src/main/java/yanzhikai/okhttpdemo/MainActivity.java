package yanzhikai.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";
    private OkHttpClient mOkHttpClient;
    private Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initOkhttp();
    }

    private void initView() {
        btn_request = (Button) findViewById(R.id.btn_request);
        btn_request.setOnClickListener(this);
    }

    private void initOkhttp() {
        mOkHttpClient = new OkHttpClient();
        getRequest();
    }

    private void getRequest() {
        YoudaoQuery query = new YoudaoQuery("I love you");
        Request request = new Request.Builder()
                .get()
                .url(query.toUrl())
                .build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private void postRequest() {
        YoudaoQuery query = new YoudaoQuery("我爱你");
        FormBody formBody = new FormBody.Builder()
                .add("q", query.query)
                .add("from", query.from)
                .add("to", query.to)
                .add("sign", query.sign)
                .add("salt", query.salt)
                .add("appKey", query.appKey)
                .build();

        Request request = new Request.Builder()
                .url("http://openapi.youdao.com/api")
                .post(formBody)
                .build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request:
//                getRequest();
                postRequest();
                break;
        }
    }
}
