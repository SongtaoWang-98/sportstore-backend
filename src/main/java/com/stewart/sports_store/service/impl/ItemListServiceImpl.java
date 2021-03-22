package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.ItemListService;
import com.stewart.sports_store.util.TranslatorUtil_List;
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
    @Cacheable(value = "select")
    public ItemListVO findItemsByConditions(List<String> groups, List<String> categories, List<String> styles,
                                              List<String> brands, List<String> colors, Boolean discount) {
        ItemListVO itemListVO = new ItemListVO();
        List<GeneralDetailedItemVO> generalDetailedItemVOS = new ArrayList<>();
        List<ItemCategory> findByCategoryList = new ArrayList<>();
        if(groups != null && categories != null && styles != null) {
            findByCategoryList = itemCategoryRepository.findByTargetGroupInAndCategoryNameInAndUsageStyleIn(
                    TranslatorUtil_List.TransList(groups),
                    TranslatorUtil_List.TransList(categories),
                    TranslatorUtil_List.TransList(styles));
        }
        if(styles == null) {
            if(groups != null && categories != null) {
                findByCategoryList = itemCategoryRepository.findByTargetGroupInAndCategoryNameIn
                        (TranslatorUtil_List.TransList(groups), TranslatorUtil_List.TransList(categories));
            }
            else if(groups == null && categories != null) {
                findByCategoryList = itemCategoryRepository.findByCategoryNameIn(TranslatorUtil_List.TransList(categories));
            }
            else if(groups != null ) {
                findByCategoryList = itemCategoryRepository.findByTargetGroupIn(TranslatorUtil_List.TransList(groups));
            }
            else findByCategoryList = itemCategoryRepository.findAll();
        }

        List<ItemAttribute> findByAttributeList;
        if(discount != null) {
            if(brands != null && colors != null) {
                findByAttributeList = itemAttributeRepository.findByItemBrandInAndItemColorInAndPreviousPriceIsNotNull(
                        TranslatorUtil_List.TransList(brands), TranslatorUtil_List.TransList(colors));
            }
            else if(brands == null && colors != null) {
                findByAttributeList = itemAttributeRepository.findByItemColorInAndPreviousPriceIsNotNull(
                        TranslatorUtil_List.TransList(colors));
            }
            else if(brands != null) {
                findByAttributeList = itemAttributeRepository.findByItemBrandInAndPreviousPriceIsNotNull(
                        TranslatorUtil_List.TransList(brands));
            }
            else {
                findByAttributeList = itemAttributeRepository.findByPreviousPriceIsNotNull();
            }
        }
        else {
            if(brands != null && colors != null) {
                findByAttributeList = itemAttributeRepository.findByItemBrandInAndItemColorIn
                        (TranslatorUtil_List.TransList(brands), TranslatorUtil_List.TransList(colors));
            }
            else if(brands == null && colors != null) {
                findByAttributeList = itemAttributeRepository.findByItemColorIn(TranslatorUtil_List.TransList(colors));
            }
            else if(brands != null) {
                findByAttributeList = itemAttributeRepository.findByItemBrandIn(TranslatorUtil_List.TransList(brands));
            }
            else {
                findByAttributeList = itemAttributeRepository.findAll();
            }
        }

        List<Integer> categoryId = new ArrayList<>();
        List<Integer> resultId = new ArrayList<>();
        for(ItemCategory itemCategory: findByCategoryList) {
            categoryId.add(itemCategory.getItemId());
        }
        for(ItemAttribute itemAttribute: findByAttributeList) {
            if (categoryId.contains(itemAttribute.getItemId())) {
                resultId.add(itemAttribute.getItemId());
            }
        }
        int itemNumber = 0;
        for(Integer id: resultId) {
            itemNumber++;
            ItemInfo itemInfo = itemInfoRepository.findByItemId(id);
            ItemCategory itemCategory = itemCategoryRepository.findByItemId(id);
            ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(id);
            GeneralDetailedItemVO generalDetailedItemVO = new GeneralDetailedItemVO(
                    id,
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
        itemListVO.setNumber(itemNumber);
        itemListVO.setDetailedItemVOList(generalDetailedItemVOS);
        return itemListVO;
    }
}
