package com.lk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lk.common.ResponseCode;
import com.lk.common.ServerResponse;
import com.lk.dao.CategoryMapper;
import com.lk.pojo.Category;
import com.lk.service.CategoryService;
import com.lk.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ServerResponse addCategory(String categoryName,Integer parentId){
        if(StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("m名字不能为空");

        }
        Category category=new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int rowCount=categoryMapper.insert(category);
        if(rowCount>0){
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }
    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName){
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Category category=new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rowCount=categoryMapper.updateCategory(category);
        if(rowCount>0){
            return ServerResponse.createBySuccess("更新名称成功");
        }
        return ServerResponse.createByErrorMessage("更新名称失败");
    }
    public ServerResponse getChildrenParallelCategory(Integer categoryId){
        List<Category> categoryList=categoryMapper.getCategoryChildrenByParentId(categoryId);
        return ServerResponse.createBySuccess(categoryList);
    }
    @Override
    public ServerResponse<List<Integer>> getAndDeepChildrenCategory(Integer categoryId){
        Set<Category> categorySet= Sets.newHashSet();
        findChildrenCategory(categorySet,categoryId);
        List<Integer> categoryIdList= Lists.newArrayList();
        if (categoryId != null){
            for (Category categoryItem:categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);

    }
    private Set<Category> findChildrenCategory(Set<Category> categorySet,Integer categoryId){
        Category category=(Category)categoryMapper.selectById(categoryId);
        if (category != null){

            categorySet.add(category);
        }
        List<Category> categoryList=categoryMapper.getCategoryChildrenByParentId(categoryId);
        for (Category categoryItem:categoryList){
            findChildrenCategory(categorySet,categoryItem.getId());
        }
        return categorySet;

    }

}
