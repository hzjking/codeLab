package hzj.service.impl;

import hzj.dao.base.PageQueryDAO;
import hzj.dao.mapper.SysRoleMapper;
import hzj.dao.po.SysRole;
import hzj.dao.po.SysRoleExample;
import hzj.page.PageDataDTO;
import hzj.pojo.Role;
import hzj.query.UserRoleQueryDTO;
import hzj.service.ResourceService;
import hzj.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by MichaelHe on 2016/5/22.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ResourceService resourceService;
    @Resource
    private PageQueryDAO pageQueryDAO;

    public Role createRole(Role role) {
        sysRoleMapper.insert(toEntity(role));
        return role;
    }

    public Role updateRole(Role role) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdEqualTo(role.getId());
        sysRoleMapper.updateByExampleSelective(toEntity(role), sysRoleExample);
        return role;
    }

    public void deleteRole(Long roleId) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdEqualTo(roleId);
        sysRoleMapper.deleteByExample(sysRoleExample);
    }

    @Override
    public Role findOne(Long roleId) {
        return toPojo(sysRoleMapper.selectByPrimaryKey(roleId));
    }

    @Override
    public List<Role> findAll() {
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdNotEqualTo(Long.parseLong("0"));
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<Role> roles = new ArrayList<Role>();
        for(int i=0;i<sysRoles.size();i++){
            roles.add(toPojo(sysRoles.get(i)));
        }
        return roles;
    }
    @Override
    public PageDataDTO queryUserRole(UserRoleQueryDTO userRoleQueryDTO){
        PageDataDTO pageData = null;

        pageData = pageQueryDAO.query("com.sand.selfMapper.QueryUserRoleMapper.selectAllUserRole", userRoleQueryDTO);

        return pageData;
    }
    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    /*@Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }*/

    public Role toPojo(SysRole sysRole){
        Role role = new Role();
        role.setDescription(sysRole.getDescription());
        role.setId(sysRole.getId());
        role.setRole(sysRole.getRole());
        role.setName(sysRole.getName());
        role.setShow(sysRole.getIsShow());
        return role;
    }

    public SysRole toEntity(Role role){
        SysRole sysRole = new SysRole();
        sysRole.setDescription(role.getDescription());
        sysRole.setId(role.getId());
        sysRole.setRole(sysRole.getRole());
        sysRole.setIsShow(role.getShow());
        sysRole.setName(role.getName());
        return sysRole;
    }

}
