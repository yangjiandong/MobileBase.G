package com.ek.mobileapp.demo;

import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.utils.GlobalCache;

public class DemoData {
    public static final String DEMO = "demo";

    public static void setUserDTO(){
        UserDTO u = new UserDTO();
        u.setId(1L);
        u.setName("演示用户");
        u.setLoginName("demo");
        u.setPassword("demo");
        GlobalCache.getCache().setLoginuser(u);
    }
}
