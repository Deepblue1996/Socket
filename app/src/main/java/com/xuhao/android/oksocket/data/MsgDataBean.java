package com.xuhao.android.oksocket.data;

import com.google.gson.Gson;
import com.xuhao.android.libsocket.sdk.bean.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * 发送内容
 *
 * Created by Tony on 2017/10/24.
 */

public class MsgDataBean implements ISendable {
    private String content = "";

    public MsgDataBean(String content) {
        this.content = content;
    }

    @Override
    public byte[] parse() {

        MessageTypeBean messageTypeBean = new MessageTypeBean();

        messageTypeBean.type = 0;
        messageTypeBean.content = new String(content.getBytes(Charset.defaultCharset()));

        Gson gson = new Gson();

        byte[] body = gson.toJson(messageTypeBean).getBytes();

        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();
    }
}
