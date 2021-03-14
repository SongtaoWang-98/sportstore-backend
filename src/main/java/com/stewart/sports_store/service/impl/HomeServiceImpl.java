package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.HomeService;
import com.stewart.sports_store.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@CacheConfig(cacheNames = "homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Override
    @Cacheable(value = "findHomeVO")
    public HomeVO findHomeVO() {
        HomeVO homeVO = new HomeVO();

        //海报图片
        homeVO.setPosterImg("../static/poster1.jpg");

        //热卖
        int trendingItemNumber = 6; //展示的数量
        //按卖出数量排序
        List<ItemAttribute> saleSortList = itemAttributeRepository.getByOrderByNumberSaleDesc();
        List<GeneralSingleItemVO> homeTrendingVOList = new ArrayList<>();
        for(int i = 0; i < trendingItemNumber; i++){
            Integer index = saleSortList.get(i).getItemId();
            ItemCategory trendingItemCategory = itemCategoryRepository.findByItemId(index);
            ItemAttribute trendingItemAttribute = itemAttributeRepository.findByItemId(index);
            ItemInfo trendingItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeTrendingVO = new GeneralSingleItemVO(
                    index,
                    trendingItemAttribute.getItemBrand(),
                    trendingItemInfo.getItemName(),
                    trendingItemInfo.getItemPic1(),
                    trendingItemAttribute.getCurrentPrice(),
                    trendingItemAttribute.getPreviousPrice(),
                    trendingItemCategory.getTargetGroup(),
                    trendingItemCategory.getUsageStyle()
            );
            homeTrendingVOList.add(homeTrendingVO);
        }
        homeVO.setHomeTrendingVO(homeTrendingVOList);

        //最新上架
        int newItemNumber = 2; //展示新品的数量
        //按上架时间排序
        List<ItemAttribute> timeSortList = itemAttributeRepository.getByOrderByUpdateTimeDesc();
        List<GeneralSimpleItemVO> homeNewVOList = new ArrayList<>();
        for(int i = 0; i < newItemNumber; i++){
            Integer index = timeSortList.get(i).getItemId();
            ItemInfo newItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSimpleItemVO homeNewVO = new GeneralSimpleItemVO(
                    index,
                    newItemInfo.getItemPic1()
                    );
            homeNewVOList.add(homeNewVO);
        }
        homeVO.setHomeNewVO(homeNewVOList);

        //特价商品
        int discountItemNumber = 6;
        List<ItemAttribute> discountList = itemAttributeRepository.findByPreviousPriceIsNotNull();
        List<GeneralSingleItemVO> generalDiscountItemVOList = new ArrayList<>();
        for(int i = 0; i < discountItemNumber; i++) {
            Integer index = discountList.get(discountList.size() - i - 1).getItemId();
            ItemCategory discountItemCategory = itemCategoryRepository.findByItemId(index);
            ItemAttribute discountItemAttribute = itemAttributeRepository.findByItemId(index);
            ItemInfo discountItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO generalDiscountItemVO = new GeneralSingleItemVO(
                    index,
                    discountItemAttribute.getItemBrand(),
                    discountItemInfo.getItemName(),
                    discountItemInfo.getItemPic1(),
                    discountItemAttribute.getCurrentPrice(),
                    discountItemAttribute.getPreviousPrice(),
                    discountItemCategory.getTargetGroup(),
                    discountItemCategory.getUsageStyle()
            );
            generalDiscountItemVOList.add(generalDiscountItemVO);
        }
        homeVO.setGeneralDiscountItemVOS(generalDiscountItemVOList);

        //推荐（精选）
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int first = rand.nextInt(10);
        int second = rand.nextInt(10);
        while(second == first) second = rand.nextInt(10);
        int[] recommendList = new int[]{first,second}; //推荐的itemId
        List<GeneralSimpleItemVO> homeRecommendVOList= new ArrayList<>();
        for (Integer index : recommendList) {
            GeneralSimpleItemVO homeRecommendVO = new GeneralSimpleItemVO(
                    index,
                    itemInfoRepository.findByItemId(index).getItemPic1()
            );
            homeRecommendVOList.add(homeRecommendVO);
        }
        homeVO.setHomeRecommendVO(homeRecommendVOList);

        //配件
        int accessoriesItemNumber = 3;
        List<String> categories = new ArrayList<>();
        Collections.addAll(categories,"鞋类","服装");
        List<ItemCategory> accessoriesList = itemCategoryRepository.findByCategoryNameNotIn(categories);
        List<GeneralSimpleItemVO> homeAccessoriesList = new ArrayList<>();
        for(int i = 0; i < accessoriesItemNumber; i++) {
            Integer index = accessoriesList.get(accessoriesList.size() - i - 1).getItemId();
            ItemInfo accessoriesItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSimpleItemVO generalSimpleItemVO = new GeneralSimpleItemVO(
                    index,
                    accessoriesItemInfo.getItemPic1()
            );
            homeAccessoriesList.add(generalSimpleItemVO);
        }
        homeVO.setHomeAccessoriesVO(homeAccessoriesList);

        //会员海报
        homeVO.setVipPosterImg("../static/vip_poster.jpg");

        return homeVO;
    }
}
