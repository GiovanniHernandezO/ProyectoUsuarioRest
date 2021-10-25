package com.ejemplo.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterValidation implements Filter {
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        ((HttpServletResponse) sr1).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) sr1).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) sr1).addHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, authorization");

        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        fc.doFilter(sr, sr1);
        return;
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
