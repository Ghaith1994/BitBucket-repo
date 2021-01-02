package org.blockchain.repository;

import org.blockchain.entity.User;
import org.blockchain.entity.VoucherEntry;
import org.blockchain.extra.projection.UserMovementProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Repository
public interface VoucherEntryRepository extends JpaRepository<VoucherEntry, Long> {
    List<UserMovementProjection> findByUser(User user);
}
