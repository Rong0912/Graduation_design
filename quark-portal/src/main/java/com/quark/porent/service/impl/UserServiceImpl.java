package com.quark.porent.service.impl;

import com.quark.porent.entity.QuarkResult;
import com.quark.porent.entity.User;
import com.quark.porent.service.UserService;
import com.quark.porent.utils.HttpClientUtils;
import com.quark.porent.utils.JsonUtils;
import groovy.transform.ASTTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.quark.porent.utils.PhoneCode.getPhonemsg;

@Service
public class UserServiceImpl implements UserService{

    @Value("${api_getUserByToken}")
    private String api_getUserByToken;

    @Override
    public User getUserByApi(String token) {
        String s = HttpClientUtils.doGet(api_getUserByToken + token);
        QuarkResult quarkResult = JsonUtils.jsonToQuarkResult(s, User.class);
        User data= (User) quarkResult.getData();
        return data;
    }


}
