package com.jtkim.study.springboot.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void searchContents() {
        //given
        String title = "테스트 게시글";
        String contents = "테스트 본문";

        postsRepository.save(Posts.builder() // id 값 있다면 update, 없다면 insert query 실행
                .title(title)
                .content(contents)
                .author("rlawlxor90@gmail.com")
                .build());

        //when
        List<Posts> postsLists = postsRepository.findAll(); // 모든 데이터 조회

        //then
        Posts posts = postsLists.get(0);

        assertThat(posts.getTitle()).isEqualTo(title); //
        assertThat(posts.getContent()).isEqualTo(contents);
    }

    @Test
    public void BaseTimeEntity_등록(){
        // given
        LocalDateTime now = LocalDateTime.of(2020, 05, 17, 16, 40, 00);

        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>> createdDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
