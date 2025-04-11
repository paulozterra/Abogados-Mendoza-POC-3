package temp.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temp.com.demo.model.Case;
import temp.com.demo.repository.CaseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;

    public Case save(Case caso) {
        return caseRepository.save(caso);
    }

    public Optional<Case> findById(Long id) {
        return caseRepository.findById(id);
    }

    public List<Case> findAll() {
        return caseRepository.findAll();
    }
}
