package com.zjtyd.tms.filter;


import com.zjtyd.tms.conn.ConnectionUtils;
import com.zjtyd.tms.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-22
 * Time: 01:17
 * Description:
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter
{

    public JDBCFilter()
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
        HttpServletRequest req = (HttpServletRequest) request;
        if(this.neeedJDBC(req))
        {
            System.out.println("Open Connection for:"+req.getServletPath());
            Connection conn = null;
            try
            {
                // Create a Connection
                conn = ConnectionUtils.getConnection();

                // Set auto commit to false
                conn.setAutoCommit(false);

                // Store Connection object in attribute of request.
                MyUtils.storeConnection(request,conn);

                // Allow request to go forward.

                //(Go to the next filter or target)
                chain.doFilter(request,response);

                //Invoke the commit() method to complete the transaction the DB
                conn.commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();

                ConnectionUtils.rollbackQuietly(conn);
                throw new ServletException();
            }
            finally
            {
                ConnectionUtils.closeQuietly(conn);
            }
        }
        else
        {
            // Allow request to go forward
            //(Go to the next filter or target)
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy()
    {

    }

    private boolean neeedJDBC(HttpServletRequest request)
    {
        System.out.println("JDBC Filter.");
        /**
         * Servlet Url-pattern:/spath/*
         *
         * spath
         */
        String servletPath = request.getServletPath();

        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;

        if (pathInfo != null)
        {
            // /spath/*
            urlPattern = servletPath + "/*";
        }

        /**
         * Key:servletName
         * Value:ServletRegisration
         */
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                                                                                 .getServletRegistrations();
        /**
         * Collection of all servlet in your Webapp
         */
        Collection<? extends ServletRegistration> values = servletRegistrations.values();

        for (ServletRegistration value : values)
        {
            Collection<String> mappings = value.getMappings();

            if(mappings.contains(urlPattern))
            {
                return true;
            }
        }
        return false;
    }


}
