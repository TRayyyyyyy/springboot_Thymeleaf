package vt01.demo_thy.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vt01.demo_thy.Entity.CategoryEntity;
import vt01.demo_thy.Respository.CategoryRepository;
import vt01.demo_thy.imp.ICategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void delete(CategoryEntity entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
      categoryRepository.deleteById(id);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public <S extends CategoryEntity> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<CategoryEntity> findAllById(Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
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
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public <S extends CategoryEntity> S save(S entity) {
        return categoryRepository.save(entity);
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
