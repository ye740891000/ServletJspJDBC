package com.zjtyd.tms.filter;

import com.zjtyd.tms.beans.UserAccount;
import com.zjtyd.tms.utils.DBUtils;
import com.zjtyd.tms.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-23
 * Time: 00:15
 * Description:
 */
@WebFilter(filterName = "cookieFilter",urlPatterns = {"/*"})
public class CookierFilter implements Filter
{

    public CookierFilter()
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
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();

        UserAccount userInSession = MyUtils.getLoginedUser(session);

        if(userInSession != null)
        {
            session.setAttribute("COOKIE_CHECKED","CHECKED");
            chain.doFilter(request,response);
            return;
        }

        // Connection was created in JDBCFilter
        Connection conn = MyUtils.getStoredConnection(request);

        //Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");

        if(checked == null && conn != null)
        {
            String userName = MyUtils.getUserNameInCookie(req);
            try
            {
                UserAccount user = DBUtils.findUser(conn, userName);
                MyUtils.storeLoginedUser(session,user);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // Mark checked Cookies.
            session.setAttribute("COOKIE_CHECKED","CHECKED");
        }
        chain.doFilter(request,response);


    }

    @Override
    public void destroy()
    {

    }
}
