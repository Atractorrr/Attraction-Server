package run.attraction.api.v1.announcement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.announcement.PostCategory;
import run.attraction.api.v1.announcement.dto.CustomPageDTO;
import run.attraction.api.v1.announcement.dto.PostDTO;
import run.attraction.api.v1.announcement.dto.PostSummaryDTO;
import run.attraction.api.v1.announcement.dto.request.PostCreateRequestDTO;
import run.attraction.api.v1.announcement.dto.request.PostSearchRequest;
import run.attraction.api.v1.announcement.dto.request.UpdatePostRequestDTO;
import run.attraction.api.v1.announcement.service.AnnouncementService;
import run.attraction.api.v1.archive.dto.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
@Validated
@Tag(name = "공지사항", description = "AnnouncementController")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    @Operation(summary = "게시글 생성", description = "게시글을 생성하는 로직입니다.")
    public ApiResponse<Void> createPost(@Valid @RequestBody PostCreateRequestDTO request) {
        announcementService.createPost(request);

        return ApiResponse.from(HttpStatus.CREATED, "성공", null);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 조회", description = "postId를 입력받아 해당 게시글을 조회하는 로직입니다.")
    public ApiResponse<PostDTO> getPost(@PathVariable Long postId) {
        final PostDTO post = announcementService.findPostById(postId);

        return ApiResponse.from(HttpStatus.OK, "성공", post);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "postId를 입력받아 해당 게시글을 삭제하는 로직입니다.")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        announcementService.deletePostById(postId);

        return ApiResponse.from(HttpStatus.OK, "성공", null);
    }

    @PatchMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "postId를 입력받아 해당 게시글을 수정하는 로직입니다.")
    public ApiResponse<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDTO post) {
        announcementService.updatePostById(postId, post);

        return ApiResponse.from(HttpStatus.OK, "성공", null);
    }

    @GetMapping
    @Operation(summary = "고정되지 않은 모든 게시물 가져오기", description = "고정되지 않은 모든 게시물을 가져오는 로직입니다.")
    public ApiResponse<CustomPageDTO<PostSummaryDTO>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category

    ) {
        Pageable pageable = PageRequest.of(page, size);
        final Page<PostSummaryDTO> posts = announcementService.findPosts(pageable, category);
        CustomPageDTO<PostSummaryDTO> customPage = new CustomPageDTO<>(posts, PostCategory.find(category));

        return ApiResponse.from(HttpStatus.OK, "성공", customPage);
    }

    @GetMapping("/pinned")
    @Operation(summary = "고정된 모든 게시물 가져오기", description = "고정된 모든 게시물을 가져오는 로직입니다.")
    public ApiResponse<List<PostSummaryDTO>> getPinnedPosts() {
        final List<PostSummaryDTO> posts = announcementService.findPinnedPosts();

        return ApiResponse.from(HttpStatus.OK, "성공", posts);
    }

    @GetMapping("/search")
    @Operation(summary = "게시물 검색", description = "검색 타입(제목/내용/제목+내용)에 맞는 게시물을 검색해주는 로직")
    public ApiResponse<Page<PostSummaryDTO>> searchPosts(@ModelAttribute PostSearchRequest request
    ) {
        Pageable pageable = PageRequest.of(request.page(), request.size());
        final Page<PostSummaryDTO> posts = announcementService.findPostsBySearchQuery(pageable, request);

        return ApiResponse.from(HttpStatus.OK, "성공", posts);
    }
}
