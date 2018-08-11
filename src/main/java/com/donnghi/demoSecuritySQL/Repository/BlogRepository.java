package com.donnghi.demoSecuritySQL.Repository;

import com.donnghi.demoSecuritySQL.Entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>
{
    List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);

    @Query(value = "SELECT * FROM BLOG", nativeQuery = true)
    List<Blog> findByMyOwn();
}
