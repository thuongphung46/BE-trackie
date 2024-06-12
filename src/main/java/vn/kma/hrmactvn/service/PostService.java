package vn.kma.hrmactvn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.kma.hrmactvn.controller.post.dto.PostCreateRequest;
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
    private final GenreCharacterRepository genreCharacterRepository;

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

    public Post createPost(PostCreateRequest request) {
        Post post = Post.from(request);
        if (request.getAuthorIds() != null && !request.getAuthorIds().isEmpty()) {
            for (Long authorId : request.getAuthorIds()) {
                Author author = authorRepository.findFirstById(authorId);
                if (author == null) {
                    continue;
                }
                PostAuthor postAuthor = new PostAuthor();
                postAuthor.setPostId(post.getId());
                postAuthor.setAuthorId(authorId);
                postAuthorRepository.save(postAuthor);
            }
        }
        if (request.getCharacterIds() != null && !request.getCharacterIds().isEmpty()) {
            for (Long characterId : request.getCharacterIds()) {
                Character character = characterRepository.findFirstById(characterId);
                if (character == null) {
                    continue;
                }
                PostCharacter postCharacter = new PostCharacter();
                postCharacter.setPostId(post.getId());
                postCharacter.setCharacterId(characterId);
                postCharacterRepository.save(postCharacter);
            }
        }
        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            for (Long genreId : request.getGenreIds()) {
                Genre genre = genreRepository.findFirstById(genreId);
                if (genre == null) {
                    continue;
                }
                PostGenre postGenre = new PostGenre();
                postGenre.setPostId(post.getId());
                postGenre.setGenreId(genreId);
                postGenreRepository.save(postGenre);
            }
        }

        if ((request.getGenreIds() != null && !request.getGenreIds().isEmpty()) && (request.getCharacterIds() != null && !request.getCharacterIds().isEmpty())) {
            for (Long genreId : request.getGenreIds()) {
                for (Long characterId : request.getCharacterIds()) {
                    GenreCharacter genreCharacter = new GenreCharacter();
                    genreCharacter.setGenreId(genreId);
                    genreCharacter.setCharacterId(characterId);
                    genreCharacterRepository.save(genreCharacter);
                }
            }
        }

        return postRepository.save(post);
    }

    public Post update(Long id, PostCreateRequest request) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }
        post.update(request);
        if (request.getAuthorIds() != null && !request.getAuthorIds().isEmpty()) {
            postAuthorRepository.deleteAllByPostId(post.getId());
            for (Long authorId : request.getAuthorIds()) {
                Author author = authorRepository.findFirstById(authorId);
                if (author == null) {
                    continue;
                }
                PostAuthor postAuthor = new PostAuthor();
                postAuthor.setPostId(post.getId());
                postAuthor.setAuthorId(authorId);
                postAuthorRepository.save(postAuthor);
            }
        }
        if (request.getCharacterIds() != null && !request.getCharacterIds().isEmpty()) {
            postCharacterRepository.deleteAllByPostId(post.getId());
            for (Long characterId : request.getCharacterIds()) {
                Character character = characterRepository.findFirstById(characterId);
                if (character == null) {
                    continue;
                }
                PostCharacter postCharacter = new PostCharacter();
                postCharacter.setPostId(post.getId());
                postCharacter.setCharacterId(characterId);
                postCharacterRepository.save(postCharacter);
            }
        }
        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            postGenreRepository.deleteAllByPostId(post.getId());
            for (Long genreId : request.getGenreIds()) {
                Genre genre = genreRepository.findFirstById(genreId);
                if (genre == null) {
                    continue;
                }
                PostGenre postGenre = new PostGenre();
                postGenre.setPostId(post.getId());
                postGenre.setGenreId(genreId);
                postGenreRepository.save(postGenre);
            }
        }

        if ((request.getGenreIds() != null && !request.getGenreIds().isEmpty()) && (request.getCharacterIds() != null && !request.getCharacterIds().isEmpty())) {
            for (Long genreId : request.getGenreIds()) {
                for (Long characterId : request.getCharacterIds()) {
                    genreCharacterRepository.deleteByGenreIdAndCharacterId(genreId, characterId);
                    GenreCharacter genreCharacter = new GenreCharacter();
                    genreCharacter.setGenreId(genreId);
                    genreCharacter.setCharacterId(characterId);
                    genreCharacterRepository.save(genreCharacter);
                }
            }
        }

        return postRepository.save(post);
    }



    public void delete(Long id) throws ActvnException {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ActvnException(404, "Post not found");
        }
        postRepository.delete(post);
    }
}
