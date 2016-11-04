package hzj.controller.base;

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

    /*@Autowired
    private ResourceService resourceService;*/
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        User user = (User)request.getAttribute(Constants.CURRENT_USER);
      /*  Set<String> permissions = userService.findPermissions(
                user.getUsername());*/
     //   List<Menu> menus = menuService.getMenuByUser(user.getUserid());
        List<String> tabs = new ArrayList<String>();
        List<List<Menu>> menusList = menuService.getMenuByUser(user.getUsername());
        for(int i=0;i<menusList.size();i++){
            tabs.add(menusList.get(i).get(0).getRootName());
        }
        model.addAttribute("menusList", menusList);
        model.addAttribute("tabs", tabs);
      //  model.addAttribute("menus", menus);
        model.addAttribute("loginid", user.getUsername());
        return "sys/home";
    }


    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        return "sys/welcome";
    }


}
