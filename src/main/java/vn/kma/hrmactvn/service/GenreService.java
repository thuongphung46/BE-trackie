package vn.kma.hrmactvn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.kma.hrmactvn.controller.genre.GenreCreateRequest;
import vn.kma.hrmactvn.controller.genre.dto.GenreResponse;
import vn.kma.hrmactvn.controller.post.dto.PostResponse;
import vn.kma.hrmactvn.entity.Genre;
import vn.kma.hrmactvn.entity.PostAuthor;
import vn.kma.hrmactvn.entity.PostGenre;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final PostAuthorRepository postAuthorRepository;
    private final PostGenreRepository postGenreRepository;


    public List<GenreResponse> getHome() {
        List<GenreResponse> genreResponses = new ArrayList<>();
        List<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            GenreResponse genreResponse = GenreResponse.from(genre);
            List<Long> postIds = postGenreRepository.findAllByGenreId(genre.getId()).stream().map(PostGenre::getPostId).collect(Collectors.toList());
            genreResponse.setPosts(postRepository.findAllById(postIds).stream().map(post -> {
                PostResponse postResponse = PostResponse.from(post);
                List<Long> authorIds = postAuthorRepository.findAllByPostId(post.getId()).stream().map(PostAuthor::getAuthorId).collect(Collectors.toList());
                postResponse.setAuthor(authorRepository.findAllByIdIn(authorIds));
                return postResponse;
            }).collect(Collectors.toList()));
            // get random rating for each post 0.1 - 5.0
            genreResponse.getPosts().forEach(postResponse -> postResponse.setRating((float) (Math.random() * 4.9 + 0.1)));
            // get random review count for each post 0 - 10000
            genreResponse.getPosts().forEach(postResponse -> postResponse.setReviewCount((long) (Math.random() * 10000)));
            genreResponses.add(genreResponse);
        }

        return genreResponses;
    }

    public GenreResponse getById(Long id) throws ActvnException {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new ActvnException(404, "Genre not found");
        }
        GenreResponse genreResponse = GenreResponse.from(genre);
        List<Long> postIds = postGenreRepository.findAllByGenreId(genre.getId()).stream().map(PostGenre::getPostId).collect(Collectors.toList());
        genreResponse.setPosts(postRepository.findAllById(postIds).stream().map(post -> {
            PostResponse postResponse = PostResponse.from(post);
            List<Long> authorIds = postAuthorRepository.findAllByPostId(post.getId()).stream().map(PostAuthor::getAuthorId).collect(Collectors.toList());
            postResponse.setAuthor(authorRepository.findAllByIdIn(authorIds));
            return postResponse;
        }).collect(Collectors.toList()));
        // get random rating for each post 0.1 - 5.0
        genreResponse.getPosts().forEach(postResponse -> postResponse.setRating((float) (Math.random() * 4.9 + 0.1)));
        // get random review count for each post 0 - 10000
        genreResponse.getPosts().forEach(postResponse -> postResponse.setReviewCount((long) (Math.random() * 10000)));
        return genreResponse;
    }

    public Genre createGenre(GenreCreateRequest request) {
        Genre genre = Genre.from(request);
        return genreRepository.save(genre);
    }

    public Genre update(Long id, GenreCreateRequest request) throws ActvnException {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new ActvnException(404, "Genre not found");
        }
        genre.update(request);
        return genreRepository.save(genre);
    }

    public void delete(Long id) throws ActvnException {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new ActvnException(404, "Genre not found");
        }
        genreRepository.delete(genre);
    }
}
