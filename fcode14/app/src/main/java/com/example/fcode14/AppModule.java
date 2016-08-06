package com.example.fcode14;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wuzhuojun on 2016/8/4 0004.
 */
@Module(injects = MainActivity.class)
public class AppModule {
    @Provides
    UserModel provideUserModel() {
        return new UserModel();
    }
}
