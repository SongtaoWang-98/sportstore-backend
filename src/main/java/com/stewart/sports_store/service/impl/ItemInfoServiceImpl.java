package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.ItemInfoService;
import com.stewart.sports_store.vo.DetailedInformationVO;
import com.stewart.sports_store.vo.GeneralSingleItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        double randomRating = (double) rand.nextInt(50) / 10;
        ItemInfo itemInfo = itemInfoRepository.findByItemId(id);
        ItemAttribute itemAttribute = itemAttributeRepository.findByItemId(id);
        ItemCategory itemCategory = itemCategoryRepository.findByItemId(id);
        List<GeneralSingleItemVO> relatedItems = new ArrayList<>();
        List<ItemAttribute> itemAttributes = itemAttributeRepository.findByItemBrand(itemAttribute.getItemBrand());
        int num = 0;
        for(ItemAttribute i: itemAttributes) {
            if(!i.getItemId().equals(itemAttribute.getItemId())) {
                ItemInfo info = itemInfoRepository.findByItemId(i.getItemId());
                ItemCategory category = itemCategoryRepository.findByItemId(i.getItemId());
                relatedItems.add(new GeneralSingleItemVO(
                        i.getItemId(),
                        i.getItemBrand(),
                        info.getItemName(),
                        info.getItemPic1(),
                        i.getCurrentPrice(),
                        i.getPreviousPrice(),
                        category.getTargetGroup(),
                        category.getUsageStyle()
                        ));
                num++;
            }
            if(num >= 6) break;
        }
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
                itemAttribute.getNumberStock(),
                itemAttribute.getNumberSale(),
                itemAttribute.getItemBrand(),
                randomRating,
                relatedItems
        );
    }
}
