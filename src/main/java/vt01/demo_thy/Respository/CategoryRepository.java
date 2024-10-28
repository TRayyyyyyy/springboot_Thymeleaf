package vt01.demo_thy.Respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vt01.demo_thy.Entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByCategoryNameContaining(String name);
    //Tìm kiếm và Phân trang
    Page<CategoryEntity> findByCategoryNameContaining(String name, Pageable pageable);

    Optional<CategoryEntity> findByCategoryName(String name);
}
