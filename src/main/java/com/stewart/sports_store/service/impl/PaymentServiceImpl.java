package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.PaymentDTO;
import com.stewart.sports_store.entity.*;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.repository.*;
import com.stewart.sports_store.service.PaymentService;
import com.stewart.sports_store.vo.PayingItemsVO;
import com.stewart.sports_store.vo.PaymentInfoVO;
import com.stewart.sports_store.vo.SingleCartVO;
import com.stewart.sports_store.vo.SinglePaymentInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

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

    @Override
    public PayingItemsVO showPayingItems(List<Integer> cartIdList) {
        List<SingleCartVO> singleCartVOList = new ArrayList<>();
        for(Integer cartId: cartIdList) {
            UserCart userCart = userCartRepository.findByCartId(cartId);
            ItemInfo itemInfo = itemInfoRepository.findByItemId(userCart.getItemId());
            ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(userCart.getItemId());
            ItemCategory itemCategory = itemCategoryRepository.findByItemId(userCart.getItemId());
            singleCartVOList.add(new SingleCartVO(
                    userCart.getCartId(),
                    userCart.getItemId(),
                    itemAttribute.getItemBrand(),
                    itemInfo.getItemName(),
                    itemInfo.getItemPic1(),
                    itemAttribute.getCurrentPrice(),
                    itemAttribute.getPreviousPrice(),
                    itemCategory.getTargetGroup(),
                    itemCategory.getUsageStyle(),
                    itemAttribute.getItemColor(),
                    itemAttribute.getItemSize(),
                    userCart.getItemNum(),
                    userCart.getIsValid()
            ));
        }
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for(SingleCartVO singleCartVO: singleCartVOList) {
            totalPrice = totalPrice.add(
                    singleCartVO.getCurrentPrice().multiply(BigDecimal.valueOf(singleCartVO.getItemNum())));
        }
        return new PayingItemsVO(singleCartVOList, totalPrice);
    }
}
