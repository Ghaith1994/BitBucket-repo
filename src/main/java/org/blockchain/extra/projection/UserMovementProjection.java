package org.blockchain.extra.projection;

import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserMovementProjection {

    @Value("#{target.getVoucher().getDate()}")
    Date getDate();

    @Value("#{target.getCredit() > 0 ? target.getCredit() : target.getDebit()}")
    double getAmount();

    @Value("#{target.getCredit() > 0 ? 'received' : 'paid'}")
    String getTransactionType();

    @Value("#{target.getCredit() > 0 ? target.getVoucher().getDebitEntries() : null }")
    List<String> getFromUsers();

    @Value("#{target.getDebit() > 0 ? target.getVoucher().getCreditEntries() : null }")
    Map<String, Double> getToUsers();

}
