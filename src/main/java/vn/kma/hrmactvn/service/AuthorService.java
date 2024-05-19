package vn.kma.hrmactvn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.kma.hrmactvn.entity.Author;
import vn.kma.hrmactvn.entity.Post;
import vn.kma.hrmactvn.entity.PostAuthor;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.repository.AuthorRepository;
import vn.kma.hrmactvn.repository.PostAuthorRepository;
import vn.kma.hrmactvn.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;
    private final PostAuthorRepository postAuthorRepository;

    public Author getAuthorById(Long id) throws ActvnException {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
           throw new ActvnException(404, "Author not found");
        }
        List<Long> postIds = postAuthorRepository.findAllByAuthorId(author.getId()).stream().map(PostAuthor::getPostId).collect(Collectors.toList());
        List<Post> posts = postRepository.findAllByIdIn(postIds);
        author.setPosts(posts);
        return author;
    }
}
