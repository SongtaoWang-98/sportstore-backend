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
        //组别分类
        List<GroupVO> groupVOList = new ArrayList<>();
        String[] groupNames = new String[]{"男子", "女子", "儿童"};
        String[] categoryNames = new String[]{"鞋类", "服装", "配件"};
        for(String group: groupNames){
            List<CategoryVO> categoryVOList = new ArrayList<>();
            for(String category: categoryNames) {
                HashSet<String> styleNames = new HashSet<>();
                List<ItemCategory> list = itemCategoryRepository.findByTargetGroupAndCategoryName(group,category);
                for(ItemCategory item: list) {
                    styleNames.add(item.getUsageStyle());
                }
                categoryVOList.add(new CategoryVO(category, new ArrayList<>(styleNames)));
            }
            groupVOList.add(new GroupVO(group,categoryVOList));
        }
        //品牌分类
        List<ItemAttribute> allItemAttributes = itemAttributeRepository.findAll();
        HashSet<String> brandNames = new HashSet<>();
        for(ItemAttribute attribute: allItemAttributes) {
            brandNames.add(attribute.getItemBrand());
        }
        List<CategoryVO> collectionVOList = new ArrayList<>();
        collectionVOList.add(new CategoryVO("品牌",new ArrayList<>(brandNames)));
        groupVOList.add(new GroupVO("分类",collectionVOList));
        //热卖分类
        List<CategoryVO> saleVOList = new ArrayList<>();
        for(String group: groupNames) {
            saleVOList.add(new CategoryVO( group + "热卖", Arrays.asList(categoryNames)));
        }
        groupVOList.add(new GroupVO("热卖",saleVOList));
        homeVO.setClassificationVOS(groupVOList);

        //海报图片
        homeVO.setPosterImg("../static/poster1.jpg");

        //推荐（精选）
        int[] recommendList = new int[]{8,6}; //推荐的itemId
        List<GeneralSingleItemVO> homeRecommendVOList= new ArrayList<>();
        for (Integer index : recommendList) {
            ItemAttribute recommendItemAttribute = itemAttributeRepository.findByItemId(index);
            ItemCategory recommendItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo recommendItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeRecommendVO = new GeneralSingleItemVO(
                    recommendItemAttribute.getItemBrand(),
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
        int trendingItemNumber = 6; //展示新品的数量
        //按卖出数量排序
        List<ItemAttribute> saleSortList = itemAttributeRepository.getByOrderByNumberSaleDesc();
        List<GeneralSingleItemVO> homeTrendingVOList = new ArrayList<>();
        for(int i = 0; i < trendingItemNumber; i++){
            Integer index = saleSortList.get(i).getItemId();
            ItemCategory trendingItemCategory = itemCategoryRepository.findByItemId(index);
            ItemInfo trendingItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO homeTrendingVO = new GeneralSingleItemVO(
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
        List<GeneralSingleItemVO> homeAccessoriesList = new ArrayList<>();
        for(int i = 0; i < accessoriesItemNumber; i++) {
            Integer index = accessoriesList.get(accessoriesList.size() - i - 1).getItemId();
            ItemAttribute accessoriesItemAttribute = itemAttributeRepository.findByItemId(index);
            ItemInfo accessoriesItemInfo = itemInfoRepository.findByItemId(index);
            GeneralSingleItemVO generalSingleItemVO = new GeneralSingleItemVO(
                    accessoriesItemAttribute.getItemBrand(),
                    accessoriesItemInfo.getItemName(),
                    accessoriesItemInfo.getItemPic1(),
                    accessoriesItemAttribute.getCurrentPrice(),
                    accessoriesList.get(i).getTargetGroup(),
                    accessoriesList.get(i).getUsageStyle()
            );
            homeAccessoriesList.add(generalSingleItemVO);
        }
        homeVO.setHomeAccessoriesVO(homeAccessoriesList);

        //会员海报
        homeVO.setVipPosterImg("../static/vip_poster.jpg");

        return homeVO;
    }
}
