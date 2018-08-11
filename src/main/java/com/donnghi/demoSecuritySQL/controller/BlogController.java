package com.donnghi.demoSecuritySQL.controller;


import com.donnghi.demoSecuritySQL.Entities.Blog;
import com.donnghi.demoSecuritySQL.Repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path="")
public class BlogController
{
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Blog> root() {
        logger.info("Welcome Controller");
        return blogRepository.findByMyOwn();
    }

    @RequestMapping("/blog")
    public List<Blog> index() {
        logger.info("Display All");
        return blogRepository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id) {
        logger.info("Display id="+id);
        int blogId = Integer.parseInt(id);
        Optional<Blog> optBlog = blogRepository.findById(blogId);
        if (optBlog.isPresent())
            return optBlog.get();
        return null;
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body) {
        System.out.println("search()");
        String searchTerm = body.get("text");
        return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body) {
        System.out.println("create()");
        String title = body.get("title");
        String content = body.get("content");
        return blogRepository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
        System.out.println("update()");
        int blogId = Integer.parseInt(id);
        Optional<Blog> optBlog = blogRepository.findById(blogId);
        if (optBlog.isPresent()) {
            Blog blog = optBlog.get();
            blog.setTitle(body.get("title"));
            blog.setContent(body.get("content"));
            blogRepository.save(blog);
        }
        return optBlog.get();
    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id) {
        System.out.println("delete()");
        int blogId = Integer.parseInt(id);
        Optional<Blog> optBlog = blogRepository.findById(blogId);
        blogRepository.delete(optBlog.get());
        return true;
    }


}
