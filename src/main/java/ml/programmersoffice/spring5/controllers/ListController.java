package ml.programmersoffice.spring5.controllers;

// import java.util.ArrayList;
// import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ml.programmersoffice.spring5.common.*;
import ml.programmersoffice.spring5.services.MusicService;
import ml.programmersoffice.spring5.common.PagenationHelper;
import ml.programmersoffice.spring5.entities.MusicEntity;

@Controller
@SessionAttributes(value = "form")
public class ListController {

    @Autowired
    MusicService mService;

    @Autowired
    HttpSession session;

    private static final String SESSION_FORM_ID = "searchForm";

    @RequestMapping({ "/list" })
    public ModelAndView get(ModelAndView mav) {
        mav.addObject("musics", mService.getAll());
        mav.setViewName("app/list");
        return mav;
    }

    @RequestMapping(value = "/listform", method = RequestMethod.GET)
    public ModelAndView get(Model model) {
        return search(new SearchForm(), model);
    }

    @RequestMapping(value = "/pagenate", method = RequestMethod.GET)
    public ModelAndView pagenate(@RequestParam("page") int page, Model model) {
        SearchForm form = (SearchForm) session.getAttribute(SESSION_FORM_ID);
        form.setPage(page);
        return search(form, model);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute SearchForm form, Model model) {
        session.setAttribute(SESSION_FORM_ID, form);
        // List<String> lyrics = new ArrayList<String>();
        String lyrics;
        // if (!form.getLyrics().isEmpty())
        //     lyrics.add(form.getLyrics());
        lyrics = form.getLyrics();
        Page<MusicEntity> musics = mService.search(form.getPage(), lyrics);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("app/listform");
        mv.addObject("url", "/pagenate");
        mv.addObject("form", form);
        mv.addObject("musiclist", musics);
        mv.addObject("page", PagenationHelper.createPagenation(musics));
        return mv;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, Model model) {
        MusicEntity mEntity = mService.findOne(id);
        model.addAttribute("music", mEntity);
        return "music/show";
    }
}