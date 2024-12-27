package cz.ales17.auto.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object requestStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String requestMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (requestStatus != null) {
            int statusCode = Integer.parseInt(requestStatus.toString());
            model.addAttribute("status", statusCode);
            model.addAttribute("message", requestMessage);
        }
        return "custom-error";
    }


}
