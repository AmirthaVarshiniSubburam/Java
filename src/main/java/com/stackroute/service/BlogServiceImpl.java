package com.stackroute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Blog;
import com.stackroute.repository.BlogRepository;

/* Add annotation to declare this class as Service class.
 * Also it should implement BlogService Interface and override all the implemented methods.*/
@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	BlogRepository blogRepo;
	
	
	@Override
	public Blog saveBlog(Blog blog) {		
		return blogRepo.save(blog);
	}

	@Override
	public List<Blog> getAllBlogs() {
		return blogRepo.findAll();
	}

	@Override
	public Blog getBlogById(int id) {
		return blogRepo.findById(id).get();
	}

	@Override
	public Blog deleteBlog(int id) {
		
		Blog blog = null;
        Optional optional = blogRepo.findById(id);
        if (optional.isPresent()) {
            blog = blogRepo.findById(id).get();
            blogRepo.deleteById(id);
        }
        return blog;
	}

	@Override
	public Blog updateBlog(Blog blog) {
		
		Blog updatedBlog = null;
        Optional optional = blogRepo.findById(blog.getBlogId());
        if (optional.isPresent()) {
            Blog getBlog = blogRepo.findById(blog.getBlogId()).get();
            getBlog.setBlogContent(blog.getBlogContent());
            updatedBlog = saveBlog(getBlog);
        }
        return updatedBlog;
	}

}
