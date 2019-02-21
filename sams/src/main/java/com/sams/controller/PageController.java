package com.sams.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Value("${static-project-url}")
    private String url;

    @GetMapping("/{page}")
    public void toPage(@PathParam("page") String page, HttpServletResponse response) throws IOException {
        response.sendRedirect(url+"view/"+page);
    }
}
