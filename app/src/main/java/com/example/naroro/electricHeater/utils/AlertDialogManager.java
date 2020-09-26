package com.example.naroro.electricHeater.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.naroro.electricHeater.R;

/**
 *自定义对话框
 */
public class AlertDialogManager {

    /**
     * @param context        上下文
     * @param title          标题
     * @param content        内容
     * @param btnCancleText  取消按钮文本
     * @param btnSureText    确定按钮文本
     * @param touchOutside   外部取消
     * @param cancleListener 取消监听
     * @param sureListener   确定监听
     * @return
     */
    public synchronized static AlertDialog showDialog(Context context,
                                                      String title,
                                                      String content,
                                                      String btnCancleText,
                                                      String btnSureText,
                                                      boolean touchOutside,
                                                      DialogInterface.OnClickListener cancleListener,
                                                      DialogInterface.OnClickListener sureListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(touchOutside);
        dialog.setCancelable(false);

        View view = View.inflate(context, R.layout.alert_dialog, null);
        //标题
        TextView tvTitle = view.findViewById(R.id.tv_alert_title);
        //内容
        TextView tvContent = view.findViewById(R.id.tv_alert_content);
        //取消按钮
        Button buttonCancle = view.findViewById(R.id.btn_alert_cancel);
        //确定按钮
        Button buttonOk = view.findViewById(R.id.btn_alert_ok);
        //线
        View viewLine = view.findViewById(R.id.v_alert_line);

        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }

        tvContent.setText(TextUtils.isEmpty(content) ? "" : content);

        if (TextUtils.isEmpty(btnCancleText)) {
            buttonCancle.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            buttonCancle.setText(btnCancleText);
        }

        buttonOk.setText(TextUtils.isEmpty(btnSureText) ? "确定" : btnSureText);
        final AlertDialog dialogFinal = dialog;
        final DialogInterface.OnClickListener finalCancleListener = cancleListener;
        final DialogInterface.OnClickListener finalSureListener = sureListener;
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCancleListener.onClick(dialogFinal, DialogInterface.BUTTON_NEGATIVE);
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalSureListener.onClick(dialogFinal, DialogInterface.BUTTON_POSITIVE);
            }
        });

        //设置背景透明,去四个角
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        dialog.getWindow().setLayout(800, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view);

        return dialog;
    }
}
/**
 * 自定义对话框的引用
 */
//    private void showDialog1() {
//        AlertDialogManager.showDialog(MainActivity.this,
//                "标题",
//                "我是中国人，我爱我的祖国",
//                null,
//                "确定",
//                false,
//                null,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });
//    }
//
//    private void showDialog2() {
//        AlertDialogManager.showDialog(this,
//                "标题",
//                "我是中国人，我爱我的祖国",
//                "取消",
//                "确定",
//                false,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                },
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });
//    }