package hzj.service.impl;


import hzj.dao.mapper.SysResourceMapper;
import hzj.dao.mapper.SysRoleResourcePermissionMapper;
import hzj.dao.mapper.SysUserRoleMapper;
import hzj.dao.po.SysResource;
import hzj.dao.po.SysResourceExample;
import hzj.pojo.Menu;
import hzj.pojo.Resource;
import hzj.pojo.User;
import hzj.service.PermissionService;
import hzj.service.ResourceService;
import hzj.service.RoleService;
import hzj.service.UserAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by MichaelHe on 2016/5/22.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleResourcePermissionMapper sysRoleResourcePermissionMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public Resource createResource(Resource resource) {
        sysResourceMapper.insert(toEntity(resource));
        return resource;
    }

    @Override
    public Resource updateResource(Resource resource) {
        SysResourceExample sysResourceExample = new SysResourceExample();
        sysResourceExample.createCriteria().andIdEqualTo(resource.getId());
        sysResourceMapper.updateByExampleSelective(toEntity(resource),sysResourceExample);
        return resource;
    }

    @Override
    public void deleteResource(Long resourceId) {
        SysResourceExample sysResourceExample = new SysResourceExample();
        sysResourceExample.createCriteria().andIdEqualTo(resourceId);
        sysResourceMapper.deleteByExample(sysResourceExample);
    }

    @Override
    public Resource findOne(Long resourceId) {
        SysResource sysResource = sysResourceMapper.selectByPrimaryKey(resourceId);
        if(sysResource!=null)
            return toPojo(sysResourceMapper.selectByPrimaryKey(resourceId));
        return null;
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = new ArrayList<Resource>();
        SysResourceExample sysResourceExample = new SysResourceExample();
        sysResourceExample.createCriteria().andIdNotEqualTo(Long.parseLong("0"));
        List<SysResource> sysResources = sysResourceMapper.selectByExample(sysResourceExample);
        for(int i=0;i<sysResources.size();i++){
            resources.add(toPojo(sysResources.get(i)));
        }
        return resources;
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<Menu> findMenus(User user) {
        List<Resource> allResources = findAll();
        Set<String> userPermissions = userAuthService.findStringPermissions(user);


        Iterator<Resource> iter = allResources.iterator();
        while (iter.hasNext()) {
            if (!hasPermission(iter.next(), userPermissions)) {
                iter.remove();
            }
        }
        /*List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
            *//*if(resource.isRootNode()) {
                continue;
            }*//*
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }*/
        return convertToMenus(allResources);
    }










    private boolean hasPermission(Resource resource, Set<String> userPermissions) {
        if(resource.getId().toString().equals("1")){
            return true;
        }
        String actualResourceIdentity = findActualResourceIdentity(resource);
        /*if (StringUtils.isEmpty(actualResourceIdentity)) {
            return true;
        }*/

        for (String permission : userPermissions) {
            if (hasPermission(permission, actualResourceIdentity)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasPermission(String permission, String actualResourceIdentity) {

        //得到权限字符串中的 资源部分，如a:b:create --->资源是a:b
        String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));

        //如果权限字符串中的资源 是 以资源为前缀 则有权限 如a:b 具有a:b的权限
        if(permissionResourceIdentity.startsWith(actualResourceIdentity)) {
            return true;
        }

        return true;
      /*  //模式匹配
        WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
        WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

        return p1.implies(p2) || p2.implies(p1);*/
    }

    /**
     * 得到真实的资源标识  即 父亲:儿子
     * @param resource
     * @return
     */
    @Override
    public String findActualResourceIdentity(Resource resource) {

        if(resource == null) {
            return null;
        }

        StringBuilder s = new StringBuilder(resource.getIdentity());

        boolean hasResourceIdentity = !StringUtils.isEmpty(resource.getIdentity());

        Resource parent = findOne(resource.getParentId());
        while(parent != null) {
            if(!StringUtils.isEmpty(parent.getIdentity().trim())) {
                s.insert(0, parent.getIdentity() + ":");
                hasResourceIdentity = true;
            }
            parent = findOne(parent.getParentId());
        }

        //如果用户没有声明 资源标识  且父也没有，那么就为空
        if(!hasResourceIdentity) {
            return "";
        }


        //如果最后一个字符是: 因为不需要，所以删除之
        int length = s.length();
        if(length > 0 && s.lastIndexOf(":") == length - 1) {
            s.deleteCharAt(length - 1);
        }

        //如果有儿子 最后拼一个*
        boolean hasChildren = false;
        for(Resource r : findAll()) {
            if(resource.getId().equals(r.getParentId())) {
                hasChildren = true;
                break;
            }
        }
        if(hasChildren) {
            s.append(":*");
        }

        return s.toString();
    }


   /* private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }*/

    public SysResource toEntity(Resource resource){
        SysResource sysResource = new SysResource();
        sysResource.setId(resource.getId());
        sysResource.setAvailable(resource.getAvailable());
        sysResource.setName(resource.getName());
        sysResource.setParentId(resource.getParentId());
        sysResource.setParentIds(resource.getParentIds());
        sysResource.setType(resource.getType().getInfo());
        sysResource.setUrl(resource.getUrl());
        sysResource.setIcon(resource.getIcon());
        sysResource.setIdentity(resource.getIdentity());
        return sysResource;

    }

    public Resource toPojo(SysResource sysResource){
        Resource resource = new Resource();
        resource.setId(sysResource.getId());
        resource.setAvailable(sysResource.getAvailable());
        resource.setName(sysResource.getName());
        resource.setParentId(sysResource.getParentId());
        resource.setParentIds(sysResource.getParentIds());
        resource.setUrl(sysResource.getUrl());
        resource.setIcon(sysResource.getIcon());
        resource.setIdentity(sysResource.getIdentity());
        return resource;

    }


    public static List<Menu> convertToMenus(List<Resource> resources) {

        if (resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        //  Menu root = convertToMenu(resources.remove(resources.size() - 1));
        Menu root = convertToMenu(resources.get(0));
        recursiveMenu(root, resources);
        List<Menu> menus = root.getChildren();
        //removeNoLeafMenu(menus);

        return menus;
    }


    private static void removeNoLeafMenu(List<Menu> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

    private static void recursiveMenu(Menu menu, List<Resource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
            Resource resource = resources.get(i);
            if (resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (Menu subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }

    private static Menu convertToMenu(Resource resource) {
        return new Menu(resource.getId(), resource.getName(), resource.getUrl(), resource.getIcon());
    }
}
