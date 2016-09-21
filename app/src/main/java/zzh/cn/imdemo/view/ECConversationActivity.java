package zzh.cn.imdemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;

import java.util.List;

import zzh.cn.imdemo.R;
import zzh.cn.imdemo.app.DemoHelper;

public class ECConversationActivity extends EaseBaseActivity {

    ECConversationListFragment conversationListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecconversation);
        conversationListFragment = new ECConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new ECConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent = new
                        Intent(ECConversationActivity.this, ECChatActivity.class);
                // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                intent.putExtra("userId", conversation.getUserName());
                intent.putExtra("chatType", conversation.getType());

                startActivity(intent);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.fl_convers, conversationListFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DemoHelper.getInstance().pushActivity(this);

        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                for(EMMessage message:list){
                    DemoHelper.getInstance().getNotifier().onNewMsg(message);
                }
                refreshUIWithMessage();
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        });
    }

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
//                updateUnreadLabel();
//                if (currentTabIndex == 0) {
//                    // refresh conversation list
                    if (conversationListFragment != null) {
                        conversationListFragment.refresh();
//                    }
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        DemoHelper.getInstance().popActivity(this);
    }
}
