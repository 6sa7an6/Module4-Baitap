package ra.student.controller;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.student.dto.request.StudentRequestDto;
import ra.student.service.student.IStudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IStudentService studentService;
    @RequestMapping({"","/dashboard"})
    public String dashboard(){
      return "admin/index";
    }
    @RequestMapping("/user")
    public String user(){
      return "admin/user";
    }
    @RequestMapping("/category")
    public String category(){
      return "admin/category";
    }
    @RequestMapping("/student")
    public String student(Model model){
        model.addAttribute("students",studentService.findAll());
      return "admin/student";
    }
    @RequestMapping("/save")
    public String save(Model model){
        StudentRequestDto studentRequestDto = new StudentRequestDto();
        model.addAttribute("student" , studentRequestDto);
        return "admin/save";
    }
    @RequestMapping(value = "/student/save/add",method = RequestMethod.POST)
    public String doAddStudent(@ModelAttribute("student") @Valid StudentRequestDto student , BindingResult bindingResult , Model model ) {
        if(bindingResult.hasErrors()){
            model.addAttribute("student" , student);
            return "/admin/save";
        }
        model.addAttribute("student" , student);
        studentService.save(student);
        return "redirect:/admin/student";
    }
    @RequestMapping("/student/delete/{id}")
    public String delete(@PathVariable Integer id){
        studentService.deleteById(id);
        return "redirect:/admin/student";
    }
}
