package swu.xl.xlnewbottomview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class XLNewItem extends RelativeLayout {
    //日志
    public static final String TAG = XLNewItem.class.getSimpleName();

    //资源
    private int icon_id = R.drawable.ic_launcher_background;
    //文本
    private String title = "测试";
    //绘制的画笔
    private Paint paint;

    //字体的大小
    private int text_size = 14;

    //图片的大小
    private int icon_size = 20;

    //图片和文本的间隔
    private int spacing = 5;

    //正常状态的颜色
    private int normal_color = Color.BLACK;
    private int select_color = Color.MAGENTA;

    //记录自身的索引
    private int item_index = 0;

    //显示icon的视图
    private ImageView icon_view;
    //显示title的视图
    private TextView title_view;

    //按钮的状态-正常
    public static final int STATUS_NORMAL = 0;
    //按钮的状态-选中
    public static final int STATUS_SELECT = 1;

    //绘制文本的素材
    private TextResource textResource;

    /**
     * 构造方法：Java代码初始化
     * @param context
     */
    public XLNewItem(Context context, int icon_id, String title, int item_index, int normal_color, int select_color) {
        super(context);

        this.icon_id = icon_id;
        this.title = title;
        this.item_index = item_index;
        this.normal_color = normal_color;
        this.select_color = select_color;

        //初始化操作
        init();
    }

    /**
     * 构造方法：Xml代码初始化
     * @param context
     * @param attrs
     */
    public XLNewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //1.获得所有属性值的集合
        if (attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XLNewItem);
            //2.解析属性
            icon_id = typedArray.getResourceId(R.styleable.XLNewItem_icon_id,icon_id);
            title = typedArray.getString(R.styleable.XLNewItem_title);
            normal_color = typedArray.getColor(R.styleable.XLNewItem_normal_color_item,normal_color);
            select_color = typedArray.getColor(R.styleable.XLNewItem_select_color_item,select_color);
            text_size = typedArray.getInteger(R.styleable.XLNewItem_text_size,text_size);
            icon_size = typedArray.getInteger(R.styleable.XLNewItem_icon_size, icon_size);
            spacing = typedArray.getInteger(R.styleable.XLNewItem_spacing,spacing);

            //3.释放资源
            typedArray.recycle();
        }

        //初始化操作
        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        //setBackgroundColor(Color.RED);

        //初始化画笔信息
        paint = new Paint();
        paint.setTextSize(PxUtil.spToPx(text_size,getContext()));
        paint.setColor(normal_color);

        //初始化绘制字体的信息
        textResource = new TextResource();
    }

    /**
     * 改变状态
     * @param status
     */
    public void changeStatus(int status){
        //判断状态
        if (status == STATUS_NORMAL){
            icon_view.setColorFilter(normal_color);

            paint.setColor(normal_color);
        }else {
            icon_view.setColorFilter(select_color);

            paint.setColor(select_color);
        }

        //刷新界面
        invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        //计算文本的宽度，高度
        int textWidth = (int) paint.measureText(title);
        int textHeight = (int) (paint.getFontMetrics().bottom-paint.getFontMetrics().top);

        //图片的大小
        int icon_size = PxUtil.dpToPx(this.icon_size,getContext());
        //图片的左右间隔
        int padding_icon = (getWidth() - icon_size) / 2;
        //图片和上边框的间距
        int padding_top = PxUtil.dpToPx(this.spacing,getContext());
        //图片和文本的间隔
        int spacing = PxUtil.dpToPx(this.spacing,getContext());

        //文本的左右间隔
        int padding_text = (getWidth() - textWidth) / 2;

        //如果布局发生改变
        if (changed){
            Log.d(TAG,"XLNewItem：+"+item_index+"开始layout了");

            //创建控件
            icon_view = new ImageView(getContext());
            title_view = new TextView(getContext());
            //设置数据
            icon_view.setImageResource(icon_id);

            //icon的位置
            int icon_left = padding_icon;
            int icon_top = padding_top;
            int icon_right = icon_left + icon_size;
            int icon_bottom = icon_top + icon_size;

            //text的位置
            int text_left = padding_text;
            int text_top = icon_bottom + spacing;
            int text_right = text_left + textWidth;
            int text_bottom = text_top + textHeight;

            //textResource
            textResource.x = text_left;
            int distance = (int) (textHeight / 2 - paint.getFontMetrics().bottom);
            textResource.y = text_top + (getHeight()-text_top) / 2 + distance;
            textResource.text = title;

            //layout
            icon_view.layout(icon_left,icon_top,icon_right,icon_bottom);
            title_view.layout(text_left,text_top,text_right,text_bottom);

            //添加到视图中
            addView(icon_view);
            addView(title_view);

            //改为选中状态
            changeStatus(STATUS_NORMAL);
        }
    }

    /**
     * 绘制文本所需要的条件
     */
    private class TextResource {
        int x;
        int y;
        String text;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        //绘制文本
        canvas.drawText(
                textResource.text,
                textResource.x,
                textResource.y,
                paint
        );
    }

    //setter，getter方法
    public int getItem_index() {
        return item_index;
    }

}
