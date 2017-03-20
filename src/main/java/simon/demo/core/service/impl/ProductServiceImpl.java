package simon.demo.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import simon.demo.core.bean.Product;
import simon.demo.core.bean.ProductExample.Criteria;
import simon.demo.core.bean.ProductExample;
import simon.demo.core.dao.ProductMapper;
import simon.demo.core.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    public int deleteByPrimaryKey(Integer id) throws Exception {
        return productMapper.deleteByPrimaryKey(id);
    }

    public int insert(Product record) throws Exception {
        return productMapper.insertSelective(record);
    }

    public Map<String,Object> findByPage(Product record) throws Exception {
    	Assert.notNull(record,"产品查询，参数不能为空！");
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        ProductExample example=new ProductExample();
//        example.setOrderByClause("id desc");
        Criteria criteria = example.createCriteria();
        
        if(StringUtils.isNotEmpty(record.getId())){
        	criteria.andIdEqualTo(record.getId());
        }
        
        if(StringUtils.isNotEmpty(record.getAttr())){
        	criteria.andAttrEqualTo(record.getAttr());
        }
        if(StringUtils.isNotEmpty(record.getProduct())){
        	criteria.andProductEqualTo(record.getProduct());
        }
        if(StringUtils.isNotEmpty(record.getStatus())){
        	criteria.andStatusEqualTo(record.getStatus());
        }
        if(StringUtils.isNotEmpty(record.getUnit())){
        	criteria.andUnitEqualTo(record.getUnit());
        }
        
        //具体条件查询请自行处理！！！
        map.put("total", productMapper.countAll());
        map.put("rows", productMapper.selectByExampleWithPage(example,record.getStart(),record.getLimit()));
        return map;
    }

    public int updateByPrimaryKeySelective(Product record) throws Exception {
        return productMapper.updateByPrimaryKeySelective(record);
    }


}