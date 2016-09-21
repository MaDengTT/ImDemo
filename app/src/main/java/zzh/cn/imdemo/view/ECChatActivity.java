package zzh.cn.imdemo.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.util.EasyUtils;

import zzh.cn.imdemo.R;

public class ECChatActivity extends EaseBaseActivity {

    // 当前聊天的 ID
    private String mChatId;
    private ChatFragment chatFragment;
    public static ECChatActivity activityInstance;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toChatUsername =  getIntent().getExtras().getString("userId");
        activityInstance = this;
        // 这里直接使用EaseUI封装好的聊天界面
        chatFragment = new ChatFragment();
        // 将参数传递给聊天界面
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container, chatFragment).commit();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // 确保只有一个聊天界面打开
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, ECMainActivity.class);
            startActivity(intent);
        }
    }

    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        //PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

}
