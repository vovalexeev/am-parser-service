package com.wine.to.up.am.parser.service.repository;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WineRepository extends CrudRepository<Wine, UUID> {

    List<Wine> findAll();

}
