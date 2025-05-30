package co.ucoshop.comprasprocesadorms.repository.sales;

import co.ucoshop.comprasprocesadorms.domain.sales.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, UUID> {
    Page<Sale> findAllByEmail(String email, Pageable pageable);
}


