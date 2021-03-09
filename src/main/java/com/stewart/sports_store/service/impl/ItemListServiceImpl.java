package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.ItemListService;
import com.stewart.sports_store.util.TranslatorUtil;
import com.stewart.sports_store.vo.GeneralDiscountItemVO;
import com.stewart.sports_store.vo.GeneralSingleItemVO;
import com.stewart.sports_store.vo.ItemDiscountListVO;
import com.stewart.sports_store.vo.ItemListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "itemListService")
public class ItemListServiceImpl implements ItemListService {

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Override
    @Cacheable(value = "findByCategory")
    public ItemListVO findItemsByCategory(String group, String category, String style) {
        ItemListVO itemListVO = new ItemListVO();
        List<GeneralSingleItemVO> generalSingleItemVOS = new ArrayList<>();
        List<ItemCategory> itemsByCategoryList;
        if(style.equals("all")) {
            if(category.equals("all")) {
                itemsByCategoryList = itemCategoryRepository.findByTargetGroup(TranslatorUtil.TransEnToCh(group));
            }
            else {
                itemsByCategoryList = itemCategoryRepository.findByTargetGroupAndCategoryName(
                        TranslatorUtil.TransEnToCh(group), TranslatorUtil.TransEnToCh(category));
            }
        }
        else itemsByCategoryList = itemCategoryRepository.findByTargetGroupAndCategoryNameAndUsageStyle(
                TranslatorUtil.TransEnToCh(group), TranslatorUtil.TransEnToCh(category),
                TranslatorUtil.TransEnToCh(style));
        for(ItemCategory itemCategory: itemsByCategoryList) {
            Integer index = itemCategory.getItemId();
            ItemInfo itemInfo = itemInfoRepository.findByItemId(index);
            ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(index);
            GeneralSingleItemVO generalSingleItemVO = new GeneralSingleItemVO(
                    index,
                    itemAttribute.getItemBrand(),
                    itemInfo.getItemName(),
                    itemInfo.getItemPic1(),
                    itemAttribute.getCurrentPrice(),
                    itemCategory.getTargetGroup(),
                    itemCategory.getUsageStyle()
            );
            generalSingleItemVOS.add(generalSingleItemVO);
        }
        itemListVO.setSingleItemVOList(generalSingleItemVOS);
        return itemListVO;
    }

    @Override
    @Cacheable(value = "findByBrand")
    public ItemListVO findItemsByBrand(String brand) {
        ItemListVO itemListVO = new ItemListVO();
        List<GeneralSingleItemVO> generalSingleItemVOS = new ArrayList<>();
        List<ItemAttribute> itemsByBrandList = itemAttributeRepository.findByItemBrand(TranslatorUtil.TransEnToCh(brand));
        for(ItemAttribute itemAttribute: itemsByBrandList) {
            Integer index = itemAttribute.getItemId();
            ItemInfo itemInfo = itemInfoRepository.findByItemId(index);
            ItemCategory itemCategory = itemCategoryRepository.findByItemId(index);
            GeneralSingleItemVO generalSingleItemVO = new GeneralSingleItemVO(
                    index,
                    itemAttribute.getItemBrand(),
                    itemInfo.getItemName(),
                    itemInfo.getItemPic1(),
                    itemAttribute.getCurrentPrice(),
                    itemCategory.getTargetGroup(),
                    itemCategory.getUsageStyle()
            );
            generalSingleItemVOS.add(generalSingleItemVO);
        }
        itemListVO.setSingleItemVOList(generalSingleItemVOS);
        return itemListVO;
    }

    @Override
    @Cacheable(value = "findDiscountByCategory")
    public ItemDiscountListVO findDiscountByCategory(String group, String category) {
        ItemDiscountListVO itemListVO = new ItemDiscountListVO();
        List<GeneralDiscountItemVO> generalDiscountItemVOS = new ArrayList<>();
        List<ItemAttribute> itemDiscount = itemAttributeRepository.findByPreviousPriceIsNotNull();
        ArrayList<Integer> discountId = new ArrayList<>();
        for(ItemAttribute itemAttribute: itemDiscount) {
            discountId.add(itemAttribute.getItemId());
        }
        List<ItemCategory> itemsDiscountCategory;
        if(category.equals("all")) itemsDiscountCategory = itemCategoryRepository.findByTargetGroup(TranslatorUtil.TransEnToCh(group));
        else itemsDiscountCategory = itemCategoryRepository.findByTargetGroupAndCategoryName(TranslatorUtil.TransEnToCh(group), TranslatorUtil.TransEnToCh(category));
        for(ItemCategory itemCategory: itemsDiscountCategory) {
            Integer index = itemCategory.getItemId();
            if(discountId.contains(index)) {
                ItemInfo itemInfo = itemInfoRepository.findByItemId(index);
                ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(index);
                GeneralDiscountItemVO generalDiscountItemVO = new GeneralDiscountItemVO(
                        index,
                        itemInfo.getItemName(),
                        itemInfo.getItemPic1(),
                        itemAttribute.getCurrentPrice(),
                        itemAttribute.getPreviousPrice(),
                        itemCategory.getTargetGroup(),
                        itemCategory.getUsageStyle()
                );
                generalDiscountItemVOS.add(generalDiscountItemVO);
            }
        }
        itemListVO.setSingleItemVOList(generalDiscountItemVOS);
        return itemListVO;
    }
}
