package com.example.hezhanheng.scrollactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.common.BitmapUtils;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mContent;
    private Button mCreate,mScan;
    private ImageView mImage;
    private final static int REQ_CODE = 1028;
    private TextView mHint;
    private TextView mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
        String isAvailable =  NetworkUtils.isNetworkAvailable(MessageActivity.this);
        showToast("您的网络"+isAvailable);
//        boolean isWifiEnabled =  NetworkUtils.isNetworkAvailable(MessageActivity.this);
//        showToast("您的WiFi状态是："+isWifiEnabled);
    }
    private void initView() {
        mContent = (EditText)findViewById(R.id.edt_content);
        mCreate = (Button)findViewById(R.id.btn_create);
        mScan = (Button)findViewById(R.id.btn_scan);
        mImage = (ImageView)findViewById(R.id.iv_image);
        mHint = (TextView)findViewById(R.id.tv_hint);
        mResult = (TextView)findViewById(R.id.tv_result);
        mCreate.setOnClickListener(this);
        mScan.setOnClickListener(this);
    }
@Nullable
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create :
                //生成二维码
                String content = mContent.getText().toString().trim();
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapUtils.create2DCode(content);
                    mImage.setVisibility(View.VISIBLE);
                    mHint.setVisibility(View.GONE);
                    mImage.setImageBitmap(bitmap);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_scan :
                //扫码
                Intent intent = new Intent(MessageActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQ_CODE);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            mImage.setVisibility(View.VISIBLE);
            mContent.setVisibility(View.GONE);

            String result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
            Bitmap bitmap = data.getParcelableExtra(CaptureActivity.SCAN_QRCODE_BITMAP);

            mResult.setText("扫描结果为：" + result);
            showToast("扫码结果："+result);
            if (bitmap != null) {
                mImage.setImageBitmap(bitmap);
            }
        }
    }
    private void showToast(String msg) {
        Toast.makeText(MessageActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
        String s="hello wrod";
    }
}
