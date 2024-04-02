package lhc.portfolio_test.repository;

import lhc.portfolio_test.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    boolean existsByUsername(String id);

    MemberEntity findByUsername(String name);
}
