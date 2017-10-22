package com.zjtyd.tms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-23
 * Time: 00:32
 * Description:
 */
@WebFilter(filterName = "encodingFilter",urlPatterns = {"/*"})
public class EncodingFilter implements Filter
{

    public EncodingFilter()
    {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy()
    {

    }
}
