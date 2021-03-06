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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<HomeRecommendVO> homeRecommendVOList= new ArrayList<>();
        for(int i = 0; i < recommendItemNumber; i++) {
            Integer index = recommendList[i];
            ItemAttribute recommendItemAttribute = itemAttributeRepository.findByItemId(index);
            ItemCategory recommendItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo recommendItemInfo = itemInfoRepository.findByItemId(index);
            HomeRecommendVO homeRecommendVO = new HomeRecommendVO(
                    recommendItemInfo.getItemName(),
                    recommendItemInfo.getItemPic1(),
                    recommendItemAttribute.getCurrentPrice(),
                    recommendItemCategory.getTargetGroup(),
                    recommendItemCategory.getUsageStyle()
            );
            homeRecommendVOList.add(homeRecommendVO);
        }
        homeVO.setHomeRecommendVOS(homeRecommendVOList);

        //新品
        int newItemNumber = 6; //展示新品的数量
        //按上架时间排序
        List<ItemAttribute> timeSortList = itemAttributeRepository.getByOrderByUpdateTimeDesc();
        List<HomeNewVO> homeNewVOList = new ArrayList<>();
        for(int i = 0; i < newItemNumber; i++){
            Integer index = timeSortList.get(i).getItemId();
            ItemCategory newItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo newItemInfo = itemInfoRepository.findByItemId(index);
            HomeNewVO homeNewVO = new HomeNewVO(
                    newItemInfo.getItemName(),
                    newItemInfo.getItemPic1(),
                    timeSortList.get(i).getCurrentPrice(),
                    newItemCategory.getTargetGroup(),
                    newItemCategory.getUsageStyle()
            );
            homeNewVOList.add(homeNewVO);
        }
        homeVO.setHomeNewVOS(homeNewVOList);

        //热销
        int trendingItemNumber = 6; //展示新品的数量
        //按卖出数量排序
        Sort saleSort = Sort.by(Sort.Direction.DESC, "numberSale");
        List<ItemAttribute> saleSortList = itemAttributeRepository.getByOrderByNumberSaleDesc();
        List<HomeTrendingVO> homeTrendingVOList = new ArrayList<>();
        for(int i = 0; i < trendingItemNumber; i++){
            Integer index = saleSortList.get(i).getItemId();
            ItemCategory trendingItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo trendingItemInfo = itemInfoRepository.findByItemId(index);
            HomeTrendingVO homeTrendingVO = new HomeTrendingVO(
                    trendingItemInfo.getItemName(),
                    trendingItemInfo.getItemPic1(),
                    saleSortList.get(i).getCurrentPrice(),
                    trendingItemCategory.getTargetGroup(),
                    trendingItemCategory.getUsageStyle()
            );
            homeTrendingVOList.add(homeTrendingVO);
        }
        homeVO.setHomeTrendingVOS(homeTrendingVOList);

        //折扣
        List<ItemAttribute> discountList = itemAttributeRepository.findByPreviousPriceIsNotNull();
        List<HomeDiscountVO> homeDiscountVOList = new ArrayList<>();
        for(ItemAttribute itemAttribute: discountList) {
            Integer index = itemAttribute.getItemId();
            ItemCategory discountItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo discountItemInfo = itemInfoRepository.findByItemId(index);
            HomeDiscountVO homeDiscountVO = new HomeDiscountVO(
                    discountItemInfo.getItemName(),
                    discountItemInfo.getItemPic1(),
                    itemAttribute.getCurrentPrice(),
                    itemAttribute.getPreviousPrice(),
                    discountItemCategory.getTargetGroup(),
                    discountItemCategory.getUsageStyle()
            );
            homeDiscountVOList.add(homeDiscountVO);
        }
        homeVO.setHomeDiscountVOS(homeDiscountVOList);

        //组别分类
        List<HomeSimpleGroupVO> homeSimpleGroupVOList = new ArrayList<>();
        homeSimpleGroupVOList.add(new HomeSimpleGroupVO("men", "../static/home-group-men.jpg"));
        homeSimpleGroupVOList.add(new HomeSimpleGroupVO("women", "../static/home-group-women.jpg"));
        homeSimpleGroupVOList.add(new HomeSimpleGroupVO("kids", "../static/home-group-kids.jpg"));
        homeVO.setHomeSimpleGroupVOS(homeSimpleGroupVOList);

        return homeVO;
    }
}
