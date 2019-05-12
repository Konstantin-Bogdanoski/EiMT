package finki.ukim.mk.emt.konstantinb.lab01.services.implementation;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.CategoryRepository;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence.PersistentCategoryRepository;
import finki.ukim.mk.emt.konstantinb.lab01.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private PersistentCategoryRepository categoryRepository;
    //private CategoryRepository categoryRepository;

    public CategoryServiceImpl(PersistentCategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addNewCategory(Category category) throws CategoryAlreadyExistsException{
        if (categoryRepository.findAll().stream().anyMatch(v -> {
            return v.getName().equals(category.getName());
        }))
            throw new CategoryAlreadyExistsException();

        categoryRepository.save(category);
        return category;
    }

    public Category addNewCategory(String categoryName) throws CategoryAlreadyExistsException{
        Category category = new Category();
        category.setName(categoryName);

        if (categoryRepository.findAll().stream().anyMatch(v -> {
            return v.equals(category);
        }))
            throw new CategoryAlreadyExistsException();

        categoryRepository.save(category);
        return category;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category update(Category category) throws CategoryNotFoundException{
        return null;
    }

    public void delete(Category category) throws CategoryNotFoundException{
        if (categoryRepository.findAll().stream().noneMatch(v -> {
            return v.equals(category);
        })) throw new CategoryNotFoundException();

        categoryRepository.deleteByID(category.getID());
    }

    public Category getById(Long categoryID) throws CategoryNotFoundException{
        Optional<Category> category = categoryRepository.findByID(categoryID);
        if(!category.isPresent()) throw new CategoryNotFoundException();
        return category.get();
    }

    public Category getByName(String name) throws CategoryNotFoundException{
        Optional<Category> category = categoryRepository.findByName(name);
        if(!category.isPresent()) throw new CategoryNotFoundException();
        return category.get();
    }
}
