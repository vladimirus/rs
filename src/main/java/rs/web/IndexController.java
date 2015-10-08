package rs.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
    @Value("${rs.static.js.version:1}")
    private Integer jsVersion;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("jsVersion", jsVersion);
        return "index";
    }
}
