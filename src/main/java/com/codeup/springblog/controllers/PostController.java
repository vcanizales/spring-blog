package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    @ResponseBody
    public String posts(){
        return "These are all of the posts.";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String individualPost(@PathVariable String id){
        return "Your ID is " + id + ".";
    }

    @GetMapping("/create")
    @ResponseBody
    public String viewCreatedPost(){
        return "This is the form to view the created post.";
    }

    @PostMapping("/create")
    @ResponseBody
    public String createPost() {
        return "This is the form to CREATE a post";
    }
}
