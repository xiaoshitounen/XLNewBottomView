# XLNewBottomView
XLBottomView的完善版本

详细内容博客地址:[自定义View-XLNewBottomView](https://fanandjiu.com/%E8%87%AA%E5%AE%9A%E4%B9%89View-XLNewBottomView/#more)

简介：

模拟BottomNavigationView，完善了之前 XLBottomView 无法使用 XLItem 的问题。

为了避免麻烦，我直接在该项目中，新建了一个XLNewItem的类来解决之前的问题。

app模块是使用例子，其运行效果：

![](https://android-1300729795.cos.ap-chengdu.myqcloud.com/project/Self_View/XLBottomView/XLBottomView.gif)


### 1. 添加依赖

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
~~~
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
~~~

Step 2. Add the dependency
~~~
dependencies {
    implementation 'com.github.xiaoshitounen:XLNewBottomView:1.0.0'
}
~~~

### 2. Xml文件中静态添加使用

~~~xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <swu.xl.xlnewbottomview.XLNewBottomView
        android:id="@+id/bottom_view"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:background="#999999"
        app:normal_color="#000000"
        app:select_color="#ff9900"
        app:hasLeftOrRightSize="false"
        app:item_size="50"
        android:layout_alignParentBottom="true"
        app:isSelectClick="true"
        />

</RelativeLayout>
~~~

数据源目前只支持Java代码添加
~~~java
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
~~~

#### ① 属性

- normal_color：item正常状态下的颜色
- select_color：item选中状态的颜色
- hasLeftOrRightSize：两端是否留有间距
- item_size：item的大小
- isSelectClick：选中的item被点击是否响应回调事件

#### ② 回调当前被点击的item

~~~java
//实现回调接口
public interface XLBottomViewItemListener{
    void itemStatusDidChange(int index);
}
~~~

~~~java
bottomView.setXLBottomViewItemListener(new XLBottomView.XLBottomViewItemListener() {
    @Override
    public void itemStatusDidChange(int index) {
        Toast toast = Toast.makeText(MainActivity.this, "第" + (index + 1) + "个按钮被点击", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
});
~~~


### 3. Java代码中动态添加

~~~java
//item_layout暂时没有用，没有的话可以传0
public XLBottomView(Context context, int normal_color, int select_color, boolean hasLeftOrRightSize, int item_size);
~~~

