package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String findAll(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userAuth,Model model) {
        model.addAttribute("userAuth", userService.findByEmail(userAuth.getUsername()).orElseThrow());
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList",userList);
        model.addAttribute("rolesList", userService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "user-list";
    }
//    @GetMapping("/user-create")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "user-list";
//    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/user-update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-update";
    }
    @PatchMapping("/user-update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }
    @DeleteMapping("user-delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/";
    }
}
