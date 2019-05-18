package ro.licenta.service;

import java.util.List;

import ro.licenta.model.MembershipFee;

public interface MembershipFeeService {

	MembershipFee addMembershipFee(MembershipFee membershipFee);
	
	MembershipFee updateMembershipFee(MembershipFee membershipFee);
	
	void deleteMembershipFee(Long membershipFeeId);
	
	MembershipFee findMembershipFee(Long membershipFeeId);
	
	List<MembershipFee> getAllMembershipFees();
}
