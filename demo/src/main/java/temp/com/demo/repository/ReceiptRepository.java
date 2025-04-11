package temp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import temp.com.demo.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
    
}
