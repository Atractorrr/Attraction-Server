package run.attraction.api.v1.announcement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.announcement.Post;
import run.attraction.api.v1.announcement.PostCategory;
import run.attraction.api.v1.announcement.SearchType;
import run.attraction.api.v1.announcement.dto.PostDTO;
import run.attraction.api.v1.announcement.dto.PostSummaryDTO;
import run.attraction.api.v1.announcement.dto.request.PostCreateRequestDTO;
import run.attraction.api.v1.announcement.dto.request.PostSearchRequest;
import run.attraction.api.v1.announcement.dto.request.UpdatePostRequestDTO;
import run.attraction.api.v1.announcement.repository.AnnouncementRepository;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Transactional
    public void createPost(final PostCreateRequestDTO request) {
        PostCategory category = PostCategory.valueOf(request.postCategory().toUpperCase());

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .postCategory(category)
                .isPinned(request.isPinned())
                .build();

        announcementRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostDTO findPostById(final Long postId) {
        final Post post = announcementRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        final Post previousPost = announcementRepository.findTopByIdLessThan(postId).orElse(null);
        final Post nextPost = announcementRepository.findTopByIdGreaterThan(postId).orElse(null);

        return new PostDTO(post, previousPost, nextPost);
    }

    @Transactional
    public void deletePostById(final Long postId) {
        if (!announcementRepository.existsById(postId)) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
        announcementRepository.deleteById(postId);
    }

    @Transactional
    public void updatePostById(final Long postId, final UpdatePostRequestDTO post) {
        final Post beforePost = announcementRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        beforePost.update(post.title(), post.content(), PostCategory.findByName(post.postCategory()), post.isPinned());
        announcementRepository.save(beforePost);
    }

    @Transactional(readOnly = true)
    public Page<PostSummaryDTO> findPosts(Pageable pageable) {
        final Page<Post> posts = announcementRepository.findAllWithoutPinned(pageable);

        return posts.map(PostSummaryDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<PostSummaryDTO> findPinnedPosts(Pageable pageable) {
        final Page<Post> posts = announcementRepository.findAllWithPinned(pageable);

        return posts.map(PostSummaryDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<PostSummaryDTO> findPostsBySearchQuery(final Pageable pageable, final PostSearchRequest request) {
        final String searchTypeName = SearchType.findSearchType(request.type()).name();
        final Page<Post> posts = announcementRepository.findPostsBySearchQuery(pageable, searchTypeName,
                request.query());

        return posts.map(PostSummaryDTO::new);
    }
}
