package com.wine.to.up.am.parser.service.repository;

import com.wine.to.up.am.parser.service.domain.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface WineRepository extends CrudRepository<Wine, Long> {
    List<Wine> findAllByBrand(Brand brand);
    List<Wine> findAllByCountry(Country country);
    List<Wine> findAllByColorEnum(Colors color);
    List<Wine> findAllBySugarEnum(Sugar sugar);
    List<Wine> findAllByStrengthAfter(double strength);
    List<Wine> findAllByStrengthBefore(double strength);
    List<Wine> findAllByDateBottlingAfter(Date date);
    List<Wine> findAllByDateBottlingBefore(Date date);
}
