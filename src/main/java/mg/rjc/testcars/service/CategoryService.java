package mg.rjc.testcars.service;

import mg.rjc.testcars.entities.Category;

public interface CategoryService {
    public Category saveCategory(Category category);
    public Category getCategoryById(Long id);
}
