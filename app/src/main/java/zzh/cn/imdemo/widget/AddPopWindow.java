package zzh.cn.imdemo.widget;
  
import android.app.Activity;  
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.ViewGroup.LayoutParams;  
import android.widget.LinearLayout;  
import android.widget.PopupWindow;
import android.widget.Toast;

import zzh.cn.imdemo.R;
import zzh.cn.imdemo.view.AddContactActivity;
import zzh.cn.imdemo.view.ECApplication;
import zzh.cn.imdemo.view.ECLoginActivity;
import zzh.cn.imdemo.view.GroupPickContactsActivity;
import zzh.cn.imdemo.view.GroupsActivity;

/** 
 * 自定义popupWindow 
 *  
 *
 *  
 *  
 */  
public class AddPopWindow extends PopupWindow {  
    private View conentView;
    private final LinearLayout add_group;
    private final LinearLayout add_friend;

    public AddPopWindow(final Activity context) {  
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        conentView = inflater.inflate(R.layout.add_popu_dialog, null);
        add_group = (LinearLayout) conentView.findViewById(R.id.add_group);
        add_friend = (LinearLayout) conentView.findViewById(R.id.add_friend);

        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
        // 设置SelectPicPopupWindow的View  
        this.setContentView(conentView);  
        // 设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(w / 2);
        // 设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        // 设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        this.setOutsideTouchable(true);  
        // 刷新状态  
        this.update();  
        // 实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0000000000);  
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作  
        this.setBackgroundDrawable(dw);  
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
        // 设置SelectPicPopupWindow弹出窗体动画效果  
        this.setAnimationStyle(R.style.AnimationPreview);  
        LinearLayout addTaskLayout = (LinearLayout) conentView  
                .findViewById(R.id.add_group);
        LinearLayout teamMemberLayout = (LinearLayout) conentView  
                .findViewById(R.id.add_friend);


//      //添加popup点击事件
        addTaskLayout.setOnClickListener(new OnClickListener() {
  
            @Override  
            public void onClick(View arg0) {  
                AddPopWindow.this.dismiss();
                //Toast.makeText(ECApplication.getInstance(),"添加群",Toast.LENGTH_SHORT).show();
                GroupsActivity.startGroupsActivity(context);
            }  
        });  
  
        teamMemberLayout.setOnClickListener(new OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
                AddPopWindow.this.dismiss();
                //Toast.makeText(ECApplication.getInstance(),"添加好友",Toast.LENGTH_SHORT).show();

               AddContactActivity.startAddContactActivity(context);
            }  
        });  
    }  

    /** 
     * 显示popupWindow 
     *  
     * @param parent 
     */  
    public void showPopupWindow(View parent) {  
        if (!this.isShowing()) {  
            // 以下拉方式显示popupwindow  
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);  
        } else {  
            this.dismiss();  
        }  
    }  
}  

