package hzj.controller.base;

import hzj.constants.Constants;
import hzj.pojo.Menu;
import hzj.pojo.User;
import hzj.service.ResourceService;
import hzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
 //       User user = (User)request.getAttribute(Constants.CURRENT_USER);
        User user = userService.findByUsername("admin");
        List<Menu> menus = resourceService.findMenus(user);
        model.addAttribute("menus", menus);
        model.addAttribute("username",user.getUsername());
        return "sys/home";
    }


    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        return "sys/welcome";
    }


}
