package com.ciba.datasynchronize.uploader;

import com.ciba.datasynchronize.entity.ProcessData;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/11
 */
public interface ProcessDataUploader {
    void uploadProcessData(List<ProcessData> processDataList);
}
