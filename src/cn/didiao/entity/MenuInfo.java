package cn.didiao.entity;


import java.io.Serializable;

import java.util.Date;

/**
* 
* @TableName menu_info
*/
public class MenuInfo implements Serializable {

    /**
    * 
    */

    private Integer id;
    /**
    * 
    */

    private String title;
    /**
    * 
    */

    private String href;
    /**
    * 
    */

    private String icon;
    /**
    * 
    */

    private Integer pId;
    /**
    * 1正常 2异常
    */

    private Integer status;
    /**
    * 
    */

    private Date createtime;
    /**
    * 
    */

    private Date updatetime;

    /**
    * 
    */
    public void setId(Integer id){
    this.id = id;
    }

    /**
    * 
    */
    public void setTitle(String title){
    this.title = title;
    }

    /**
    * 
    */
    public void setHref(String href){
    this.href = href;
    }

    /**
    * 
    */
    public void setIcon(String icon){
    this.icon = icon;
    }

    /**
    * 
    */
    public void setPId(Integer pId){
    this.pId = pId;
    }

    /**
    * 1正常 2异常
    */
    public void setStatus(Integer status){
    this.status = status;
    }

    /**
    * 
    */
    public void setCreatetime(Date createtime){
    this.createtime = createtime;
    }

    /**
    * 
    */
    public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
    }


    /**
    * 
    */
    public Integer getId(){
    return this.id;
    }

    /**
    * 
    */
    public String getTitle(){
    return this.title;
    }

    /**
    * 
    */
    public String getHref(){
    return this.href;
    }

    /**
    * 
    */
    public String getIcon(){
    return this.icon;
    }

    /**
    * 
    */
    public Integer getPId(){
    return this.pId;
    }

    /**
    * 1正常 2异常
    */
    public Integer getStatus(){
    return this.status;
    }

    /**
    * 
    */
    public Date getCreatetime(){
    return this.createtime;
    }

    /**
    * 
    */
    public Date getUpdatetime(){
    return this.updatetime;
    }

}
