package com.quark.rest.controller;

import com.quark.common.dto.UploadResult;
import com.quark.common.exception.ServiceProcessException;
import com.quark.rest.service.RedisService;
import com.quark.rest.service.UserService;
import com.quark.rest.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(value = "文件上传接口",description = "图片上传")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${REDIS_RANK_USERS}")
    private String REDIS_RANK_USERS;
    @Autowired
    private RedisService<List<Object>> redisService;

    @Autowired
    private UserService userService;

    @ApiOperation("图片上传接口")
    @PostMapping("/image")
    public UploadResult upload(@RequestParam("file") MultipartFile file) {
        UploadResult result = null;
        if (!file.isEmpty()) {
            try {
                String s = FileUtils.uploadFile(file);
                result = new UploadResult(0, new UploadResult.Data(s));
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result = new UploadResult(1,"上传图片失败");
            }
            return result;
        }
        result = new UploadResult(1,"文件不存在");
        return result;
    }

    @ApiOperation("用户头像上传接口")
    @PostMapping("/usericon/{token}")
    public UploadResult iconUpload(@PathVariable("token") String token,@RequestParam("file") MultipartFile file){
        UploadResult result = null;
        if (!file.isEmpty()) {
            try {
                String icon = FileUtils.uploadFile(file);
                userService.updateUserIcon(token,icon);
                //当新用户换头像时，删除当前redis中用户头像缓存并重新加载
                redisService.deleteString(REDIS_RANK_USERS);
                return new UploadResult(0, new UploadResult.Data(icon));

            } catch (IOException e) {
                e.printStackTrace();
                return new UploadResult(1,"上传图片失败");
            }catch (ServiceProcessException e1){
                e1.printStackTrace();
                return new UploadResult(1,e1.getMessage());
            }
        }
        return new UploadResult(1,"文件不存在");
    }



}
