package ml.programmersoffice.spring5.controllers;

import ml.programmersoffice.spring5.services.NameService; // ←追加

import org.springframework.beans.factory.annotation.Autowired; // ←追加
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {
    @Autowired private NameService service; // ←追加

    @RequestMapping({ "/calendar" })
    public ModelAndView get(ModelAndView mav) {
        mav.addObject("names", service.getAll()); // ←追加
        mav.setViewName("/app/calendar");
        return mav;
    }
}