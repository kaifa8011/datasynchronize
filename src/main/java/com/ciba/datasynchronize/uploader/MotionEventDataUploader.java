package com.ciba.datasynchronize.uploader;

import com.ciba.datasynchronize.entity.OperationData;

import java.util.List;

/**
 * @author ciba
 * @description 描述
 * @date 2018/12/4
 */
public interface MotionEventDataUploader {
    void uploadMotionEventData(List<OperationData> motionEventList);
}
