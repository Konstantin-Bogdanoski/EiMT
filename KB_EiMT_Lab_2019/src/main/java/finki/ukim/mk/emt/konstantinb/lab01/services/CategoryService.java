package finki.ukim.mk.emt.konstantinb.lab01.services;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.CategoryNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface CategoryService {
    Category addNewCategory(Category category) throws CategoryAlreadyExistsException;
    Category addNewCategory(String categoryName) throws CategoryAlreadyExistsException;
    List<Category> getCategories();
    Category update(Category category) throws CategoryNotFoundException;
    void delete(Category category) throws CategoryNotFoundException;
    Category getById(Long categoryID) throws CategoryNotFoundException;
    Category getByName(String name) throws CategoryNotFoundException;

}
