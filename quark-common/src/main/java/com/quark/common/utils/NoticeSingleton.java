package com.quark.common.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


/*@Component
@Scope("singleton")*/
public class NoticeSingleton {

    private static Map<String,Date> map=new HashMap<>();
    public static synchronized void getData(String phone){
        Date date=map.get(phone);
        Date now=new Date();
        if(date==null){
            map.put(phone,now);
        }
        long time=now.getTime()-map.get(phone).getTime();
        if(time>8*1000){//8秒
            throw new RuntimeException("获取验证码太频繁");
            //System.out.println("1.................................");
        }
        else {
            map.put(phone,now);
            System.out.println("0.................................");
        }

    }

    public static void main(String[] args) {
       getData("1111");
    }



}
