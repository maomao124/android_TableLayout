package mao.android_tablelayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
                    new AlertDialog.Builder(MainActivity6.this)
                            .setTitle("更新id为" + id + "的信息")
                            .setMessage("\n\n\n\n\n\n\n\n\n\n")
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
}