package co.ucoshop.comprasprocesadorms.repository.paymethod;

import co.ucoshop.ucoshopapi.domain.paymethod.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPayMethodRepository extends JpaRepository<PayMethod, UUID> {
}
