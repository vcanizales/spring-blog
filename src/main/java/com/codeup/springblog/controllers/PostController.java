package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/posts")
public class PostController {



    @GetMapping("/")
    @ResponseBody
    public String posts(){
        return "These are all of the posts.";
    }

    @GetMapping("/{id}")
//    @ResponseBody
    public String individualPost(@PathVariable long id, Model model){
//        return "Your ID is " + id + ".";
        Post post = new Post(1,"title", "Here is my title");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping
    public String postsIndex(Model model){
        ArrayList<Post> postIndex = new ArrayList<Post>();
        postIndex.add(new Post(1,"Title 1", "Body 1"));
        postIndex.add(new Post(2,"Title 2", "Body 2"));
        model.addAttribute("posts", postIndex);

        return "posts/index";
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
