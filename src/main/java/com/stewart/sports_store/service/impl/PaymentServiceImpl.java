package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.PaymentDTO;
import com.stewart.sports_store.entity.PaymentInfo;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.repository.PaymentInfoRepository;
import com.stewart.sports_store.repository.UserInfoRepository;
import com.stewart.sports_store.service.PaymentService;
import com.stewart.sports_store.vo.PaymentInfoVO;
import com.stewart.sports_store.vo.SinglePaymentInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public StatusCode addPaymentInfo(PaymentDTO paymentDTO) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setUserId(userId);
        paymentInfo.setFamilyName(paymentDTO.getFamilyName());
        paymentInfo.setGivenName(paymentDTO.getGivenName());
        paymentInfo.setProvince(paymentDTO.getProvince());
        paymentInfo.setCity(paymentDTO.getCity());
        paymentInfo.setDistrict(paymentDTO.getDistrict());
        paymentInfo.setDetailedAddress(paymentDTO.getAddress());
        paymentInfo.setPaymentTel(paymentDTO.getTel());
        paymentInfoRepository.save(paymentInfo);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode deletePaymentInfo(Integer paymentId) {
        paymentInfoRepository.deleteById(paymentId);
        return StatusCode.SUCCESS;
    }

    @Override
    public PaymentDTO choosePaymentInfo(Integer paymentId) {
        PaymentInfo paymentInfo = paymentInfoRepository.findByPaymentId(paymentId);
        return new PaymentDTO(
                paymentInfo.getFamilyName(),
                paymentInfo.getGivenName(),
                paymentInfo.getProvince(),
                paymentInfo.getCity(),
                paymentInfo.getDistrict(),
                paymentInfo.getDetailedAddress(),
                paymentInfo.getPaymentTel()
        );
    }

    @Override
    public StatusCode updatePaymentInfo(Integer paymentId, PaymentDTO paymentDTO) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentId(paymentId);
        paymentInfo.setUserId(userId);
        paymentInfo.setFamilyName(paymentDTO.getFamilyName());
        paymentInfo.setGivenName(paymentDTO.getGivenName());
        paymentInfo.setProvince(paymentDTO.getProvince());
        paymentInfo.setCity(paymentDTO.getCity());
        paymentInfo.setDistrict(paymentDTO.getDistrict());
        paymentInfo.setDetailedAddress(paymentDTO.getAddress());
        paymentInfo.setPaymentTel(paymentDTO.getTel());
        paymentInfoRepository.save(paymentInfo);
        return StatusCode.SUCCESS;
    }

    @Override
    public PaymentInfoVO showPaymentInfo() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();
        List<PaymentInfo> paymentInfoList = paymentInfoRepository.findByUserId(userId);
        List<SinglePaymentInfoVO> singlePaymentInfoVOList = new ArrayList<>();
        for(PaymentInfo paymentInfo: paymentInfoList) {
            singlePaymentInfoVOList.add(new SinglePaymentInfoVO(
                    paymentInfo.getPaymentId(),
                    paymentInfo.getFamilyName() + paymentInfo.getGivenName(),
                    paymentInfo.getProvince() + paymentInfo.getCity() + paymentInfo.getDistrict(),
                            paymentInfo.getDetailedAddress(),
                    paymentInfo.getPaymentTel()));
        }
        return new PaymentInfoVO(singlePaymentInfoVOList);
    }
}
