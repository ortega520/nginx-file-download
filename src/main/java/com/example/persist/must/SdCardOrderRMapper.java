package com.example.persist.must;

import java.util.List;

import com.example.domain.SdCardOrder;

public interface SdCardOrderRMapper {

	public List<SdCardOrder> selectAll();

	public List<SdCardOrder> selectByUserId(long userId);

}
