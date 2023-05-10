package pl.javastart.restoffers.offer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> findFiltered(Long categoryId, String title) {
        List<Offer> offers;
        if (categoryId == null) {
            if (title == null) {
                offers = offerRepository.findAll();
            } else {
                offers = offerRepository.findAll()
                        .stream()
                        .filter(offer -> offer.getTitle().toLowerCase()
                                .contains(title.toLowerCase()))
                        .collect(Collectors.toList());
            }
        } else {
            if (title == null) {
                offers = offerRepository.findByCategoryId(categoryId);
            } else {
                offers = offerRepository.findByCategoryId(categoryId)
                        .stream()
                        .filter(offer -> offer.getTitle().toLowerCase()
                                .contains(title.toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return offers
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    public Optional<OfferDto> findById(Long id) {
        return offerRepository.findById(id).map(this::convertToDto);
    }

    public List<OfferDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public OfferDto insert(OfferInsertDto offerInsertDto) {
        Offer offer = new Offer();
        setOfferFromOfferInsertDto(offerInsertDto, offer);
        offerRepository.save(offer);
        return convertToDto(offer);
    }

    private void setOfferFromOfferInsertDto(OfferInsertDto offerInsertDto, Offer offer) {
        offer.setTitle(offerInsertDto.getTitle());
        offer.setDescription(offerInsertDto.getDescription());
        offer.setImgUrl(offerInsertDto.getImgUrl());
        offer.setPrice(offerInsertDto.getPrice());
        if (offerInsertDto.getCategoryId() != null) {
            offer.setCategory(categoryRepository.getById(offerInsertDto.getCategoryId()));
        } else {
            offer.setCategory(categoryRepository.getById(1L));
        }
    }

    private OfferDto convertToDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offer.getId());
        offerDto.setTitle(offer.getTitle());
        offerDto.setDescription(offer.getDescription());
        offerDto.setImgUrl(offer.getImgUrl());
        offerDto.setPrice(offer.getPrice());
        offerDto.setCategoryName(offer.getCategory().getName());
        offerDto.setCategoryDescription(offer.getCategory().getDescription());
        return offerDto;
    }

    public Optional<OfferDto> update(Long id, OfferInsertDto offerInsertDto) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            Offer offerInDb = offerOptional.get();
            setOfferFromOfferInsertDto(offerInsertDto, offerInDb);
            offerRepository.save(offerInDb);
            return Optional.of(convertToDto(offerInDb));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        try {
            offerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            // ignore this Exception
        }
    }
}

