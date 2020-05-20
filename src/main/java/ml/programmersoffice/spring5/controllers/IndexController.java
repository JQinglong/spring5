package ml.programmersoffice.spring5.controllers;

import ml.programmersoffice.spring5.services.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired private NameService service; 

    @RequestMapping({ "/", "/index" })
    public ModelAndView get(ModelAndView mav) {
        mav.addObject("names", service.getAll()); 
        mav.setViewName("index");
        return mav;
    }
}