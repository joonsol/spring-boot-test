package restapi.prac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.prac.model.Post;
import restapi.prac.service.PostService;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000") // ✅ 추가

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private  PostService postService;

//    목록 조회
    @GetMapping
    public ResponseEntity<Page<Post>> listPost(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size){
        Pageable pageable =PageRequest.of(page,size);
        Page<Post> posts =postService.getPosts(pageable);
        return ResponseEntity.ok().body(posts);
    }

//    단건조회
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPosts(@PathVariable Long id){
        Optional<Post> postOpt=postService.getPost(id);
        return  postOpt.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }

//    생성
    @PostMapping
    public  ResponseEntity<Post> createPost(@RequestBody Post post){

        // 🔍 수신된 데이터 로그 출력
        System.out.println("✅ 수신된 게시글 정보:");
        System.out.println("제목: " + post.getTitle());
        System.out.println("내용: " + post.getContent());
        Post createdPost =postService.createPost(post);
        return  ResponseEntity.ok(createdPost);
    }

//    수정
    @PutMapping("/{id}")
    public  ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody Post updatedPost){
        Optional<Post> updated=postService.updatePost(id,updatedPost);

        return  updated.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePost(@PathVariable Long id){
        boolean deleted=postService.deletePost(id);
        if(deleted){
            return  ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.notFound().build();
        }
    }




}
