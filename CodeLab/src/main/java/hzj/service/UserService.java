package hzj.service;



import hzj.dao.po.SysUser;
import hzj.exception.ErrCodeException;
import hzj.page.PageDataDTO;
import hzj.pojo.User;
import hzj.query.UserQueryDTO;

import java.util.List;

/**
 * Created by MichaelHe on 2015/6/8.
 */


public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);


    User findOne(Long userId);

    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    User login(String username, String password);

    User findByEmail(String email);

    User findByMobilePhoneNumber(String mobile);

    public PageDataDTO queryUser(UserQueryDTO userQueryDTO);
    public List<User> queryUserList(UserQueryDTO userQueryDTO) throws Exception;
    public List<User> queryUserNameList(UserQueryDTO userQueryDTO) throws Exception;
    public List<User> queryUserPhoneList(UserQueryDTO userQueryDTO) throws Exception;
    public List<User> queryUserNamePhoneList(UserQueryDTO userQueryDTO) throws Exception;
    public void insertUser(User user) throws Exception;
    public SysUser findBySysUsername(String username);
    public SysUser findBySysUserphone(String userphone);
    public SysUser findBySysUseremail(String useremail);
    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    //public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    //public Set<String> findPermissions(String username);

    public boolean checkTokenByAccount(Integer userid, String token, String uuid) throws ErrCodeException;



}
