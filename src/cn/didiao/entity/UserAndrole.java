package cn.didiao.entity;

public class UserAndrole {
    private UserInfo userInfo;
    private String role;

    public UserAndrole(UserInfo userInfo, String role) {
        this.userInfo = userInfo;
        this.role = role;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
