package com.ciba.data.synchronize.uploader;

import com.ciba.data.synchronize.entity.ProcessData;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/11
 */
public interface ProcessDataUploader {
    void uploadProcessData(List<ProcessData> processDataList);
}
