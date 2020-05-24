package ml.programmersoffice.spring5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import org.springframework.web.servlet.ModelAndView;

import ml.programmersoffice.spring5.entities.MusicEntity;
import ml.programmersoffice.spring5.services.MusicService;
import ml.programmersoffice.spring5.entities.AlbumEntity;
import ml.programmersoffice.spring5.entities.WordEntity;
import ml.programmersoffice.spring5.entities.HskEntity;

@Controller
@RequestMapping("/musics") 
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping
    public String index(Model model) { 
        List<MusicEntity> musics = musicService.findAll();
        model.addAttribute("musics", musics); 
        return "musics/index"; 
    }

    // @GetMapping({ "/list" })
    // public ModelAndView get(ModelAndView mav) {
    //     mav.addObject("musics", musicService.getAll());
    //     mav.setViewName("musics/index");
    //     return mav;
    // }


    @GetMapping("new")
    public String newMusic(Model model) {
        return "musics/new";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Integer id, Model model) { 
        MusicEntity music = musicService.findOne(id);
        model.addAttribute("music", music);
        return "musics/edit";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Integer id, Model model) {
        MusicEntity music = musicService.findOne(id);
        List<AlbumEntity> albums = musicService.findAlbums(music.getTitle());
        List<WordEntity> words = musicService.findWords(music.getTitle());
        List<HskEntity> hskhanzi = musicService.findHsk(music.getTitle());

        model.addAttribute("music", music);
        model.addAttribute("youtube", music.getYoutubeEmbUrl());
        model.addAttribute("albums", albums);
        model.addAttribute("words", words);
        model.addAttribute("hskhanzi", hskhanzi);

        return "musics/show";
    }

    @RequestMapping("search")
    public String search(@RequestParam("query") String query, Model model) { 
        List<MusicEntity> musics = musicService.findAll(query);
        model.addAttribute("musics", musics); 
        model.addAttribute("query", query); 
        model.addAttribute("queries", musicService.splitQuery(query)); 
        return "musics/index"; 
    }

    @PostMapping
    public String create(@ModelAttribute MusicEntity music) { 
        musicService.save(music);
        return "redirect:/musics"; 
    }

    @PutMapping("{id}")
    public String update(@PathVariable Integer id, @ModelAttribute MusicEntity music) {
        music.setId(id);
        musicService.save(music);
        return "redirect:/musics";
    }

    @DeleteMapping("{id}")
    public String destroy(@PathVariable Integer id) {
        musicService.delete(id);
        return "redirect:/musics";
    }
}