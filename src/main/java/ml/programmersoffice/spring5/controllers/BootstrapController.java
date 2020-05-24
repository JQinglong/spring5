package ml.programmersoffice.spring5.controllers;

import ml.programmersoffice.spring5.services.NameService; // ←追加

import org.springframework.beans.factory.annotation.Autowired; // ←追加
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BootstrapController {
    @Autowired private NameService service; // ←追加

    @RequestMapping({ "/bootstrap" })
    public ModelAndView get(ModelAndView mav) {
        mav.addObject("names", service.getAll()); // ←追加
        mav.setViewName("bootstrap");
        return mav;
    }
}
