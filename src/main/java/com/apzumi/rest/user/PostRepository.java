package com.apzumi.rest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostRepository  extends JpaRepository<Post, Long> {


    @Transactional
    @Query(value = "SELECT p.ID FROM POST p\n" +
            "join MODIFIER m\n" +
            "ON p.ID = m.ID\n" +
            "WHERE M.ENABLE_TO_UPDATE  = 'TRUE'", nativeQuery = true)
    List<Long> retrivePostsIdReadyToUpdate();

    @Transactional
    @Query(value = "SELECT p.ID, p.BODY, p.TITLE FROM POST p WHERE p.TITLE like %:title%" , nativeQuery = true)
    List<Object[]> findByPostTitle(@Param("title") String title);


}
