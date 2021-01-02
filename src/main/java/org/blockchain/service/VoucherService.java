package org.blockchain.service;

import org.blockchain.entity.User;
import org.blockchain.entity.Voucher;
import org.blockchain.entity.VoucherEntry;
import org.blockchain.extra.enums.VoucherType;
import org.blockchain.repository.VoucherEntryRepository;
import org.blockchain.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Service
public class VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    VoucherEntryRepository voucherEntryRepository;

    @Transactional
    public synchronized void addPaymentVoucher(User user, List<VoucherEntry> paymentEntries){
        Double totalCredit = paymentEntries.stream().mapToDouble(VoucherEntry::getCredit).sum();
        if (user.getVc() < totalCredit){
            throw new RuntimeException("You do not have enough balance to complete the transaction");
        }

        Voucher paymentVoucher = new Voucher();
        paymentVoucher.setType(VoucherType.PAYMENT_VOUCHER);
        voucherRepository.save(paymentVoucher);

        VoucherEntry debitEntry = new VoucherEntry();
        debitEntry.setUser(user);
        debitEntry.setDebit(totalCredit);

        paymentEntries.add(debitEntry);
        paymentEntries.forEach(ve -> ve.setVoucher(paymentVoucher));
        voucherEntryRepository.saveAll(paymentEntries);
    }

    @Transactional
    public void addOpeningBalanceVoucher(List<VoucherEntry> voucherEntries) {
        Double totalCredit = voucherEntries.stream().mapToDouble(VoucherEntry::getCredit).sum();
        Double totalDebit = voucherEntries.stream().mapToDouble(VoucherEntry::getDebit).sum();
        if(!totalCredit.equals(totalDebit)){
            throw new RuntimeException("Debit amount must be equal credit amount");
        }

        Voucher entryVoucher = new Voucher();
        entryVoucher.setType(VoucherType.OPENING_BALANCE_VOUCHER);
        voucherRepository.save(entryVoucher);

        voucherEntries.forEach(ve -> ve.setVoucher(entryVoucher));
        voucherEntryRepository.saveAll(voucherEntries);
    }
}
