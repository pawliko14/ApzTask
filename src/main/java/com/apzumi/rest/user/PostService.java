package com.apzumi.rest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public Post save(Post post) {
        return  postRepository.save(post);
    }

    public Post finById(long id) {
        return  postRepository.getOne(id);
    }

    public List<Post> getAllRecord(){
        return postRepository.findAll();
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }


    public List<Post> saveAll(final String path, final HttpMethod method) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<List<Post>> response = restTemplate.exchange(
                path,
                method,
                null,
                new ParameterizedTypeReference<List<Post>>(){});
        List<Post> list = response.getBody();

        return postRepository.saveAll(list);
    }

    public List<Post> updatePosts(final String path, final HttpMethod method) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<List<Post>> response = restTemplate.exchange(
                path,
                method,
                null,
                new ParameterizedTypeReference<List<Post>>(){});
        List<Post> list = response.getBody();

        List<Long> readyToUpdate = postRepository.retrivePostsIdReadyToUpdate();

        List<Post> resultsToUpdate = list.stream()
                .filter(two -> readyToUpdate.stream()
                .anyMatch(one -> one == two.getId()))
                .collect(Collectors.toList());

        return postRepository.saveAll(resultsToUpdate);
    }

    public List<Object[]> findPostByTilte(String title) {
        return postRepository.findByPostTitle(title);
    }
}
