package pl.javastart.restoffers.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/names")
    public List<String> getAllCategoriesNames() {
        return categoryService.findAll()
                .stream()
                .map(CategoryDto::getName)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryDto insert(@RequestBody CategoryDto categoryDto) {
        return categoryService.insert(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
