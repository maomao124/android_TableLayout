package mao.android_tablelayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.UUID;

public class MainActivity6 extends AppCompatActivity
{

    /**
     * 得到int随机
     *
     * @param min 最小值
     * @param max 最大值
     * @return int
     */
    public static int getIntRandom(int min, int max)
    {
        if (min > max)
        {
            min = max;
        }
        return min + (int) (Math.random() * (max - min + 1));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        TableLayout tableLayout = findViewById(R.id.TableLayout);

        for (int i = 0; i < 500; i++)
        {
            int id = 100000 + i + 1;
            String name = UUID.randomUUID().toString().substring(0, 6);
            String sex = Math.random() > 0.5 ? "男" : "女";
            int age = getIntRandom(15, 25);

            View view = LayoutInflater.from(this).inflate(R.layout.item_table, null);
            TextView textView_id = view.findViewById(R.id.id);
            textView_id.setText(String.valueOf(id));
            TextView textView_name = view.findViewById(R.id.name);
            textView_name.setText(name);
            TextView textView_sex = view.findViewById(R.id.sex);
            textView_sex.setText(sex);
            if (sex.equals("男"))
            {
                textView_sex.setTextColor(Color.rgb(130, 80, 255));
            }
            TextView textView_age = view.findViewById(R.id.age);
            textView_age.setText(String.valueOf(age));

            TableRow tableRow = view.findViewById(R.id.TableRow);
            tableRow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    View view1 = LayoutInflater.from(MainActivity6.this).inflate(R.layout.item_edit, null);

                    EditText editText_name = view1.findViewById(R.id.EditText_name);
                    EditText editText_sex = view1.findViewById(R.id.EditText_sex);
                    EditText editText_age = view1.findViewById(R.id.EditText_age);

                    editText_name.setText(textView_name.getText().toString());
                    editText_sex.setText(textView_sex.getText().toString());
                    editText_age.setText(textView_age.getText().toString());

                    new AlertDialog.Builder(MainActivity6.this)
                            .setTitle("更新id为" + id + "的信息")
                            .setView(view1)
                            .setPositiveButton("确认修改", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    textView_name.setText(editText_name.getText().toString());
                                    textView_sex.setText(editText_sex.getText().toString());
                                    textView_age.setText(editText_age.getText().toString());
                                    toastShow("已更改");
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                }
            });

            tableRow.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    new AlertDialog.Builder(MainActivity6.this)
                            .setTitle("删除提示")
                            .setMessage("是否删除id为" + id + "的信息？")
                            .setPositiveButton("确定删除", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    tableLayout.removeView(view);
                                    toastShow("已删除");
                                }
                            })
                            .setNeutralButton("取消", null)
                            .create()
                            .show();
                    return true;
                }
            });

            tableLayout.addView(view);
        }
    }

    /**
     * 显示消息
     *
     * @param message 消息
     */
    private void toastShow(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}