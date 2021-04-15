package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.OrderDTO;
import com.stewart.sports_store.dto.OrderItemDTO;
import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.entity.OrderInfo;
import com.stewart.sports_store.entity.OrderItems;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.repository.*;
import com.stewart.sports_store.service.OrderService;
import com.stewart.sports_store.vo.OrderInfoVO;
import com.stewart.sports_store.vo.SingleItemInOrderVO;
import com.stewart.sports_store.vo.SingleOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Override
    public StatusCode createOrder(OrderDTO orderDTO) {
        int i;
        List<OrderItems> orderItemsList = orderItemsRepository.getByOrderByItemsIdDesc();
        if(orderItemsList == null) i = 1;
        else i = orderItemsList.get(0).getItemsId() + 1;

        List<OrderItemDTO> orderItemDTOList = orderDTO.getOrderItemDTOList();
        for(OrderItemDTO orderItemDTO: orderItemDTOList) {
            OrderItems orderItems = new OrderItems();
            orderItems.setItemsId(i);
            orderItems.setItemId(orderItemDTO.getItemId());
            orderItems.setNum(orderItemDTO.getNum());
            orderItemsRepository.save(orderItems);
        }

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setItemsId(i);
        orderInfo.setPaymentId(orderDTO.getPaymentId());
        orderInfo.setCreateTime(new Date());
        orderInfo.setItemsPrice(orderDTO.getItemsPrice());
        orderInfo.setDeliveryType(orderDTO.getDeliveryType());
        orderInfo.setDeliveryPrice(orderDTO.getDeliveryPrice());
        orderInfo.setPaymentMethod(orderDTO.getPaymentMethod());
        orderInfoRepository.save(orderInfo);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode deleteOrder(Integer orderId) {
        orderInfoRepository.deleteById(orderId);
        return StatusCode.SUCCESS;
    }

    @Override
    public OrderInfoVO viewOrder() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();

        List<OrderInfo> orderInfoList = orderInfoRepository.findByUserId(userId);
        List<SingleOrderVO> singleOrderVOList = new ArrayList<>();
        for(OrderInfo orderInfo:orderInfoList) {
            List<OrderItems> orderItemsList = orderItemsRepository.findByItemsId(orderInfo.getItemsId());
            List<SingleItemInOrderVO> singleItemInOrderVOList = new ArrayList<>();
            for(OrderItems orderItems: orderItemsList) {
                ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(orderItems.getItemId());
                ItemInfo itemInfo = itemInfoRepository.findByItemId(orderItems.getItemId());
                singleItemInOrderVOList.add(new SingleItemInOrderVO(
                        orderItems.getItemId(),
                        itemAttribute.getItemBrand(),
                        itemInfo.getItemName(),
                        itemInfo.getItemPic1(),
                        orderItems.getNum(),
                        itemAttribute.getCurrentPrice()
                ));
            }

            Date now = new Date();
            Date date = orderInfo.getCreateTime();
            Date arrDate;
            if(orderInfo.getDeliveryType().equals("普通达")) arrDate = new Date(date.getTime() + 5 * 24 * 60 * 60 * 1000);
            else arrDate = new Date(date.getTime() + 2 * 24 * 60 * 60 * 1000);

            String status;
            if(arrDate.before(now)) status = "正在路上";
            else status = "已签收";

            singleOrderVOList.add(new SingleOrderVO(
               orderInfo.getOrderId(),
               singleItemInOrderVOList,
               orderInfo.getItemsPrice(),
               orderInfo.getDeliveryType(),
               orderInfo.getDeliveryPrice(),
               orderInfo.getItemsPrice().add(orderInfo.getDeliveryPrice()),
               date, arrDate, status
            ));
        }
        return new OrderInfoVO(singleOrderVOList);
    }
}
