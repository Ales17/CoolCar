package cz.ales17.auto.service;

import cz.ales17.auto.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands();
    Brand getBrand(long id);
}
