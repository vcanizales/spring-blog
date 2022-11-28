package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.beans.factory.support.SecurityContextProvider;
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
    public String addPost(@RequestParam(name="title")String title, @RequestParam(name="body")String body){
        Post post = new Post(title, body);
        User user = (User) userDao.findAll().get(0);
        post.setUser(user);
        postDao.save(post);
        return "redirect:";
    }



    @GetMapping("/users")
    public String createUser(){return "/posts/users";}

    @PostMapping("/users")
    public String addUser(@RequestParam(name="username")String username, @RequestParam(name="email")String email, @RequestParam(name="password")String password){
        User user = new User(username, email, password);
        userDao.save(user);
        return "redirect:/posts";
    }

}
