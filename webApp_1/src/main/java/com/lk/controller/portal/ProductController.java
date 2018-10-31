package com.lk.controller.portal;

import com.github.pagehelper.PageInfo;

import com.lk.common.ServerResponse;

import com.lk.service.ProductService;
import com.lk.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/product/")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "detail.action")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId) {

            return productService.getProductDetail(productId);

    }

    @RequestMapping(value = "list.action")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false) String keyword,
                                         @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderby",defaultValue = "") String orderby){
        return productService.getByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderby);

    }
}
