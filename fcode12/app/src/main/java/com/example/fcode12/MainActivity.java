package com.example.fcode12;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.EditText;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ultimate_recycler_view)
    UltimateRecyclerView recyclerView;

    private int uid = 1;

    @OnClick(R.id.btn_add_user) void AddUser()
    {
        // 插入操作，简单到只要你创建一个 Java 对象
        UserInfoModel user = new UserInfoModel(uid+"", "用户"+uid);
        getUserInfoDao().insert(user);
        uid++;
    }

    @OnClick(R.id.btn_show_user) void UpdateUserList()
    {
        List userList = getUserInfoDao().loadAll();

        //设置recycler的内容
        RecyclerViewAdapter listViewAdapter = new RecyclerViewAdapter(MainActivity.this);
        listViewAdapter.addDataAll(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(listViewAdapter);

        recyclerView.enableDefaultSwipeRefresh(true);//开启下拉刷新

        //添加间隔线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;
    public static final String TAG = "DaoExample";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupDatabase();
    }

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private UserInfoModelDao getUserInfoDao() {
        return daoSession.getUserInfoModelDao();
    }
}
