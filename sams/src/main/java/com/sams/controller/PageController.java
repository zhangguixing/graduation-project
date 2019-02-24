package com.sams.controller;

import com.sams.constant.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * @Author Guixing
 * @Date 2019/2/21 12:53
 * @Description
 */
@Controller
@RequestMapping("toPage")
public class PageController {

    @GetMapping("/{folder}/{page}")
    public void toPage(@PathParam("folder") String folder,@PathParam("page") String page, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(Constants.STATIC_PROJECT_URL+"view/"+folder+"/"+page).forward(request,response);
    }
}
