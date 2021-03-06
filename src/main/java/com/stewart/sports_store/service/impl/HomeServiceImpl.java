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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Override
    public HomeVO findHomeVO() {
        HomeVO homeVO = new HomeVO();
        //分类
        List<GroupVO> groupVOList = new ArrayList<>();
        String[] groupNames = new String[]{"men","women","kids"};
        for(String group: groupNames){
            List<ItemCategory> categoryList = itemCategoryRepository.findByTargetGroup(group);
            HashSet<String> categoryNames = new HashSet<>();
            for(ItemCategory category: categoryList) {
                categoryNames.add(category.getCategoryName());
            }
            HashSet<String> styleNames = new HashSet<>();
            List<CategoryVO> categoryVOList = new ArrayList<>();
            for(String category: categoryNames) {
                List<ItemCategory> list = itemCategoryRepository.findByTargetGroupAndCategoryName(group,category);
                for(ItemCategory item: list) {
                    styleNames.add(item.getUsageStyle());
                }
                categoryVOList.add(new CategoryVO(category, new ArrayList<>(styleNames)));
            }
            GroupVO groupVO = new GroupVO(group,categoryVOList);
            groupVOList.add(groupVO);
        }
        homeVO.setClassificationVOS(groupVOList);

        //海报图片
        homeVO.setPosterImg("../static/poster1.jpg");

        //推荐
        int[] recommendList = new int[]{2,6}; //推荐的itemId
        int recommendItemNumber = recommendList.length;
        List<GeneralSingleItemVO> homeRecommendVOList= new ArrayList<>();
        for (Integer index : recommendList) {
            ItemAttribute recommendItemAttribute = itemAttributeRepository.findByItemId(index);
            ItemCategory recommendItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo recommendItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeRecommendVO = new GeneralSingleItemVO(
                    recommendItemInfo.getItemName(),
                    recommendItemInfo.getItemPic1(),
                    recommendItemAttribute.getCurrentPrice(),
                    recommendItemCategory.getTargetGroup(),
                    recommendItemCategory.getUsageStyle()
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
        int trendingItemNumber = 6; //展示新品的数量
        //按卖出数量排序
        List<ItemAttribute> saleSortList = itemAttributeRepository.getByOrderByNumberSaleDesc();
        List<GeneralSingleItemVO> homeTrendingVOList = new ArrayList<>();
        for(int i = 0; i < trendingItemNumber; i++){
            Integer index = saleSortList.get(i).getItemId();
            ItemCategory trendingItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo trendingItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeTrendingVO = new GeneralSingleItemVO(
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
        List<ItemAttribute> discountList = itemAttributeRepository.findByPreviousPriceIsNotNull();
        List<GeneralDiscountItemVO> generalDiscountItemVOList = new ArrayList<>();
        for(ItemAttribute itemAttribute: discountList) {
            Integer index = itemAttribute.getItemId();
            ItemCategory discountItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo discountItemInfo = itemInfoRepository.findByItemId(index);
            GeneralDiscountItemVO generalDiscountItemVO = new GeneralDiscountItemVO(
                    discountItemInfo.getItemName(),
                    discountItemInfo.getItemPic1(),
                    itemAttribute.getCurrentPrice(),
                    itemAttribute.getPreviousPrice(),
                    discountItemCategory.getTargetGroup(),
                    discountItemCategory.getUsageStyle()
            );
            generalDiscountItemVOList.add(generalDiscountItemVO);
        }
        homeVO.setGeneralDiscountItemVOS(generalDiscountItemVOList);

        //配件
        homeVO.setHomeAccessoriesVO(null);

        //会员海报
        homeVO.setVipPosterImg("../static/vip_poster.jpg");
        return homeVO;
    }
}
