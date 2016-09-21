package zzh.cn.imdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zzh.cn.imdemo.R;
import zzh.cn.imdemo.app.DemoHelper;
import zzh.cn.imdemo.db.DbOpenHelper;

/**
 * 联系人列表页
 */
public class ECContactListActivity extends EaseBaseActivity {


    ContactListFragment fragment;

    public static void startECContactListActivity(Activity activity) {
        if (activity != null) {
            Intent intent = new Intent(activity, ECContactListActivity.class);
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eccontact_list);

        Map<String, EaseUser> contactList = DemoHelper.getInstance().getContactList();
        for (EaseUser user : contactList.values()) {
            Log.d("TAG", user.getUsername());
        }

        fragment = new ContactListFragment();
        fragment.setContactListItemClickListener(new ContactListFragment.EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
                Log.e("TAG", user.getUsername());
//                Intent intent = new Intent(ECContactListActivity.this, ECChatActivity.class);
//                intent.putExtra("userId", user.getUsername());
//                intent.putExtra("chatType", EMMessage.ChatType.Chat);
//                startActivity(intent);
            }
        });
        Map<String, EaseUser> contactsMap = new HashMap<>();
        EaseUser one = new EaseUser("a1");
        EaseUser two = new EaseUser("a2");
        contactsMap.put("1", one);
        contactsMap.put("2", two);
        for(int i = 0;i<10;i++) {
            EaseUser user= new EaseUser("联系人：" + i);
            contactsMap.put("item" + i, user);
        }
        getContacts();
        //需要获取联系人列表
        fragment.setContactsMap(contactsMap);



        fragment.setContactListItemClickListener(new ContactListFragment.EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
                //点击事件
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.fl_contact, fragment).commit();
    }

    public Map<String,EaseUser> getContacts(){

        try {
            Log.d("Contact", "获取联系人列表");
            List<String> allContactsFromServer = EMClient.getInstance().contactManager().getAllContactsFromServer();
            Log.d("allContact", allContactsFromServer.toString());
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

        return null;
    }

}
