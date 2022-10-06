package mao.android_tablelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
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

        for (int i = 0; i < 100; i++)
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
            TextView textView_age = view.findViewById(R.id.age);
            textView_age.setText(String.valueOf(age));
            tableLayout.addView(view);
        }
    }
}