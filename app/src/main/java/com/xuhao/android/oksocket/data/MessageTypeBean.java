package com.xuhao.android.oksocket.data;

import java.io.Serializable;

/**
 * Created by Deep on 2018/4/9 0009.
 */

public class MessageTypeBean implements Serializable {

    // 0 : text 1: img
    public int type;
    public String mTime;

    public String content;
    public byte[] imgData;
}
