package jsonObjectMappers;

/**
 * RDP REST ENDPOINTS
 * Created by G538807 on 25/04/2016.
 */
class UserRole {
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

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    private Integer id;
    private String createDate;
    private String updateDate;
    private String self;
    private String name;
    private String description;
}
