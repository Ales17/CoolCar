package cz.ales17.auto.service.impl;

import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.repository.BrandRepository;
import cz.ales17.auto.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(long id) {
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
