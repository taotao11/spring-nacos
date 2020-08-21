package com.nacos.sys.user.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ReslutBean {
    private int code;
    private String message;
    private Object Data;

    public ReslutBean(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        Data = data;
    }
    public static ReslutBean error(String message){
        return new ReslutBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),message,null);
    }
    public static ReslutBean error(int code,String message){
        return new ReslutBean(code,message,null);
    }

    public static ReslutBean error(HttpStatus status){
        return new ReslutBean(status.value(),status.getReasonPhrase(),null);
    }
    public static ReslutBean success(Object data){
        return new ReslutBean(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),data);
    }
    public static ReslutBean success(String message){
        return new ReslutBean(HttpStatus.OK.value(),message,null);
    }
}
