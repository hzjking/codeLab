package hzj.dao.po;

public class SysRoleResourcePermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_resource_permission.id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_resource_permission.role_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_resource_permission.resource_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private Long resourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_resource_permission.permission_ids
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    private String permissionIds;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_resource_permission.id
     *
     * @return the value of sys_role_resource_permission.id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_resource_permission.id
     *
     * @param id the value for sys_role_resource_permission.id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_resource_permission.role_id
     *
     * @return the value of sys_role_resource_permission.role_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_resource_permission.role_id
     *
     * @param roleId the value for sys_role_resource_permission.role_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_resource_permission.resource_id
     *
     * @return the value of sys_role_resource_permission.resource_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_resource_permission.resource_id
     *
     * @param resourceId the value for sys_role_resource_permission.resource_id
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_resource_permission.permission_ids
     *
     * @return the value of sys_role_resource_permission.permission_ids
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public String getPermissionIds() {
        return permissionIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_resource_permission.permission_ids
     *
     * @param permissionIds the value for sys_role_resource_permission.permission_ids
     *
     * @mbggenerated Fri Nov 04 11:00:20 CST 2016
     */
    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}