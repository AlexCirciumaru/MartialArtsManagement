package ro.licenta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.licenta.exception.RecordNotFoundException;
import ro.licenta.model.MembershipFee;
import ro.licenta.repository.MembershipFeeRepository;

@Service("membershipFeeService")
public class MembershipFeeServiceImpl implements MembershipFeeService{
	
	private static final Logger logger = LoggerFactory.getLogger(MembershipFeeServiceImpl.class);
	
	@Autowired
	private MembershipFeeRepository membershipFeeRepository;
	
	@Override
	public MembershipFee addMembershipFee(MembershipFee membershipFee) {
		// TODO Auto-generated method stub
		return membershipFeeRepository.save(membershipFee);
	}

	@Override
	public MembershipFee updateMembershipFee(MembershipFee membershipFee) {
		// TODO Auto-generated method stub
		MembershipFee existingMembershipFee = membershipFeeRepository.findById(membershipFee.getId()).orElse(null);
		if(existingMembershipFee == null) {
			String errorMessage = "Membership Fee with id : " + membershipFee.getId() + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return membershipFeeRepository.save(membershipFee);
	}

	@Override
	public void deleteMembershipFee(Long membershipFeeId) {
		// TODO Auto-generated method stub
		MembershipFee membershipFee = membershipFeeRepository.findById(membershipFeeId).orElse(null);
		if(membershipFee != null) {
			membershipFeeRepository.deleteById(membershipFeeId);
		} else {
			String errorMessage = "Membership Fee with id : " + membershipFeeId + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public MembershipFee findMembershipFee(Long membershipFeeId) {
		// TODO Auto-generated method stub
		return membershipFeeRepository.findById(membershipFeeId).orElse(null);		
	}

	@Override
	public List<MembershipFee> getAllMembershipFees() {
		// TODO Auto-generated method stub
		return membershipFeeRepository.findAll();
	}

}
