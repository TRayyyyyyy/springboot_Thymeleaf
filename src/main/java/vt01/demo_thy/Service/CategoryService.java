package vt01.demo_thy.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vt01.demo_thy.Entity.CategoryEntity;
import vt01.demo_thy.Respository.CategoryRepository;
import vt01.demo_thy.imp.ICategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void delete(CategoryEntity entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends CategoryEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CategoryEntity> findAllById(Iterable<Long> ids) {
        return List.of();
    }

    @Override
    public List<CategoryEntity> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findByCategoryName(String name) {
        return Optional.empty();
    }

    @Override
    public <S extends CategoryEntity> S save(S entity) {
        return null;
    }

    @Override
    public Page<CategoryEntity> findByCategoryNameContaining (String name, Pageable pageable){
        return categoryRepository.findByCategoryNameContaining(name, pageable);
    }

    @Override
    public List<CategoryEntity> findByCategoryNameContaining (String name){
        return categoryRepository.findByCategoryNameContaining(name);
    }

}
