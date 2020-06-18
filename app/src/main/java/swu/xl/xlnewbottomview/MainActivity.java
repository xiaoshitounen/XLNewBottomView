package swu.xl.xlnewbottomview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XLNewBottomView bottomView = findViewById(R.id.bottom_view);

        List<XLNewItem> items = new ArrayList<>();
        XLNewItem item1 = new XLNewItem(
                this,
                R.drawable.contact,
                "用户1",
                0,
                Color.BLACK,
                Color.MAGENTA
        );
        XLNewItem item2 = new XLNewItem(
                this,
                R.drawable.contact,
                "用户2",
                0,
                Color.BLACK,
                Color.MAGENTA
        );
        XLNewItem item3 = new XLNewItem(
                this,
                R.drawable.contact,
                "用户3",
                0,
                Color.BLACK,
                Color.MAGENTA
        );
        XLNewItem item4 = new XLNewItem(
                this,
                R.drawable.contact,
                "用户4",
                0,
                Color.BLACK,
                Color.MAGENTA
        );

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        bottomView.setItems(items);

        bottomView.setXLBottomViewItemListener(new XLNewBottomView.XLBottomViewItemListener() {
            @Override
            public void itemStatusDidChange(int index) {
                Toast toast = Toast.makeText(MainActivity.this, "第" + (index + 1) + "个按钮被点击", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
}