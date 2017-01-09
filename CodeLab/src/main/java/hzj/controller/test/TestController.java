package hzj.controller.test;

import hzj.controller.base.BaseController;
import hzj.vo.test.WeixinVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MichaelHe on 2016/11/21.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{

    @RequestMapping(value = "/weixin")
    public String weixin(HttpServletRequest request, ModelMap model) {
        String test1 = request.getParameter("test1");
        WeixinVo weixinVo = new WeixinVo();
        weixinVo.setMsg("this is msg");
        return this.Obj2JSON(weixinVo);
    }


}
