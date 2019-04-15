package com.demo.dao;

import com.demo.entity.QuotationPhenomenonProperty;

public interface QuotationPhenomenonPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuotationPhenomenonProperty record);

    int insertSelective(QuotationPhenomenonProperty record);

    QuotationPhenomenonProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuotationPhenomenonProperty record);

    int updateByPrimaryKey(QuotationPhenomenonProperty record);
}