package hzj.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hzj.annotation.RequestModel;
import hzj.exception.ErrCodeException;
import hzj.respCode.RespCode;
import hzj.vo.base.BaseVO;
import hzj.vo.base.HeadVO;
import hzj.vo.base.RequestVO;
import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MyHandlerMethodArgumentResolver implements
        HandlerMethodArgumentResolver {

	private Logger logger = Logger.getLogger(MyHandlerMethodArgumentResolver.class);
	
	private static final String HEAD = "head";
	private static final String BODY = "body";
	private static final String PARAM = "p";

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(RequestModel.class);
	}

	public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
		Type genType = parameter.getGenericParameterType();
		// 获取RequestModel中的绑定参数的泛型
		@SuppressWarnings("unchecked")
		Class<BaseVO> clazz = (Class<BaseVO>) ((ParameterizedType) genType)
				.getActualTypeArguments()[0];
		String requestP = webRequest.getParameter(PARAM);
		HeadVO headVO = null;
		BaseVO baseVO = null;
		//logger.info("request content == > " + requestP);
		try {
			JSONObject object = JSON.parseObject(requestP);

			if (object != null) {
				//logger.info("请求====>\r\n" + JSON.toJSONString(object, false));
				JSONObject head = object.getJSONObject(HEAD);
				JSONObject body = object.getJSONObject(BODY);
				headVO = JSON.parseObject(head.toJSONString(), HeadVO.class);
				baseVO = JSON.parseObject(body.toJSONString(), clazz);
			} else {
				// TODO 输出日志
				throw new ErrCodeException(RespCode.PARAM_INVALID);
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ErrCodeException(RespCode.REQUEST_INVALID);
		}

		RequestVO<BaseVO> requestVO = new RequestVO<BaseVO>();
		requestVO.setHead(headVO);
		requestVO.setBody(baseVO);

		return requestVO;
	}

}
