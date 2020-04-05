package com.nacos.api.service.auth;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 认证服务用户接口
 */
@FeignClient( value = "nacos-auth" )
public interface AuthUserFeignService {


}
