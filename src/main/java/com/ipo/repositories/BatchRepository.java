package com.ipo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipo.entities.Batch;


public interface BatchRepository extends PagingAndSortingRepository<Batch, Long> {

}
