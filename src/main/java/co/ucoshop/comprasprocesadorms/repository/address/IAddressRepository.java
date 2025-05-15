package co.ucoshop.comprasprocesadorms.repository.address;

import co.ucoshop.comprasprocesadorms.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAddressRepository extends JpaRepository<Address, UUID> {
}
