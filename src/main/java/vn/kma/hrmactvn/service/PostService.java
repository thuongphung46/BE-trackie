package vn.kma.hrmactvn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.kma.hrmactvn.controller.post.dto.PostResponse;
import vn.kma.hrmactvn.entity.*;
import vn.kma.hrmactvn.entity.Character;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostAuthorRepository postAuthorRepository;
    private final AuthorRepository authorRepository;
    private final PostCharacterRepository postCharacterRepository;
    private final CharacterRepository characterRepository;
    private final PostGenreRepository postGenreRepository;
    private final GenreRepository genreRepository;

    public PostResponse getPostById(Long id) throws ActvnException {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ActvnException(404, "Post not found");
        }
        PostResponse postResponse = PostResponse.from(post);
        List<Long> authorIds = postAuthorRepository.findAllByPostId(post.getId()).stream().map(PostAuthor::getAuthorId).collect(Collectors.toList());
        postResponse.setAuthor(authorRepository.findAllByIdIn(authorIds));
        postResponse.setRating((float) (Math.random() * 4.9 + 0.1));
        postResponse.setReviewCount((long) (Math.random() * 10000));
        List<Long> characterIds = postCharacterRepository.findAllByPostId(post.getId()).stream().map(PostCharacter::getCharacterId).collect(Collectors.toList());
        postResponse.setCharacters(new ArrayList<>(characterRepository.findAllByIdIn(characterIds)));
        List<Long> genreIds = postGenreRepository.findAllByPostId(id).stream().map(PostGenre::getGenreId).collect(Collectors.toList());
        postResponse.setGenres(new ArrayList<>(genreRepository.findAllByIdIn(genreIds)));

        return postResponse;
    }

    public List<PostResponse> getMostPopularPosts() {
        List<Post> postsRaw = postRepository.findAll();
        List<Post> posts = new ArrayList<>();
        // get random posts from list
        for (int i =0 ; i < 10; i++) {
            int randomIndex = (int) (Math.random() * postsRaw.size());
            // if id is not in list, add it
            if (!posts.contains(postsRaw.get(randomIndex))) {
                posts.add(postsRaw.get(randomIndex));
            }
            // if post >= postsRaw, break
            if (posts.size() >= postsRaw.size()) {
                break;
            }
        }
        List<PostResponse> postResponses = new ArrayList<>();
        return getPostResponses(postResponses, posts);
    }

    public List<PostResponse> getPosts(String query) {
        List<PostResponse> postResponses = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        if (query == null) {
            posts =  postRepository.findAll();
        } else {
            posts =  postRepository.findAllByNameLike(query);
        }
        return getPostResponses(postResponses, posts);
    }

    private List<PostResponse> getPostResponses(List<PostResponse> postResponses, List<Post> posts) {
        for (Post post : posts) {
            PostResponse postResponse = PostResponse.from(post);
            List<Long> authorIds = postAuthorRepository.findAllByPostId(post.getId()).stream().map(PostAuthor::getAuthorId).collect(Collectors.toList());
            postResponse.setAuthor(authorRepository.findAllByIdIn(authorIds));
            postResponse.setRating((float) (Math.random() * 4.9 + 0.1));
            postResponse.setReviewCount((long) (Math.random() * 10000));
            List<Long> characterIds = postCharacterRepository.findAllByPostId(post.getId()).stream().map(PostCharacter::getCharacterId).collect(Collectors.toList());
            postResponse.setCharacters(new ArrayList<>(characterRepository.findAllByIdIn(characterIds)));
            List<Long> genreIds = postGenreRepository.findAllByPostId(post.getId()).stream().map(PostGenre::getGenreId).collect(Collectors.toList());
            postResponse.setGenres(new ArrayList<>(genreRepository.findAllByIdIn(genreIds)));
            postResponses.add(postResponse);
        }
        return postResponses;
    }
}
