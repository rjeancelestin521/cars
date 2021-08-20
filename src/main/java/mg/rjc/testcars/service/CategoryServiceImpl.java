package mg.rjc.testcars.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.rjc.testcars.entities.Category;
import mg.rjc.testcars.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        log.info("Saving new category of car {} to the database", category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory =  categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            log.info("Fetching category {}", category.getName());
            return category;
        } else {
            log.error("Category id: {} is not exists", id);
            throw new RuntimeException("Category id: " + id + " is not exists");
        }

    }
}
