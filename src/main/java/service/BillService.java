package service;

import model.Bill;
import repository.BillRepository;

import java.util.List;

public class BillService {
    private final BillRepository repo;

    public BillService(BillRepository repo) {
        this.repo = repo;
    }

    public Bill save(Bill bill) {
        return repo.save(bill);
    }

    public List<Bill> getAll() {
        return repo.findAll();
    }
}
