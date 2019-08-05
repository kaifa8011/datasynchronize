package com.ciba.data.synchronize.uploader;

import com.ciba.data.synchronize.entity.OperationData;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/4
 */
public interface OperationDataUploader {
    void uploadOperationDataData(List<OperationData> motionEventList);
}
