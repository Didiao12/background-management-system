package cn.didiao.utils;

import com.google.gson.Gson;

public class BaseResult<T> {
    private Integer code;
    private String msg;
    private T data;



    private BaseResult(){}
    private BaseResult(Integer code,String msg,T data){

        this.code=code;
        this.msg=msg;
        this.data=data;
    }



    public static String success(){
        return new Gson().toJson(new BaseResult(200,"success",null)) ;
    }
    public static <T>  String success(T data){
        return new Gson().toJson(new BaseResult(200,"success",data));
    }
    public static String error(Integer code ,String msg){
        return  new Gson().toJson(new BaseResult(code,msg,null));
    }
}
