package temp.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temp.com.demo.model.Receipt;
import temp.com.demo.repository.ReceiptRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public Optional<Receipt> findById(Long id) {
        return receiptRepository.findById(id);
    }

    public List<Receipt> findAll() {
        return receiptRepository.findAll();
    }
}
