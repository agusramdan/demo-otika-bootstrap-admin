package agusramdan.demo.otika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UnAunthController {

    @GetMapping(value = "/forgot-password-email")
    public ModelMap forgotPasswordEmail(String pageName) {

        return new ModelMap();
    }

}
