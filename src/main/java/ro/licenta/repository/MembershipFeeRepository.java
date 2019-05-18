package ro.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.licenta.model.MembershipFee;

@Repository("membershipFeeRepository")
public interface MembershipFeeRepository extends JpaRepository<MembershipFee, Long>, JpaSpecificationExecutor<MembershipFee>{

}
