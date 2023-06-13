package cn.didiao.entity;


import java.io.Serializable;

import java.util.Date;

/**
* 
* @TableName t_business_info
*/
public class BusinessInfo implements Serializable {

    /**
    * id
    */

    private Integer id;
    /**
    * 商家名称
    */

    private String businessname;
    /**
    * 昵称
    */

    private String nickname;
    /**
    * 密码MD5加密
    */

    private String password;
    /**
    * 店铺地址
    */

    private String address;
    /**
    * 联系方式
    */

    private String phone;
    /**
    * 创建时间
    */

    private Date createtime;
    /**
    * 状态 0 冻结 1正常
    */

    private Integer status;

    /**
    * id
    */
    public void setId(Integer id){
    this.id = id;
    }

    /**
    * 商家名称
    */
    public void setBusinessname(String businessname){
    this.businessname = businessname;
    }

    /**
    * 昵称
    */
    public void setNickname(String nickname){
    this.nickname = nickname;
    }

    /**
    * 密码MD5加密
    */
    public void setPassword(String password){
    this.password = password;
    }

    /**
    * 店铺地址
    */
    public void setAddress(String address){
    this.address = address;
    }

    /**
    * 联系方式
    */
    public void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 创建时间
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }

    /**
    * 状态 0 冻结 1正常
    */
    public void setStatus(Integer status){
    this.status = status;
    }


    /**
    * id
    */
    public Integer getId(){
    return this.id;
    }

    /**
    * 商家名称
    */
    public String getBusinessname(){
    return this.businessname;
    }

    /**
    * 昵称
    */
    public String getNickname(){
    return this.nickname;
    }
    public String getPassword(){
    return this.password;
    }

    /**
    * 店铺地址
    */
    public String getAddress(){
    return this.address;
    }

    /**
    * 联系方式
    */
    public String getPhone(){
    return this.phone;
    }

    /**
    * 创建时间
    */
    public Date getCreatetime(){
    return this.createtime;
    }

    /**
    * 状态 0 冻结 1正常
    */
    public Integer getStatus(){
    return this.status;
    }

}
