package hzj.service;

import hzj.dao.po.SysUserRole;
import hzj.page.PageDataDTO;
import hzj.pojo.Role;
import hzj.query.UserRoleQueryDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by MichaelHe on 2016/5/22.
 */
public interface RoleService {


    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();

    public PageDataDTO queryUserRole(UserRoleQueryDTO userRoleQueryDTO);
    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);
}
