package br.com.luancarlos.examplejwt.service;

import br.com.luancarlos.examplejwt.entity.Product;
import br.com.luancarlos.examplejwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete (Long id) {
        Optional<Product> product = productRepository.findById(id);
        productRepository.delete(product.get());
    }

}
