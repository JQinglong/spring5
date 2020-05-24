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
import ml.programmersoffice.spring5.entities.AlbumEntity;
import ml.programmersoffice.spring5.services.MusicService;
import ml.programmersoffice.spring5.services.AlbumService;

@Controller
@RequestMapping("/albums") 
public class AlbumController {
    @Autowired
    private MusicService musicService;

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public String index(Model model) { 
        List<AlbumEntity> albums = albumService.findAll();
        model.addAttribute("albums", albums); 
        return "albums/index"; 
    }

    // @GetMapping({ "/list" })
    // public ModelAndView get(ModelAndView mav) {
    //     mav.addObject("musics", albumService.getAll());
    //     mav.setViewName("musics/index");
    //     return mav;
    // }


    @GetMapping("new")
    public String newMusic(Model model) {
        return "albums/new";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Integer id, Model model) { 
        AlbumEntity album = albumService.findOne(id);
        model.addAttribute("album", album);
        return "albums/edit";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Integer id, Model model) {
        AlbumEntity album = albumService.findOne(id);
        List<MusicEntity> musics = albumService.findMusics(album.getAlbumname());

        model.addAttribute("album", album);
        model.addAttribute("musics", musics); 
        // model.addAttribute("youtube", music.getYoutubeEmbUrl());
        return "albums/show";
    }

    @PostMapping
    public String create(@ModelAttribute AlbumEntity album) { 
        albumService.save(album);
        return "redirect:/albums"; 
    }

    @PutMapping("{id}")
    public String update(@PathVariable Integer id, @ModelAttribute AlbumEntity album) {
        album.setId(id);
        albumService.save(album);
        return "redirect:/albums";
    }

    @DeleteMapping("{id}")
    public String destroy(@PathVariable Integer id) {
        albumService.delete(id);
        return "redirect:/albums";
    }
}