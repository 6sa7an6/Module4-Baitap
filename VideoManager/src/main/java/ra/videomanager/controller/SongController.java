package ra.videomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ra.videomanager.model.Song;
import ra.videomanager.service.ISongService;

import java.util.List;

@Controller
public class SongController {
    @Autowired
    private ISongService songService;

    @RequestMapping("/home")
    public String home(Model model){
        List<Song> list = songService.showAll();
        model.addAttribute("list" , list);
        return "home";
    }
    @RequestMapping("/search")
    public String search(@RequestParam String search , Model model){
        List<Song> list = songService.findSongByNameOrAuthor(search);
        model.addAttribute("list" , list);
        model.addAttribute("search" , search);
        return "home";
    }
    @RequestMapping("/save")
    public String save(@RequestParam(required = false) String id , Model model){
        if(id != null){
            Song song = songService.findSongById(id);
            model.addAttribute(song);
        }
        return "save";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@ModelAttribute Song song){
        songService.save(song);
        return "redirect:/home";
    }
}
