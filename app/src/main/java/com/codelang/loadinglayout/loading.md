# EmptyView


> 前言

最近一直在做新项目，做的途中也一直在思考，如何让开发更加的方便，界面的实现更加的优雅，于是，就去github稍微搜索了一下好的例子，结果不尽人意，要么太臃肿，要么移植难，要么结构不清晰，于是，就打算自己来实现一个


>效果图

![](http://oxp6pf88h.bkt.clouddn.com/loadingloading.gif)

>代码使用

```
     LoadingLayout loadingLayout;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         loadingLayout = (LoadingLayout) findViewById(R.id.main_loading);
         loadingLayout.showLoadingView();
     }
 
     public void click(View v) {
         switch (v.getId()) {
             case R.id.main_content:
                 loadingLayout.showContentView();
                 break;
             case R.id.main_empty:
                 loadingLayout.showEmptyView();
                 break;
             case R.id.main_error:
                 loadingLayout.showErrorView();
                 break;
             case R.id.main_load:
                 loadingLayout.showLoadingView();
                 break;
             default:
         }
     }
```

那四个Button就不看了，只是来模拟加载的几种情况，主要来看一下主布局，一般主布局里面放的是一个RecyclerView，为了演示方便就放了一个TextView

```
    <com.codelang.loadinglayout.LoadingLayout
        android:id="@+id/main_loading"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="显示网络加载后的内容" />

    </com.codelang.loadinglayout.LoadingLayout>
```


这么写的好处是什么呢？
 - 再写不用在每个页面里面去include个空布局了
 - 将所有的处理都交给根布局，专注主布局，结构更加的清晰


>实现

先来说说我的思路：
 - 挑选根布局: 挑选一个自己平常在写xml布局的时候用的最多的根布局，因为ConstraintLayout能基本满足所有布局的要求，所以，我打算让LoadingLayout继承ConstraintLayout
 - 添加布局: 将正在加载布局、加载错误布局、加载空布局addView到LoadingLayout
 - 如何去控制布局: 这个地方，我想到了一个办法，比如，当前我要显示一个空布局，遍历LoadingLayout的所有子View全部gone隐藏，然后只Visible空布局，正在加载布局和加载失败原理一样
 - 显示加载成功布局: 加载成功的布局和上面略有一点不同，因为我们已经加载成功了，辅助的三个布局也没有必要再存留在根布局，所以，我们先remove掉这个三个布局，然后遍历所有的子view去Visible
 
然后，来看下LoadingLayout的代码吧
```
/**
 * @author wangqi
 * @since 2017/12/11 14:04
 */

public class LoadingLayout extends ConstraintLayout {
    /**
     * 正在加载的布局
     */
    private View loadingView;
    /**
     * 错误布局
     */
    private View errorView;
    /**
     * 空布局
     */
    private View emptyView;


    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化
     * 添加"空布局"、"错误布局"、"正在加载布局"
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        /*****  加载正在加载页面  *******/
        loadingView = inflater.inflate(R.layout.loading_load, this, false);
        addView(loadingView);
        /****  加载错误页面  *******/
        errorView = inflater.inflate(R.layout.loading_error, this, false);
        addView(errorView);
        /*****  加载空页面  *******/
        emptyView = inflater.inflate(R.layout.loading_empty, this, false);
        addView(emptyView);

        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    /**
     * 显示主布局
     */
    public void showContentView() {
        removeView(errorView);
        removeView(emptyView);
        removeView(loadingView);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示加载错误布局
     */
    public void showErrorView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        errorView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示正在加载布局
     */
    public void showLoadingView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        loadingView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示空布局
     */
    public void showEmptyView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setVisibility(View.GONE);
        }
        emptyView.setVisibility(View.VISIBLE);
    }
}

```

代码十分的简短，逻辑也特别的清晰，如果 想扩展的话，也可以直接在LoadingLayout里面直接处理，比如在showLoadingView的时候做一下网络判断处理，如果无网络的话，显示错误布局，告知用户打开网络，也有可能在显示错误布局的时候，会有点击再次加载功能，这时候，我们就需要在LoadingLayout里面增加接口回调功能，此处就不展示了，下载源码的链接