package com.netcracker.avizhen.services.service.impl;

import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.persistence.repository.AdvertRepository;
import com.netcracker.avizhen.persistence.repository.CarRepository;
import com.netcracker.avizhen.services.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Александр on 07.11.2016.
 */
@Service
@Transactional
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Advert> findAllAdverts() {
        return advertRepository.getAll();
    }

    @Override
    public Advert findAdvertById(int advertId) {
        return advertRepository.findOne(advertId);
    }

    @Override
    public Advert addAdvert(@Valid Advert advert) {
        carRepository.save(advert.getCar());
        return advertRepository.save(advert);
    }

    @Override
    public void removeAdvertById(int advertId) {
        if (advertRepository.findOne(advertId) == null) {
            return;
        }
        advertRepository.delete(advertId);
    }

    @Override
    public Page<Advert> findPaginated(Pageable pageable) {
        return advertRepository.findAll(pageable);
    }

    @Override
    public long getCount() {
        return advertRepository.count();
    }
}
