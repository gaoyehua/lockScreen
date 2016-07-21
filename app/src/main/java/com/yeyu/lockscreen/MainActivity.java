package com.yeyu.lockscreen;

import android.app.Activity;
import android.app.Notification;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取设备管理者
        devicePolicyManager =(DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        //获取管理者标识
        componentName =new ComponentName(this,admin.class);
        //判断超级管理者是否激活
        if(devicePolicyManager.isAdminActive(componentName)){
            devicePolicyManager.lockNow();
            finish();
        }

    }

    public void lock(View view) {
        devicePolicyManager.lockNow();
    }
    public void register(View view) {
        //代码激活超级管理者
        Intent intent =new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        //超级管理者的标识
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
        //描述信息
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"一键锁屏，很好用");
        startActivity(intent);
    }
    public void delete(View view) {
        //判断超级管理员是否激活
        if(devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.removeActiveAdmin(componentName);
        }
    }
}
