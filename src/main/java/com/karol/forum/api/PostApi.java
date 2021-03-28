package com.karol.forum.api;

import com.karol.forum.dao.entity.Category;
import com.karol.forum.dao.entity.Post;
import com.karol.forum.dao.entity.Reply;
import com.karol.forum.dao.entity.User;
import com.karol.forum.dto.PostDto;
import com.karol.forum.dto.ReplyDto;
import com.karol.forum.manager.CategoryManager;
import com.karol.forum.manager.PostManager;
import com.karol.forum.manager.ReplyManager;
import com.karol.forum.manager.UserManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostApi {
    private PostManager postManager;
    private ReplyManager replyManager;
    private ModelMapper modelMapper;
    private CategoryManager categoryManager;
    private UserManager userManager;

    @Autowired
    public PostApi(PostManager postManager, ReplyManager replyManager, ModelMapper modelMapper, CategoryManager categoryManager, UserManager userManager) {
        this.postManager = postManager;
        this.replyManager = replyManager;
        this.modelMapper = modelMapper;
        this.categoryManager = categoryManager;
        this.userManager = userManager;
    }

    private PostDto convertToDto(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);
        Set<Reply> replies = post.getReplies();
        if(replies != null) {
            Set<ReplyDto> replyDtoSet = new HashSet<>();
            for (Reply reply : replies) {
                ReplyDto replyDto = modelMapper.map(reply, ReplyDto.class);
                replyDtoSet.add(replyDto);
            }
            postDto.setReplyDtoSet(replyDtoSet);
        }
        return postDto;
    }

    private Post convertToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
        if(post.getID() == null){
            Date date = new Date(System.currentTimeMillis());
            post.setDate(date);
        }
        Optional<Category> category = categoryManager.findById(postDto.getCategoryId());
        post.setCategory(category.get());
        Optional<User> user = userManager.findById(postDto.getUserId());
        post.setUser(user.get());
        return post;
    }

    private Reply convertToEntity(ReplyDto replyDto){
        Reply reply = modelMapper.map(replyDto, Reply.class);
        if(reply.getID() == null){
            Date date = new Date(System.currentTimeMillis());
            reply.setDate(date);
        }
        Optional<Post> post = postManager.findById(replyDto.getPostID());
        reply.setPost(post.get());
        Optional<User> user = userManager.findById(replyDto.getUserID());
        reply.setUser(user.get());
        return reply;
    }

    @GetMapping
    public List<PostDto> getAll(){
        Iterable<Post> postIterable = postManager.findAll();
        List<Post> list = new ArrayList<>();
        postIterable.forEach(list::add);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PostDto getById(@PathVariable Long id){
        Optional<Post> post = postManager.findById(id);
        PostDto postDto = convertToDto(post.get());
        return postDto;
    }

    @PostMapping
    public PostDto save(@RequestBody PostDto postDto){
        Post post = convertToEntity(postDto);
        return convertToDto(postManager.save(post));
    }

    @PutMapping("{id}")
    public PostDto update(@PathVariable Long id, @RequestBody PostDto postDto){
        postDto.setID(id);
        Post post = convertToEntity(postDto);
        return convertToDto(postManager.save(post));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        postManager.deleteById(id);
    }

    @PostMapping("{id}")
    public Reply saveReply(@PathVariable Long id, @RequestBody ReplyDto replyDto){
        replyDto.setPostID(id);
        Reply reply = convertToEntity(replyDto);
        return replyManager.save(reply);
    }

    @PutMapping("{postId}/{id}")
    public Reply updateReply(@PathVariable Long postId, @PathVariable Long id, @RequestBody ReplyDto replyDto){
        replyDto.setPostID(postId);
        replyDto.setID(id);
        Reply reply = convertToEntity(replyDto);
        return replyManager.save(reply);
    }

    @DeleteMapping("{postId}/{id}")
    public void deleteReplyById(@PathVariable Long postId, @PathVariable Long id){
        replyManager.deleteById(id);
    }
}
