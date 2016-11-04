package hzj.query;


import hzj.page.Page;

/**
 * Created by Administrator on 2016/6/28.
 */
public class UserRoleQueryDTO extends Page<UserQueryDTO> {
    private Long id;
    private String name;
    private String role;
    private String description;
    private Boolean isShow;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isShow() {
        return isShow;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
}
