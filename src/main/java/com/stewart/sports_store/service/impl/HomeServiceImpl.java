package com.stewart.sports_store.service.impl;

import com.stewart.sports_store.entity.ItemAttribute;
import com.stewart.sports_store.entity.ItemCategory;
import com.stewart.sports_store.entity.ItemInfo;
import com.stewart.sports_store.repository.ItemAttributeRepository;
import com.stewart.sports_store.repository.ItemCategoryRepository;
import com.stewart.sports_store.repository.ItemInfoRepository;
import com.stewart.sports_store.service.HomeService;
import com.stewart.sports_store.vo.*;
import io.netty.channel.ChannelHandler;
import org.hibernate.annotations.Cascade;
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

        //新品
        int newItemNumber = 6; //展示新品的数量
        //按上架时间排序
        List<ItemAttribute> timeSortList = itemAttributeRepository.getByOrderByUpdateTimeDesc();
        List<GeneralSingleItemVO> homeNewVOList = new ArrayList<>();
        for(int i = 0; i < newItemNumber; i++){
            Integer index = timeSortList.get(i).getItemId();
            ItemCategory newItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo newItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeNewVO = new GeneralSingleItemVO(
                    index,
                    timeSortList.get(i).getItemBrand(),
                    newItemInfo.getItemName(),
                    newItemInfo.getItemPic1(),
                    timeSortList.get(i).getCurrentPrice(),
                    newItemCategory.getTargetGroup(),
                    newItemCategory.getUsageStyle()
            );
            homeNewVOList.add(homeNewVO);
        }
        homeVO.setHomeNewVO(homeNewVOList);

        //热销
        int trendingItemNumber = 6; //展示的数量
        //按卖出数量排序
        List<ItemAttribute> saleSortList = itemAttributeRepository.getByOrderByNumberSaleDesc();
        List<GeneralSingleItemVO> homeTrendingVOList = new ArrayList<>();
        for(int i = 0; i < trendingItemNumber; i++){
            Integer index = saleSortList.get(i).getItemId();
            ItemCategory trendingItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo trendingItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeTrendingVO = new GeneralSingleItemVO(
                    index,
                    saleSortList.get(i).getItemBrand(),
                    trendingItemInfo.getItemName(),
                    trendingItemInfo.getItemPic1(),
                    saleSortList.get(i).getCurrentPrice(),
                    trendingItemCategory.getTargetGroup(),
                    trendingItemCategory.getUsageStyle()
            );
            homeTrendingVOList.add(homeTrendingVO);
        }
        homeVO.setHomeTrendingVO(homeTrendingVOList);

        //折扣
        int discountItemNumber = 3;
        List<ItemAttribute> discountList = itemAttributeRepository.findByPreviousPriceIsNotNull();
        List<GeneralDiscountItemVO> generalDiscountItemVOList = new ArrayList<>();
        for(int i = 0; i < discountItemNumber; i++) {
            Integer index = discountList.get(discountList.size() - i - 1).getItemId();
            ItemCategory discountItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo discountItemInfo = itemInfoRepository.findByItemId(index);
            GeneralDiscountItemVO generalDiscountItemVO = new GeneralDiscountItemVO(
                    index,
                    discountItemInfo.getItemName(),
                    discountItemInfo.getItemPic1(),
                    discountList.get(i).getCurrentPrice(),
                    discountList.get(i).getPreviousPrice(),
                    discountItemCategory.getTargetGroup(),
                    discountItemCategory.getUsageStyle()
            );
            generalDiscountItemVOList.add(generalDiscountItemVO);
        }
        homeVO.setGeneralDiscountItemVOS(generalDiscountItemVOList);

        //配件
        int accessoriesItemNumber = 2;
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
