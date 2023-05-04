package com.example.mapper;

import com.example.entity.TransferLine;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransferLineMapper {
    @Select("select * from alltransferline")
    List<TransferLine> findAll();

    @Select("select * from alltransferline where line_num=#{line_num}")
    TransferLine findTransferLineById();
}
