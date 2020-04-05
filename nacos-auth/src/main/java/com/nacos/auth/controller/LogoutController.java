package com.nacos.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class LogoutController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @DeleteMapping(value = "/exit")
    public String revokeToken(String access_token){
//        Result msg = new Result();
        if (consumerTokenServices.revokeToken(access_token)){
//            msg.setCode(StatusCode.SUCCESS_CODE);
            return "注销成功";
        }else {
//            msg.setCode(StatusCode.FAILURE_CODE);
            return "注销失败";
        }

    }
}
