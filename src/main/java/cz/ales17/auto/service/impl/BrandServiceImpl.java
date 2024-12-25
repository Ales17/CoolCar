package cz.ales17.auto.service.impl;

import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.repository.BrandRepository;
import cz.ales17.auto.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Brand getBrand(long id) {
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
