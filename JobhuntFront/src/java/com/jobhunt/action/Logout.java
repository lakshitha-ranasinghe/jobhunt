/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Lakshitha
 */
public class Logout extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest servletRequest;
    
    public Logout() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("user");
        
        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
        String outputfileBuild = filePath+"external\\image\\applicant.jpg";

        Path pathBuild = Paths.get(outputfileBuild);
        Files.deleteIfExists(pathBuild);
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
    
}
