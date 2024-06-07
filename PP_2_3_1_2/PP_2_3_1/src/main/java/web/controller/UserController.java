package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import web.Service.CarServiceImpl;
import web.model.User;
import web.service.UserServiceImp;

@Controller
@RequestMapping("/users")
//@RequestMapping("/cars")
public class UserController {

    final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userServiceImp.index());
        return "users";
    }

    @GetMapping("/{id}")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userServiceImp.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceImp.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userServiceImp.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userServiceImp.update(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam("id") int id) {
        userServiceImp.delete(id);
        return "redirect:/users";
    }
}
