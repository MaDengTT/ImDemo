package zzh.cn.imdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zzh.cn.imdemo.R;

/**
 * 联系人列表页
 */
public class ECContactListActivity extends EaseBaseActivity {


    ContactListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eccontact_list);

        fragment = new ContactListFragment();

        Map<String, EaseUser> contactsMap = new HashMap<>();
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
