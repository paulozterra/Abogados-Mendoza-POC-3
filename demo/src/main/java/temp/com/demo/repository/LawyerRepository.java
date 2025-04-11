package temp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import temp.com.demo.model.Lawyer;

public interface LawyerRepository extends JpaRepository<Lawyer,Long> {
    
}
