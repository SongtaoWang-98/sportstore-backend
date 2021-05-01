package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.dto.OrderDTO;
import com.stewart.sports_store.entity.*;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.repository.*;
import com.stewart.sports_store.service.OrderService;
import com.stewart.sports_store.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @Autowired
    private UserCartRepository userCartRepository;

    @Override
    public StatusCode createOrder(OrderDTO orderDTO) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();
        List<OrderItems> orderItemsList = orderItemsRepository.getByOrderByItemsIdDesc();
        int newItemsId;
        if(orderItemsList.size() == 0) newItemsId = 0;
        else newItemsId = orderItemsList.get(0).getItemsId() + 1;
        BigDecimal itemsPrice = BigDecimal.valueOf(0);
        for(Integer cartId: orderDTO.getCartIds()) {
            OrderItems orderItems = new OrderItems();
            orderItems.setItemsId(newItemsId);
            orderItems.setCartId(cartId);
            orderItemsRepository.save(orderItems);
            itemsPrice = itemsPrice.add(itemAttributeRepository.findByItemId(
                    userCartRepository.findByCartId(cartId).getItemId()).getCurrentPrice());
            UserCart userCart = userCartRepository.findByCartId(cartId);
            userCart.setIsPaid(true);
            userCartRepository.save(userCart);
        }
        BigDecimal deliveryPrice =
                (orderDTO.getDeliveryType().equals("普通达")?BigDecimal.valueOf(0):BigDecimal.valueOf(30));
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setItemsId(newItemsId);
        orderInfo.setPaymentId(orderDTO.getPaymentId());
        orderInfo.setCreateTime(new Date());
        orderInfo.setItemsPrice(itemsPrice);
        orderInfo.setDeliveryType(orderDTO.getDeliveryType());
        orderInfo.setDeliveryPrice(deliveryPrice);
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
        List<SingleOrderVO> singleOrderVOList = new ArrayList<>();
        List<OrderInfo> orderInfoList = orderInfoRepository.findByUserId(userId);
        for(OrderInfo orderInfo: orderInfoList) {
            List<SingleItemInOrderVO> itemVOList = new ArrayList<>();
            List<OrderItems> orderItemsList = orderItemsRepository.findByItemsId(orderInfo.getItemsId());
            for(OrderItems orderItems: orderItemsList) {
                Integer itemId = userCartRepository.findByCartId(orderItems.getCartId()).getItemId();
                itemVOList.add(new SingleItemInOrderVO(
                        itemId,
                        itemAttributeRepository.findByItemId(itemId).getItemBrand(),
                        itemInfoRepository.findByItemId(itemId).getItemName(),
                        itemInfoRepository.findByItemId(itemId).getItemPic1(),
                        userCartRepository.findByCartId(orderItems.getCartId()).getItemNum(),
                        itemAttributeRepository.findByItemId(itemId).getCurrentPrice()
                ));
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date arrivalTime;
            if(orderInfo.getDeliveryType().equals("普通达")) arrivalTime =
                    new Date(orderInfo.getCreateTime().getTime() + 7 * 24 * 60 * 60 *1000);
            else arrivalTime = new Date(orderInfo.getCreateTime().getTime() + 3 * 24 * 60 * 60 *1000);
            String currentStatus;
            if(new Date().before(arrivalTime)) currentStatus = "派送中";
            else currentStatus = "已签收";
            singleOrderVOList.add(new SingleOrderVO(
                    orderInfo.getOrderId(),
                    itemVOList,
                    orderInfo.getItemsPrice(),
                    orderInfo.getDeliveryType(),
                    orderInfo.getDeliveryPrice(),
                    orderInfo.getItemsPrice().add(orderInfo.getDeliveryPrice()),
                    dateFormat.format(orderInfo.getCreateTime()),
                    dateFormat.format(orderInfo.getCreateTime()),
                    currentStatus
            ));
        }
        return new OrderInfoVO(singleOrderVOList);
    }
}
