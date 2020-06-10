package swu.xl.xlnewbottomview;

import androidx.appcompat.app.AppCompatActivity;

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

        List<XLNewBottomView.BottomViewItem> items = new ArrayList<>();
        XLNewBottomView.BottomViewItem item1 = new XLNewBottomView.BottomViewItem(
                R.drawable.contact,
                "用户1"
        );
        XLNewBottomView.BottomViewItem item2 = new XLNewBottomView.BottomViewItem(
                R.drawable.contact,
                "用户2"
        );
        XLNewBottomView.BottomViewItem item3 = new XLNewBottomView.BottomViewItem(
                R.drawable.contact,
                "用户3"
        );
        XLNewBottomView.BottomViewItem item4 = new XLNewBottomView.BottomViewItem(
                R.drawable.contact,
                "用户4"
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