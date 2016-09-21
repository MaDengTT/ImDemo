package zzh.cn.imdemo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

import zzh.cn.imdemo.app.Constant;

/**
 * Created by Administrator on 2016/9/21.
 */

public class ECChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void setUpView() {
        setChatFragmentListener(this);
        super.setUpView();

    }

    //消息菜单长按菜单
    @Override
    protected void registerExtendMenuItem() {
        super.registerExtendMenuItem();

    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {
        if (chatType == Constant.CHATTYPE_GROUP) {
            //如果聊天消息是群的话
            EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
            if (group == null) {
                //没有这个群
                return;
            }

        } else if (chatType == Constant.CHATTYPE_CHATROOM) {
            //聊天室
        }
    }

    /**
     * 点击头像事件
     * @param username
     */
    @Override
    public void onAvatarClick(String username) {

    }

    /**
     * 长按头像事件
     * @param username
     */
    @Override
    public void onAvatarLongClick(String username) {

    }

    /**
     * 消息点击事件，返回true表示覆盖
     * @param message
     * @return
     */
    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    /**
     * 消息框长按事件
     * @param message
     */
    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    /**
     * 底部更多消息界面
     * @param itemId id
     * @param view  view
     * @return
     */
    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    /**
     * 设置自定义聊天提供程序
     * //默认拥有基本，如需红包，拨打电话在这加入
     * @return
     */
    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
