package com.stackroute.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.Blog;
import com.stackroute.service.BlogService;

/* Add annotation to declare this class as REST Controller */
@RestController
public class BlogController {

    /* Provide implementation code for these methods */
	@Autowired
	BlogService blogServ;	
	
    /*This method should save blog and return savedBlog Object */
	@PostMapping("/api/v1/blog")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
        return new ResponseEntity<Blog>(blogServ.saveBlog(blog),HttpStatus.CREATED) ;
    }

    /*This method should fetch all blogs and return the list of all blogs */
	@GetMapping("/api/v1/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<List<Blog>>(blogServ.getAllBlogs(),HttpStatus.OK);
    }

    /*This method should fetch the blog taking its id and return the respective blog */
	@GetMapping("/api/v1/blog/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Integer blogId){
        return new ResponseEntity<Blog>(blogServ.getBlogById(blogId), HttpStatus.OK);
    }

    /*This method should delete the blog taking its id and return the deleted blog */
	@DeleteMapping("/api/v1/blog/{blogId}")
    public Blog getBlogAfterDeleting(@PathVariable Integer blogId) {
		Blog deletedBlog = blogServ.getBlogById(blogId); 
		blogServ.deleteBlog(blogId);
		return deletedBlog;
    }

    /*This method should update blog and return the updatedBlog */
	@PutMapping("/api/v1/blog")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
        return new ResponseEntity<Blog>(blogServ.updateBlog(blog),HttpStatus.OK);
    }
}