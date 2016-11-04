package hzj.service;



import hzj.pojo.Menu;
import hzj.pojo.Resource;
import hzj.pojo.User;

import java.util.List;
import java.util.Set;

/**
 * Created by MichaelHe on 2016/5/22.
 */
public interface ResourceService {


    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param user
     * @return
     */
    List<Menu> findMenus(User user);


    String findActualResourceIdentity(Resource resource);
}
