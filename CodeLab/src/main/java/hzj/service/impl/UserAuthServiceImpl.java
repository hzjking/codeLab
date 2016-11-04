package hzj.service.impl;


import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import hzj.dao.mapper.SysRoleResourcePermissionMapper;
import hzj.dao.mapper.SysUserRoleMapper;
import hzj.dao.po.SysRoleResourcePermission;
import hzj.dao.po.SysRoleResourcePermissionExample;
import hzj.dao.po.SysUserRole;
import hzj.dao.po.SysUserRoleExample;
import hzj.pojo.*;
import hzj.service.PermissionService;
import hzj.service.ResourceService;
import hzj.service.RoleService;
import hzj.service.UserAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by MichaelHe on 2016/5/24.
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    ResourceService resourceService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    SysRoleResourcePermissionMapper sysRoleResourcePermissionMapper;

    @Autowired
    RoleService roleService;



    @Override
    public Set<String> findStringRoles(User user) {
        Set<Role> roles = findRoles(user);
        return Sets.newHashSet(Collections2.transform(roles, new Function<Role, String>() {
            @Override
            public String apply(Role input) {
                return input.getRole();
            }
        }));
    }

    @Override
    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public Set<String> findStringPermissions(User user) {
        Set<String> permissions = Sets.newHashSet();

        Set<Role> roles = findRoles(user);
        for (Role role : roles) {
            for (RoleResourcePermission rrp : role.getResourcePermissions()) {

                Resource resource = resourceService.findOne(rrp.getResourceId());

                String actualResourceIdentity = resourceService.findActualResourceIdentity(resource);

                //不可用 即没查到 或者标识字符串不存在
                if (resource == null || StringUtils.isEmpty(actualResourceIdentity.trim())) {
                    continue;
                }

                for (Long permissionId : rrp.getPermissionIds()) {
                    Permission permission = permissionService.findOne(permissionId);

                    //不可用
                    if (permission == null || Boolean.FALSE.equals(permission.getShow())) {
                        continue;
                    }
                    permissions.add(actualResourceIdentity + ":" + permission.getPermission());

                }
            }

        }

        return permissions;
    }



    public Set<Role> findRoles(User user) {

        if (user == null) {
            return Sets.newHashSet();
        }

        Long userId = user.getId();
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(userId);

        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        SysRoleResourcePermissionExample sysRoleResourcePermissionExample = new SysRoleResourcePermissionExample();
        sysRoleResourcePermissionExample.createCriteria().andRoleIdEqualTo(sysUserRoles.get(0).getRoleId());

        List<SysRoleResourcePermission> sysRoleResourcePermissions = sysRoleResourcePermissionMapper.selectByExample(sysRoleResourcePermissionExample);
        Set<Long> roleIds = Sets.newHashSet();
        for(SysRoleResourcePermission sysRoleResourcePermission : sysRoleResourcePermissions){
            roleIds.add(sysRoleResourcePermission.getRoleId());
        }

        Set<Role> roles = findShowRoles(roleIds);

        return roles;

    }


    public Set<Role>  findShowRoles(Set<Long> roleIds) {

        Set<Role> roles = Sets.newHashSet();
        SysRoleResourcePermissionExample sysRoleResourcePermissionExample = new SysRoleResourcePermissionExample();

        //TODO 如果角色很多 此处应该写查询
        for (Role role : roleService.findAll()) {
            if (Boolean.TRUE.equals(role.getShow()) && roleIds.contains(role.getId())) {
                sysRoleResourcePermissionExample.createCriteria().andRoleIdEqualTo(role.getId());
                List<SysRoleResourcePermission> sysRoleResourcePermissions = sysRoleResourcePermissionMapper.selectByExample(sysRoleResourcePermissionExample);
                role.setResourcePermissions(toListPojo(sysRoleResourcePermissions));
                roles.add(role);
            }
        }
        return roles;
    }

    private List<RoleResourcePermission> toListPojo(
            List<SysRoleResourcePermission> sysRoleResourcePermissions){

        List<RoleResourcePermission> roleResourcePermissions = new ArrayList<RoleResourcePermission>();
        for(SysRoleResourcePermission sysRoleResourcePermission: sysRoleResourcePermissions){
            roleResourcePermissions.add(toPojo(sysRoleResourcePermission));
        }
        return roleResourcePermissions;
    }

    private RoleResourcePermission toPojo(SysRoleResourcePermission sysRoleResourcePermission){
        RoleResourcePermission roleResourcePermission = new RoleResourcePermission();
        roleResourcePermission.setId(sysRoleResourcePermission.getId());
        Set<Long> ids = Sets.newHashSet();
        String perIds = sysRoleResourcePermission.getPermissionIds();
        if(perIds.contains(",")){
            for(String id:perIds.split(",")){
                ids.add(Long.parseLong(id));
            }
        }else{
            ids.add(Long.parseLong(perIds));
        }
        roleResourcePermission.setPermissionIds(ids);
        roleResourcePermission.setResourceId(sysRoleResourcePermission.getResourceId());
        return  roleResourcePermission;
    }
}
