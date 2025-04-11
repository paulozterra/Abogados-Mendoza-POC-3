package temp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import temp.com.demo.model.Case;

public interface CaseRepository extends JpaRepository<Case,Long> {
    
}
