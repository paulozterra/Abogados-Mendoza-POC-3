package temp.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temp.com.demo.model.Lawyer;
import temp.com.demo.repository.LawyerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LawyerService {

    private final LawyerRepository lawyerRepository;

    public Lawyer save(Lawyer lawyer) {
        return lawyerRepository.save(lawyer);
    }

    public Optional<Lawyer> findById(Long id) {
        return lawyerRepository.findById(id);
    }

    public List<Lawyer> findAll() {
        return lawyerRepository.findAll();
    }
}