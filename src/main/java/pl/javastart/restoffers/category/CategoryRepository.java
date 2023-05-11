package pl.javastart.restoffers.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
    int countCategoriesByName(String name);

}
