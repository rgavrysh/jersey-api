package jsonObjectMappers;

import java.util.List;

/**
 * RDP REST ENDPOINTS
 * Created by G538807 on 25/04/2016.
 */
public class LoginUser {
    public Integer getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getSelf() {
        return self;
    }

    public String getName() {
        return name;
    }

    public String getPid() {
        return pid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    private Integer id;
    private String createDate;
    private String updateDate;
    private String self;
    private String name;
    private String pid;

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    private List<UserRole> roles;
}
