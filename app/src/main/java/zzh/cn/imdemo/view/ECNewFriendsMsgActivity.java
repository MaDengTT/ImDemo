package zzh.cn.imdemo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseBaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzh.cn.imdemo.R;
import zzh.cn.imdemo.adapter.NewFriendsMsgAdapter;
import zzh.cn.imdemo.db.InviteMessgeDao;
import zzh.cn.imdemo.domain.InviteMessage;

public class ECNewFriendsMsgActivity extends EaseBaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.iv_back_contact)
    ImageView ivBackContact;
    @BindView(R.id.activity_ecnew_friends_msg)
    LinearLayout activityEcnewFriendsMsg;

    public static void startECNewFriendsMsgActivity(Activity activity) {
        if (activity != null) {
            Intent intent = new Intent(activity, ECNewFriendsMsgActivity.class);
            activity.startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecnew_friends_msg);
        ButterKnife.bind(this);
        InviteMessgeDao dao = new InviteMessgeDao(this);
        List<InviteMessage> msgs = dao.getMessagesList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewFriendsMsgAdapter(msgs, this));

        ivBackContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }




}
