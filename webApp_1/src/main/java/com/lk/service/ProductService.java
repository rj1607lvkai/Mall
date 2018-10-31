package com.lk.service;

import com.github.pagehelper.PageInfo;
import com.lk.common.ServerResponse;
import com.lk.pojo.Product;
import com.lk.vo.ProductDetailVo;

public interface ProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize,String orderby);
}
