package com.apzumi.rest.user;

import com.apzumi.rest.modifier.ModifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    ModifierRepository mdModifierRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void startUp() {

        callForExternalAPi();
        callForModifierStartup();

    }

    private List<Post> callForExternalAPi() {
        return postService.saveAll("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET);
    }

    private void callForModifierStartup() {
        mdModifierRepository.findAllData();
    }

    @Scheduled(cron = "0 12 * * * ?") // each day at 12:00
    List<Post> updatePosts() {
        return  postService.updatePosts("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET);
    }

    @RequestMapping("/updatePosts")
    List<Post> updatePostsOnRequest() {
        return  postService.updatePosts("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET);
    }


    @RequestMapping("/filtered/{title}")
    public List<Object[]> getAllElements(@PathVariable String title) {
        return postService.findPostByTilte(title);
    }

    @RequestMapping("/posts")
    public List<Post> getAllElements() {
        return postService.getAllRecord();
    }

    @RequestMapping("/postremove/{id}")
    public String delete(@PathVariable int id ) {

        Optional<Post> post = Optional.ofNullable(postService.finById(id));
        if(post.isPresent()) {
            postService.deleteById(id);
            mdModifierRepository.deleteById((long) id);
            return "Post:  " + id + " deleted";
        }
        else {
            throw new RuntimeException("Post " + id + " cannot be j found");
        }
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable long id) {

        Optional<Post> postToUpdate = Optional.ofNullable(postService.finById(id));

        if(! postToUpdate.isPresent())
            return  ResponseEntity.notFound().build();

        post.setId(id);
        postService.save(post);
        mdModifierRepository.updateEnableToModifiy(id);

        return  ResponseEntity.noContent().build();
    }
}
