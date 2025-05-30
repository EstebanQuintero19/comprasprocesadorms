package co.ucoshop.comprasprocesadorms.repository.bill;


import co.ucoshop.comprasprocesadorms.domain.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID>{
}
