package com.ugurukku.secondhand.advertisement.repositories;

import com.ugurukku.secondhand.advertisement.models.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdvertisementElasticSearchRepository extends PagingAndSortingRepository<Advertisement, String> {

    Page<Advertisement> findAdvertisementByDescriptionLikeIgnoreCase(String description, Pageable pageable);

//    Page<Advertisement> findAdvertisementsByTitleLikeIgnoreCase(String title,Pageable pageable);

    @Query("{\"match\": {\"query\": \"?0\",\"fuzziness\":\"3\", \"operator\": \"or\"}}}")
    Page<Advertisement> findAdvertisementsByTitleLikeIgnoreCase(String title,Pageable pageable);

}
