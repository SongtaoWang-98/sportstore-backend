package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.ItemListService;
import com.stewart.sports_store.util.TranslatorUtil;
import com.stewart.sports_store.vo.GeneralDetailedItemVO;
import com.stewart.sports_store.vo.ItemListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "homeService")
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
        List<GeneralDetailedItemVO> generalDetailedItemVOS = new ArrayList<>();
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
            GeneralDetailedItemVO generalDetailedItemVO = new GeneralDetailedItemVO(
                    index,
                    itemAttribute.getItemBrand(),
                    itemInfo.getItemName(),
                    itemInfo.getItemPic1(),
                    itemInfo.getItemPic2(),
                    itemInfo.getItemPic3(),
                    itemAttribute.getCurrentPrice(),
                    itemAttribute.getPreviousPrice(),
                    itemCategory.getTargetGroup(),
                    itemCategory.getUsageStyle()
            );
            generalDetailedItemVOS.add(generalDetailedItemVO);
        }
        itemListVO.setDetailedItemVOList(generalDetailedItemVOS);
        return itemListVO;
    }

    @Override
    @Cacheable(value = "findByBrand")
    public ItemListVO findItemsByBrand(String brand) {
        ItemListVO itemListVO = new ItemListVO();
        List<GeneralDetailedItemVO> generalDetailedItemVOS = new ArrayList<>();
        List<ItemAttribute> itemsByBrandList = itemAttributeRepository.findByItemBrand
                (TranslatorUtil.TransEnToCh(brand));
        for(ItemAttribute itemAttribute: itemsByBrandList) {
            Integer index = itemAttribute.getItemId();
            ItemInfo itemInfo = itemInfoRepository.findByItemId(index);
            ItemCategory itemCategory = itemCategoryRepository.findByItemId(index);
            GeneralDetailedItemVO generalDetailedItemVO = new GeneralDetailedItemVO(
                    index,
                    itemAttribute.getItemBrand(),
                    itemInfo.getItemName(),
                    itemInfo.getItemPic1(),
                    itemInfo.getItemPic2(),
                    itemInfo.getItemPic3(),
                    itemAttribute.getCurrentPrice(),
                    itemAttribute.getPreviousPrice(),
                    itemCategory.getTargetGroup(),
                    itemCategory.getUsageStyle()
            );
            generalDetailedItemVOS.add(generalDetailedItemVO);
        }
        itemListVO.setDetailedItemVOList(generalDetailedItemVOS);
        return itemListVO;
    }

    @Override
    @Cacheable(value = "findDiscountByCategory")
    public ItemListVO findDiscountByCategory(String group, String category) {
        ItemListVO itemListVO = new ItemListVO();
        List<GeneralDetailedItemVO> generalDetailedItemVOS = new ArrayList<>();
        List<ItemAttribute> itemDiscount = itemAttributeRepository.findByPreviousPriceIsNotNull();
        ArrayList<Integer> discountId = new ArrayList<>();
        for(ItemAttribute itemAttribute: itemDiscount) {
            discountId.add(itemAttribute.getItemId());
        }
        List<ItemCategory> itemsDiscountCategory;
        if(category.equals("all")) itemsDiscountCategory = itemCategoryRepository.findByTargetGroup
                (TranslatorUtil.TransEnToCh(group));
        else itemsDiscountCategory = itemCategoryRepository.findByTargetGroupAndCategoryName
                (TranslatorUtil.TransEnToCh(group), TranslatorUtil.TransEnToCh(category));
        for(ItemCategory itemCategory: itemsDiscountCategory) {
            Integer index = itemCategory.getItemId();
            if(discountId.contains(index)) {
                ItemInfo itemInfo = itemInfoRepository.findByItemId(index);
                ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(index);
                GeneralDetailedItemVO generalDetailedItemVO = new GeneralDetailedItemVO(
                        index,
                        itemAttribute.getItemBrand(),
                        itemInfo.getItemName(),
                        itemInfo.getItemPic1(),
                        itemInfo.getItemPic2(),
                        itemInfo.getItemPic3(),
                        itemAttribute.getCurrentPrice(),
                        itemAttribute.getPreviousPrice(),
                        itemCategory.getTargetGroup(),
                        itemCategory.getUsageStyle()
                );
                generalDetailedItemVOS.add(generalDetailedItemVO);
            }
        }
        itemListVO.setDetailedItemVOList(generalDetailedItemVOS);
        return itemListVO;
    }
}
