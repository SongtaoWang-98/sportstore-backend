package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.ItemListFilterService;
import com.stewart.sports_store.util.TranslatorUtil_List;
import com.stewart.sports_store.vo.GeneralDetailedItemVO;
import com.stewart.sports_store.vo.ItemListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@CacheConfig(cacheNames = "homeService")
public class ItemListFilterServiceImpl implements ItemListFilterService {

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    @Cacheable(value = "filter")
    public ItemListVO filterItemsByConditions(List<String> groups, List<String> categories,
                                              List<String> brands, List<String> colors) {
        ItemListVO itemListVO = new ItemListVO();
        List<GeneralDetailedItemVO> generalDetailedItemVOS = new ArrayList<>();
        List<ItemAttribute> filterByAttributeList = itemAttributeRepository.findByItemBrandInAndItemColorIn
                (TranslatorUtil_List.TransList(brands), TranslatorUtil_List.TransList(colors));
        List<ItemCategory> filterByCategoryList = itemCategoryRepository.findByTargetGroupInAndCategoryNameIn
                (TranslatorUtil_List.TransList(groups), TranslatorUtil_List.TransList(categories));
        List<Integer> categoryId = new ArrayList<>();
        List<Integer> resultId = new ArrayList<>();
        for(ItemCategory itemCategory: filterByCategoryList) {
            categoryId.add(itemCategory.getItemId());
        }
        for(ItemAttribute itemAttribute: filterByAttributeList) {
            if (categoryId.contains(itemAttribute.getItemId())) {
                resultId.add(itemAttribute.getItemId());
            }
        }
        for(Integer id: resultId) {
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
        itemListVO.setDetailedItemVOList(generalDetailedItemVOS);
        return itemListVO;
    }
}
