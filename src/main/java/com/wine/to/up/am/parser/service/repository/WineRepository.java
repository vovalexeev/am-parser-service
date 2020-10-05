package com.wine.to.up.am.parser.service.repository;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {
}
