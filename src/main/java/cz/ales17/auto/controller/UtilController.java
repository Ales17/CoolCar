package cz.ales17.auto.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Controller
public class UtilController {
    @RequestMapping(value = "/robots.txt")
    public void robots(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/plain");
            response.getWriter().write("User-agent: *\nDisallow: /\n");
        } catch (IOException e) {
            //CustomLogger.info(TAG, "robots", "robots(): "+e.getMessage());
        }
    }
}
