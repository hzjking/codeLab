package hzj.interceptor;

import hzj.respCode.RespCode;
import hzj.service.UserService;
import hzj.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(MyInterceptor.class);
	
	@Autowired
	UserService userService;

	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
	 * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		int userid = 0;
		if(StringUtils.isNotBlank(request.getHeader("huserid"))){
			userid = Integer.parseInt(request.getHeader("huserid"));
		}
		String token = request.getHeader("htoken");
		String uuid = request.getHeader("huuid");




		// 验证是否是合法的登陆用户
		if (userid == 0
				|| token == null
				|| "".equals(token)
				|| !userService.checkTokenByAccount(userid, token, uuid)) {
			//*logger.warn("invalid request uri：" + request.getRequestURI());*//
			StringBuffer result = new StringBuffer();
			result.append("{\"resultCode\":\"").append(RespCode.SESSION_INVALID)
					.append("\",\"resultMsg\":\"").append(RespCode.getCodeDesc(RespCode.SESSION_INVALID))
					.append("\"}");
			response.setStatus(200);
			response.getOutputStream().write(
					result.toString().getBytes("UTF-8"));
			return false;
		}

		return true;
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		response.setStatus(200);
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// /**
		// * 如果发生有错误码的异常, 则根据错误码生成json错误包返回给前台
		// */
		// if(ex instanceof ErrCodeException){
		// StringBuffer result = new StringBuffer();
		// result.append("{\"resultCode\":\"").append(ex.getMessage())
		// .append("\",\"resultMsg\":\"").append(RespCode.getCodeDesc(ex.getMessage())).append("\"}");
		// ex = null;
		// response.setStatus(200);
		// response.getOutputStream().write(result.toString().getBytes("UTF-8"));
		// }
		// handler = null;

	}

	/*public static void main(String[] args) throws Exception {
		String a = "%E4%B8%8A%E6%B5%B7%25%E4%B8%8A%E6%B5%B7%E5%B8%82%24%E5%8D%A2%E6%B9%BE%E5%8C%BA%25%E4%B8%89%E9%87%8C%E5%B1%AF";
		System.out.println(URLDecoder.decode(a, "utf-8"));
	}*/

}
