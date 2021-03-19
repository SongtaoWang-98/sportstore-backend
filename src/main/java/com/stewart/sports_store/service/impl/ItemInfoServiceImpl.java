package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.ItemInfoService;
import com.stewart.sports_store.vo.DetailedInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public DetailedInformationVO showInformationById(int id) {
        ItemInfo itemInfo = itemInfoRepository.findByItemId(id);
        ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(id);
        ItemCategory itemCategory = itemCategoryRepository.findByItemId(id);
        return new DetailedInformationVO(
                id,
                itemInfo.getItemName(),
                itemInfo.getItemPic1(),
                itemInfo.getItemPic2(),
                itemInfo.getItemPic3(),
                itemCategory.getTargetGroup(),
                itemCategory.getCategoryName(),
                itemCategory.getUsageStyle(),
                itemAttribute.getItemColor(),
                itemAttribute.getItemSize(),
                itemAttribute.getCurrentPrice(),
                itemAttribute.getPreviousPrice(),
                itemAttribute.getItemBrand()
        );
    }
}
