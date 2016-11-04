package hzj.controller.user;

import com.alibaba.fastjson.JSON;

import hzj.constants.Constants;
import hzj.controller.base.BaseController;
import hzj.page.PageDataDTO;
import hzj.pojo.Menu;
import hzj.pojo.Role;
import hzj.pojo.User;
import hzj.query.UserQueryDTO;
import hzj.query.UserRoleQueryDTO;
import hzj.service.ResourceService;
import hzj.service.RoleService;
import hzj.service.UserService;
import hzj.utils.Utils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by MichaelHe on 2015/6/11.
 * 用户信息相关类
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ResourceService resourceService;


    /**
     * 管理登录
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, ModelMap model) {

     //   User user = (User)request.getAttribute(Constants.CURRENT_USER);
        User user = userService.findByUsername("admin");
        List<Menu> menus = resourceService.findMenus(user);
        model.addAttribute("menus", menus);
        model.addAttribute("username",user.getUsername());
        return "sys/home";


        //表示退出
        /*if (!StringUtils.isEmpty(request.getParameter("logout"))) {
            model.addAttribute(Constants.MESSAGE, messageSource.getMessage("user.logout.success", null, null));
        }*/

        //表示用户删除了 @see org.apache.shiro.web.filter.user.SysUserFilter
        /*if (!StringUtils.isEmpty(request.getParameter("notfound"))) {
            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.notfound", null, null));
        }*/

        //表示用户被管理员强制退出
        /*if (!StringUtils.isEmpty(request.getParameter("forcelogout"))) {
            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.forcelogout", null, null));
        }*/

        //表示用户输入的验证码错误
        /*if (!StringUtils.isEmpty(request.getParameter("jcaptchaError"))) {
            model.addAttribute(Constants.ERROR, messageSource.getMessage("jcaptcha.validate.error", null, null));
        }*/


        //表示用户锁定了 @see org.apache.shiro.web.filter.user.SysUserFilter
        /*if (!StringUtils.isEmpty(request.getParameter("blocked"))) {
            User user = (User) request.getAttribute(Constants.CURRENT_USER);
            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.blocked", null, null));
        }

        if (!StringUtils.isEmpty(request.getParameter("unknown"))) {
            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.unknown.error", null, null));
        }*/

        //登录失败了 提取错误消息
        /*String shiroLoginFailureEx = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (shiroLoginFailureEx != null) {
            model.addAttribute(Constants.ERROR, shiroLoginFailureEx);
        }*/

        //如果用户直接到登录页面 先退出一下
        //原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
        // 这样会导致登录一直死循环
       /* Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }*/


        //如果同时存在错误消息 和 普通消息  只保留错误消息
        /*if (model.containsAttribute(Constants.ERROR)) {
            model.remove(Constants.MESSAGE);
        }*/


        // model.addAttribute("error", error);
       // return "sys/login";
    }


    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

        System.out.println("login");

        return "sys/login";
    }*/


    /*@RequiresPermissions("user")
    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String home(HttpServletRequest request, HttpServletResponse response){
        System.out.println("loginSuccess");
        return "sys/home";

    }*/

    /**
     * 用户列表
     */
    @RequestMapping(value = "/user_view")
    public String userView(HttpServletRequest request, ModelMap model) {

        /*List<User> users = userService.findAll();
        model.addAttribute("users", users);*/


        UserQueryDTO userQueryDTO = new UserQueryDTO();
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null && !StringUtils.isEmpty(pageNo)) {
            userQueryDTO.setPageNo(Integer.parseInt(pageNo));
        }
        PageDataDTO pageDataDTO = userService.queryUser(userQueryDTO);
        List<User> users = (ArrayList<User>) pageDataDTO.getData();
        model.addAttribute("users", users);
        model.addAttribute("page", userQueryDTO);
       /* Pageable pageable = new Pageable();
        Page<User> userPage = new PageImpl<User>(users,searchable,userQueryDTO.getTotalPage());
        model.addAttribute("page",userQueryDTO);*/
        return "user/userView";
    }

    /**
     * 用户查询列表（根据所选条件条件）
     */

    @RequestMapping("user_queryList")
    public String userQueryList(HttpServletRequest request, ModelMap model) throws Exception {
        String username = request.getParameter("userName");
        String userphone = request.getParameter("userPhone");
        String starttime = request.getParameter("startTime");
        String endtime = request.getParameter("endTime");
        String status = request.getParameter("userStatus");

        if (StringUtils.isEmpty(starttime) || StringUtils.isEmpty(endtime) || StringUtils.isEmpty(status)) {
            String errInfo = "查询条件未能满足，请检查必选项！";
            System.out.println(errInfo);
            return "error/404";
        }
        if (!StringUtils.isEmpty(username) && Utils.userNameReg(username)) {
            String errInfo = "用户名只能以字母、数字、下划线";
            System.out.println(errInfo);
            return "error/404";
        }
        if ( !StringUtils.isEmpty(userphone) && Utils.userPhoneReg(userphone)) {
            String errInfo = "请检查手机号是否填写正确！";
            System.out.println(errInfo);
            return "error/404";
        }
        String oldStartTime=starttime;
        String oldEndTime=endtime;
        starttime = Utils.timeSplit(starttime);
        endtime = Utils.timeSplit(endtime);
        int result = starttime.compareTo(endtime);
        if (result >= 0) {
            String errInfo = "开始时间不能大于结束时间！";
            return "error/404";
        }
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null && !StringUtils.isEmpty(pageNo)) {
            userQueryDTO.setPageNo(Integer.parseInt(pageNo));
        }

        userQueryDTO.setStarttime(starttime);
        userQueryDTO.setEndtime(endtime);
        userQueryDTO.setUserstatus(status);
        List<User> users = null;
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(userphone)) {
            users = userService.queryUserList(userQueryDTO);

        }

        if (!StringUtils.isEmpty(username) && StringUtils.isEmpty(userphone) && !Utils.userNameReg(username)) {
            userQueryDTO.setUsername(username);
            users = userService.queryUserNameList(userQueryDTO);
        }

        if (!StringUtils.isEmpty(userphone) && StringUtils.isEmpty(username) && !Utils.userPhoneReg(userphone)) {
            userQueryDTO.setMobilePhoneNumber(userphone);
            users = userService.queryUserPhoneList(userQueryDTO);
        }
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userphone) && !Utils.userNameReg(username) && !Utils.userPhoneReg(userphone)) {
            userQueryDTO.setUsername(username);
            userQueryDTO.setMobilePhoneNumber(userphone);
            users = userService.queryUserNamePhoneList(userQueryDTO);
        }

        model.addAttribute("users", users);
        model.addAttribute("page", userQueryDTO);
        return "user/userView";

    }


    /**
     *角色列表
     */
    @RequestMapping(value ="/user_role_view")
    public String userRoleView(HttpServletRequest request, ModelMap model) throws Exception {

        UserRoleQueryDTO userRoleQueryDTO = new UserRoleQueryDTO();
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null && !StringUtils.isEmpty(pageNo)) {
            userRoleQueryDTO.setPageNo(Integer.parseInt(pageNo));
        }
        PageDataDTO pageDataDTO = roleService.queryUserRole(userRoleQueryDTO);
        List<Role> roles = (ArrayList<Role>) pageDataDTO.getData();
        model.addAttribute("roles", roles);
        model.addAttribute("page", userRoleQueryDTO);
        return "user/insertUserRole";
    }

}

