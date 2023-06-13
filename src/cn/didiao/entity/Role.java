package cn.didiao.entity;


import java.io.Serializable;


/**
* 
* @TableName role
*/
public class Role implements Serializable {

    /**
    * id
    */

    private Integer id;
    /**
    * 角色名称
    */

    private String name;
    /**
    * 状态
    */
    private Integer status;

    /**
    * id
    */
    public void setId(Integer id){
    this.id = id;
    }

    /**
    * 角色名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 状态
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
    * 角色名称
    */
    public String getName(){
    return this.name;
    }

    /**
    * 状态
    */
    public Integer getStatus(){
    return this.status;
    }

}
