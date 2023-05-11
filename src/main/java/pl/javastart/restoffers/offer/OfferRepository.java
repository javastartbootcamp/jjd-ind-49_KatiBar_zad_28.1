package pl.javastart.restoffers.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.restoffers.category.Category;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByCategoryId(Long categoryId);

    int countOfferByCategory(Category category);
    int countOffersByIdIsNotNull();
}
