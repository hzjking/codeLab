package hzj.controller.base;

import com.alibaba.fastjson.JSON;
import hzj.exception.ErrCodeException;
import hzj.respCode.RespCode;
import hzj.utils.JsonMapper;
import hzj.vo.base.BaseVO;
import hzj.vo.base.ResponseVO;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/1/7.
 */
public class BaseController {

    private final Logger logger = Logger.getLogger(BaseController.class);




    private String viewPrefix;








    public String Obj2JSON(BaseVO baseVO) {
        /**
         * 因未按初期设计的代码coding, 导致无法使用通过OO来完成这一步的api编号的全局化,
         * 所以使用目前这种强侵入性的方式过度一下
         */
     //   String apiCode = baseVO.getApiCode();
        baseVO.setApiCode(null);

        ResponseVO<BaseVO> vo = new ResponseVO<BaseVO>();
        vo.setBody(baseVO);
        vo.setResultCode(RespCode.SUCCESS);
        vo.setResultMsg(RespCode.getCodeDesc(RespCode.SUCCESS));
        logger.info("响应====>\r\n" + JSON.toJSONString(vo, false));


        return JSON.toJSONString(vo);
    }


    @ExceptionHandler
    @ResponseBody
    public void exception(HttpServletRequest request, HttpServletResponse response,
                          Exception ex) throws IOException {
        StringBuffer result = new StringBuffer();

        /*ServletOutputStream sos = response.getOutputStream();*/
//        PrintWriter sos=response.getWriter();
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        ServletOutputStream sos = response.getOutputStream();


        try {

            if (ex instanceof ErrCodeException) {


                result.append("{\"resultCode\":\"").append(ex.getMessage())
                        .append("\",\"resultMsg\":\"")
                        .append(RespCode.getCodeDesc(ex.getMessage()))
                        .append("\"}");

            } else {
                logger.error(ex.getMessage(), ex);
                result.append("{\"resultCode\":\"").append(RespCode.UNKNOWN_ERROR)
                        .append("\",\"resultMsg\":\"").append("服务中断,请稍后再试").append("\"}");
            }
            logger.info("error return====>\r\n" + result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
           // System.out.println(getEncoding(result.toString()));
            sos.write(result.toString().getBytes("utf-8"));
            sos.flush();
            sos.close();
        }

    }


    /**
     * 添加Model消息
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * 客户端返回JSON字符串
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, JsonMapper.toJsonString(object), "application/json");
    }

    /**
     * 客户端返回字符串
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * 授权登录异常
     */
    @ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {
        return "error/403";
    }







}
