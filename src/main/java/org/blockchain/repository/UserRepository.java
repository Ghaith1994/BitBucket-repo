package org.blockchain.repository;

import org.blockchain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
