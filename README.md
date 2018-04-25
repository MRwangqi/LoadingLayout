###v1.0.1 LoadingLayout
为Activity加载提供多布局

![](http://oxp6pf88h.bkt.clouddn.com/loadingloading.gif)

###v1.0.2 修改了下
一般加载页面都是在打开界面进行加载，第一次才会去显示，再次调用则不会显示

![](http://oxp6pf88h.bkt.clouddn.com/loadingloading2.gif)


###v1.0.3 修改了下

增加了加载错误页面，点击错误页面可继续加载

#### 使用如下

在点击错误页面的时候，需要再次执行请求网络方法，为了解耦，我在请求网络的方法上面添加注解@ErrorClick，
点击错误的时候，会自动调用注解的这个方法

```
       @ErrorClick
       public void pullNet(){
            
       }

```

#### 实现原理

在LoadingLayout中，给错误界面增加点击事件，事件响应为通过context找到对应的类，然后遍历该类的所有方法，找到具有@ErrorClick注解的方法，找到的话直接invoke这个方法，也就是调用注解描述的那个方法，然后break掉循环，由于我这个地方用了break，所以，只对最先注解的ErrorClick有效，如果想响应多个ErrorClick描述的方法，可以去掉break


#### 核心代码

```
   private void clickErrorLayout() {
        if (context instanceof BaseNewActivity) {

            Class clazz = context.getClass();
            try {
                Method methods[] = clazz.getDeclaredMethods();
                for (Method m : methods) {
                    if (m.getAnnotation(ErrorClick.class) != null) {
                        m.setAccessible(true);
                        m.invoke(context);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
```
![](http://oxp6pf88h.bkt.clouddn.com/textbricks5.gif)
