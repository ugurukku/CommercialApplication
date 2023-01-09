package com.ugurukku.secondhand.repositories;

import com.ugurukku.secondhand.models.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementElasticSearchRepository extends ElasticsearchRepository<Advertisement, String> {

    Page<Advertisement> findAdvertisementByDescriptionLikeIgnoreCase(String description, Pageable pageable);

    Page<Advertisement> findAdvertisementsByTitleLikeIgnoreCase(String title,Pageable pageable);

    @Query("{\"match\": {\"title\": {\"query\": \"?0\"}}}")
    Page<Advertisement> findAdvertisementsByTitleFuzzy(String title,Pageable pageable);

}
