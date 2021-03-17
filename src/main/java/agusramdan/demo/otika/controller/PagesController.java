package agusramdan.demo.otika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "pages")
public class PagesController {

    @GetMapping(value = "{pageName}")
    public ModelMap mmDashboard(String pageName) {

        return new ModelMap();
    }

}
