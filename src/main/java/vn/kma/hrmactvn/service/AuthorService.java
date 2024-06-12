package vn.kma.hrmactvn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.kma.hrmactvn.controller.author.AuthorCreateRequest;
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
        return authorRepository.findFirstById(id);
    }

    public Author createAuthor(AuthorCreateRequest request) {
        Author author = Author.from(request);
        return authorRepository.save(author);
    }

    public Author update(Long id, AuthorCreateRequest request) throws ActvnException {
        Author author = authorRepository.findFirstById(id);
        if (author == null) {
            throw new ActvnException(404,"Author not found");
        }
        author.update(request);
        return authorRepository.save(author);
    }

    public void delete(Long id) throws ActvnException {
        Author author = authorRepository.findFirstById(id);
        if (author == null) {
            throw new ActvnException(404,"Author not found");
        }
        authorRepository.delete(author);
    }
}
