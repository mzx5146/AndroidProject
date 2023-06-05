package com.example.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rebootButton = (Button) findViewById(R.id.btn_reboot);
        rebootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRebootDialog();
            }
        });
    }

    //弹窗确定是否重启1
    private void showRebootDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Phone Reboot");
        builder.setMessage("确定重启手机");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rebootPhone();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void rebootPhone() {
        try {
            //重启代码
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(process.getOutputStream());
            outputStream.writeBytes("reboot \n");
            outputStream.flush();
            outputStream.writeBytes("exit");
                    outputStream.flush();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}