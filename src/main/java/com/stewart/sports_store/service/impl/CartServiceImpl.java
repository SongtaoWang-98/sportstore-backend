package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.entity.UserCart;
import com.stewart.sports_store.enums.StatusCode;
import com.stewart.sports_store.repository.*;
import com.stewart.sports_store.service.CartService;
import com.stewart.sports_store.vo.CartVO;
import com.stewart.sports_store.vo.SingleCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public StatusCode addItem(Integer itemId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();
        System.out.println(userId);
        Integer stock = itemAttributeRepository.findByItemId(itemId).getNumberStock();
        if(stock > 0) {
            if(userCartRepository.findByUserIdAndItemId(userId, itemId) != null) {
                return StatusCode.ITEM_EXISTS;
            }
            else{
                UserCart userCart = new UserCart();
                userCart.setUserId(userId);
                userCart.setItemId(itemId);
                userCart.setItemNum(1);
                userCart.setIsValid(true);
                userCartRepository.save(userCart);
                return StatusCode.SUCCESS;
            }
        }
        else return StatusCode.INSUFFICIENT_STOCK;
    }

    @Override
    public StatusCode deleteItem(Integer cartId) {
        userCartRepository.deleteById(cartId);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode updateItem(Integer cartId, Integer num) {
        UserCart userCart = userCartRepository.findByCartId(cartId);
        Integer stock = itemAttributeRepository.findByItemId(userCart.getItemId()).getNumberStock();
        if(num > stock) return StatusCode.INSUFFICIENT_STOCK;
        else {
            userCart.setItemNum(num);
            userCartRepository.save(userCart);
            return StatusCode.SUCCESS;
        }
    }

    @Override
    public CartVO viewCart() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userInfoRepository.findByUserName(userName).getUserId();

        List<UserCart> userCartList = userCartRepository.findByUserId(userId);
        List<SingleCartVO> singleCartVOList = new ArrayList<>();
        for(UserCart userCart: userCartList) {
            ItemInfo itemInfo = itemInfoRepository.findByItemId(userCart.getItemId());
            ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(userCart.getItemId());
            ItemCategory itemCategory = itemCategoryRepository.findByItemId(userCart.getItemId());
            if(itemAttribute.getNumberStock() < userCart.getItemNum()) {
                if(userCart.getIsValid()) {
                    userCart.setIsValid(false);
                    userCartRepository.save(userCart);
                }
            }
            else{
                if(!userCart.getIsValid()) {
                    userCart.setIsValid(true);
                    userCartRepository.save(userCart);
                }
            }
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
        CartVO cartVO = new CartVO();
        cartVO.setCartItemVOList(singleCartVOList);
        cartVO.setGeneralSimpleItemVOList(null);
        return cartVO;
    }
}
