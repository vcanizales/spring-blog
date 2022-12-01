package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }


    @GetMapping("/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping
    public String allPosts(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println();
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("posts", allPosts);
        return "posts/index";
    }


    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String addPost(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("user", new User());
        return "redirect:";
    }

//    @GetMapping("/{id}/create")
//    public String editPost(Model model){
//        model.addAttribute()
//    }

    @GetMapping("/users")
    public String createUser(){return "/posts/users";}

    @PostMapping("/users")
    public String addUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.findById(id));
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@ModelAttribute Post post){
//        User user = userDao.getById(1L);
//        post.setUser(user);
//        ^^^this will show one post with the email of the user that made the post
        postDao.save(post);
        return "redirect:/posts";
    }

}
