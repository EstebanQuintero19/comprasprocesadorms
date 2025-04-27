package co.ucoshop.comprasprocesadorms.repository.product;

import co.ucoshop.comprasprocesadorms.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID>  {
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(UUID productId);
    Page<Product> findAllByEmail(String email, Pageable pageable);
}
