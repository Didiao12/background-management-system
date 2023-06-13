package cn.didiao.entity;


import java.io.Serializable;

import java.util.Date;


/**
* 
* @TableName user_info
*/
public class UserInfo implements Serializable {

    /**
    * id
    */

    private Integer id;
    /**
    * 账户名
    */

    private String userName;
    /**
    * 密码(存储加密后的)
    */

    private String password;
    /**
    * 昵称
    */

    private String nick_name;
    /**
    * 手机号
    */

    private String phone;
    /**
    * 地址
    */

    private String address;
    /**
    * 创建时间
    */

    private Date createtime;
    /**
    * 0 ：冻结，1：正常
    */

    private Integer status;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    /**
    * 角色id
    */

    private Integer role_id;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }



    /**
    * id
    */
    public void setId(Integer id){
    this.id = id;
    }

    /**
    * 账户名
    */
    public void setUserName(String userName){
    this.userName = userName;
    }

    /**
    * 密码(存储加密后的)
    */
    public void setPassword(String password){
    this.password = password;
    }

    /**
    * 昵称
    */

    /**
    * 手机号
    */
    public void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 地址
    */
    public void setAddress(String address){
    this.address = address;
    }

    /**
    * 创建时间
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }

    /**
    * 0 ：冻结，1：正常
    */
    public void setStatus(Integer status){
    this.status = status;
    }

    /**
    * 角色id
    */



    /**
    * id
    */
    public Integer getId(){
    return this.id;
    }

    /**
    * 账户名
    */
    public String getUserName(){
    return this.userName;
    }

    /**
    * 密码(存储加密后的)
    */
    public String getPassword(){
    return this.password;
    }

    /**
    * 昵称
    */


    /**
    * 手机号
    */
    public String getPhone(){
    return this.phone;
    }

    /**
    * 地址
    */
    public String getAddress(){
    return this.address;
    }

    /**
    * 创建时间
    */
    public Date getCreatetime(){
    return this.createtime;
    }

    /**
    * 0 ：冻结，1：正常
    */
    public Integer getStatus(){
    return this.status;
    }

    /**
    * 角色id
    */


}
