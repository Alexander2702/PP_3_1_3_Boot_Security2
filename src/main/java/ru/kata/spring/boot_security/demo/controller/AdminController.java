package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @GetMapping()
    public String findAll(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userAuth, Model model) {
        model.addAttribute("userAuth", userService.findByEmail(userAuth.getUsername()).orElseThrow());
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        model.addAttribute("rolesList", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "user-list";
    }

    //    @GetMapping("/user-create")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "user-list";
//    }
    @PostMapping
    public String add(@ModelAttribute("newUser") User user) {  //,        @RequestParam(value = "nameRoles") String[] roles
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-list";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {  //, @PathVariable("id") int id
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/";
    }
}
