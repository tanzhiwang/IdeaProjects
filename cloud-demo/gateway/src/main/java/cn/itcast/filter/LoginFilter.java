package cn.itcast.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//就是"pre"
    }
    @Override//通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高。
    public int filterOrder() {//处理请求头之前，请求参数处理完再拦截
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;//5-1=4
    }
    @Override
    public boolean shouldFilter() {
        return true;//是否过滤，选择true则过滤
    }
    /**
     * 登录拦截的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //0.获取请求上下文,zuul的request的作用域是从请求到达zuul一直到路由结束返回到客户端的完整流程
        RequestContext ctx=RequestContext.getCurrentContext();
        //注意：request是tomcat构建的
        //1.获取request
        HttpServletRequest request = ctx.getRequest();
        //2.获取请求参数access-token
        String token = request.getParameter("access-token");
        //3.判断所规定的参数是否存在
        //if(token==null||token.trim().isEmpty())//过于麻烦，用StringUtils
        //trim是截取字符串形成新字符串，整个过程会在内存中出现多个字符串效率极差，容易引起内存溢出（OM）
        if(StringUtils.isBlank(token)){
            //4.若不存在，认为未登录，拦截
            ctx.setSendZuulResponse(false);//true放行，false拦截
            //返回403
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }

        return null;
    }
}
