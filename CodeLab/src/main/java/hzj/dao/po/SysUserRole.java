package hzj.dao.po;

public class SysUserRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.user_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.role_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.is_show
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Boolean isShow;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role.id
     *
     * @return the value of sys_user_role.id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role.id
     *
     * @param id the value for sys_user_role.id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role.user_id
     *
     * @return the value of sys_user_role.user_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role.user_id
     *
     * @param userId the value for sys_user_role.user_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role.role_id
     *
     * @return the value of sys_user_role.role_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role.role_id
     *
     * @param roleId the value for sys_user_role.role_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role.is_show
     *
     * @return the value of sys_user_role.is_show
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role.is_show
     *
     * @param isShow the value for sys_user_role.is_show
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
}