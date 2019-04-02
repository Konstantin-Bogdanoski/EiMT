package finki.ukim.mk.emt.konstantinb.lab01.services.implementation;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.CategoryRepository;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addNewCategory(Category category) throws CategoryAlreadyExistsException{
        if(categoryRepository.getCategories().stream().anyMatch(v -> {
            return v.getName().equals(category.getName());
        }))
            throw new CategoryAlreadyExistsException();

        categoryRepository.addCategory(category);
        return category;
    }

    public Category addNewCategory(String categoryName) throws CategoryAlreadyExistsException{
        Category category = new Category();
        category.setName(categoryName);

        if(categoryRepository.getCategories().stream().anyMatch(v -> {
            return v.equals(category);
        }))
            throw new CategoryAlreadyExistsException();

        categoryRepository.addCategory(category);
        return category;
    }

    public List<Category> getCategories(){
        return categoryRepository.getCategories();
    }

    public Category update(Category category) throws CategoryNotFoundException{
        return null;
    }

    public void delete(Category category) throws CategoryNotFoundException{
        if(categoryRepository.getCategories().stream().noneMatch(v -> {
            return v.equals(category);
        })) throw new CategoryNotFoundException();
        
        categoryRepository.deleteCategory(category);
    }

    public Category getById(Long categoryID) throws CategoryNotFoundException{
        Optional<Category> category = categoryRepository.findById(categoryID);
        if(!category.isPresent()) throw new CategoryNotFoundException();
        return category.get();
    }

    public Category getByName(String name) throws CategoryNotFoundException{
        Optional<Category> category = categoryRepository.findByName(name);
        if(!category.isPresent()) throw new CategoryNotFoundException();
        return category.get();
    }
}
