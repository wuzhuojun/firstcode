# firstcode

Android入门练手的demo，以下是项目的目录：



1、fcode1
Activity 与 Fragment的练习

2、fcode2
butterknife 注解的使用

gradle设置：

    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    apply plugin: 'android-apt'

需要增加库：

    compile 'com.jakewharton:butterknife:8.1.0'
    apt 'com.jakewharton:butterknife-compiler:8.1.0'
	
资料：
http://jakewharton.github.io/butterknife/

3、fcode3
获取网络数据
多线程的使用 Thread & AsyncTask 
发消息到主线程更新UI元素

4、fcode4
Service的使用
Activity 与 Service 的相互通信。
Service 通过 回调的方式来通知 主线程更新UI元素

5、fcode5
Service 通过 广播的方式来通知 主线程更新UI元素
每次用Service都需要另外启动线程来处理耗时操作。而IntentService对此做了封装，不需要每次另外启动线程。

6、fcode6
AIDL

7、fcode7
OkHttp & gson 的使用

需要增加库：

    compile 'com.squareup.okhttp:okhttp:2.4.0'
    compile 'com.google.code.gson:gson:2.4'

资料：
http://square.github.io/okhttp
https://github.com/google/gson

8、fcode8
viewpager

引入的库：
compile 'com.android.support:design:23.2.1'

修改样式：

    G:\AndroidTestWork\fcode8\app\src\main\res\values\styles.xml
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    
9、fcode9
ListView的使用


10、fcode10
RecyclerView的使用

需要增加库

    compile 'com.android.support:recyclerview-v7:23.4.0'

资料：
RecyclerView 可以做各种复杂的列表界面
https://github.com/cymcsg/UltimateRecyclerView
https://github.com/lucasr/twoway-view


使用图片缓存 Fresco
http://fresco-cn.org/docs/index.html

图片缓存开源组件比较：
http://www.cnblogs.com/younghao/p/5088299.html
http://www.trinea.cn/android/android-image-cache-compare

11、fcode11

RecyclerView 的第三方组件 UltimateRecyclerView；封装了很多功能间隔线、下拉刷新、加载更多、加载动画 等
缓存组件 文件/sharepreference；ASimpleCache 是一个为android制定的 轻量级的 开源缓存框架。轻量到只有一个java文件（由十几个类精简而来）。


资料：
https://github.com/yangfuhai/ASimpleCache
https://github.com/cymcsg/UltimateRecyclerView


12、fcode12
数据库 greenDAO

资料：
https://github.com/greenrobot/greenDAO
http://itangqi.me/2015/07/26/orm-greendao-summary/


13、fcode13
广播接受者
内容提供者
logger

资料：
https://github.com/orhanobut/logger

14、fcode14
dagger

http://my.oschina.net/rengwuxian/blog/287892
https://github.com/square/dagger
http://blog.csdn.net/ljphhj/article/details/37663071
http://fanxu.me/post/2013-07-18



15、fcode15
EventBus

https://github.com/greenrobot/EventBus
http://www.cnblogs.com/angeldevil/p/3715934.html




16、fcode16
RxAndroid 和 Retrofit 结合使用

https://github.com/ReactiveX/RxAndroid
https://github.com/lzyzsd/Awesome-RxJava


https://github.com/drakeet/Meizhi
https://github.com/rengwuxian/RxJavaSamples
https://github.com/xcc3641/SeeWeather