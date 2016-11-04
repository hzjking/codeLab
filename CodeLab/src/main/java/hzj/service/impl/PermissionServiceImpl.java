package hzj.service.impl;


import hzj.dao.mapper.SysPermissionMapper;
import hzj.dao.po.SysPermission;
import hzj.pojo.Permission;
import hzj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MichaelHe on 2016/5/22.
 */
@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public Permission findOne(Long permissionId) {

        return toPojo(sysPermissionMapper.selectByPrimaryKey(permissionId));
    }

    private Permission toPojo(SysPermission sysPermission){
        Permission permission = new Permission();

        permission.setShow(sysPermission.getIsShow());
        permission.setDescription(sysPermission.getDescription());
        permission.setId(sysPermission.getId());
        permission.setName(sysPermission.getName());
        permission.setPermission(sysPermission.getPermission());

        return  permission;
    }
}
