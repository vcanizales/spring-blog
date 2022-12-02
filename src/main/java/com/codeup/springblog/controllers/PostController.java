package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.Utils;
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


    @GetMapping
    public String allPosts(Model model){
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println();
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("posts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }


    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String addPost(@ModelAttribute Post post){
        User user = Utils.loggedInUser();
        post.setUser(user);
        postDao.save(post);
        return "redirect:";
    }


    @GetMapping("/users")
    public String createUser(){return "/posts/users";}

    @PostMapping("/users")
    public String addUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ).getId();
        if (currentUserId == 0) {
            return "redirect:/login";
        }
        Post post = postDao.findById(id);
        if (post.getUser().getId() != currentUserId){;
            return "redirect:";
        }
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@ModelAttribute Post post){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ).getId();
        if (currentUserId == 0) {
            return "redirect:/login";
        }
        User user = userDao.findById(currentUserId);
        post.setUser(user);
        postDao.save(post);
        return "redirect:posts/index";
    }

}
