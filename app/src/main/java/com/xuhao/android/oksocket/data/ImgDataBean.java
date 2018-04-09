package com.xuhao.android.oksocket.data;

import android.util.Base64;

import com.google.gson.Gson;
import com.xuhao.android.libsocket.sdk.bean.ISendable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * 发送图片
 * <p>
 * Created by Tony on 2017/10/24.
 */

public class ImgDataBean implements ISendable {
    private String imagePath = "";

    public ImgDataBean(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 照片转byte二进制
     *
     * @param imagePathSinge 需要转byte的照片路径
     * @return 已经转成的byte
     * @throws Exception
     */
    private byte[] readStream(String imagePathSinge) throws Exception {
        FileInputStream fs = new FileInputStream(imagePathSinge);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }

    @Override
    public byte[] parse() {
        byte[] body = new byte[0];

        MessageTypeBean messageTypeBean = new MessageTypeBean();

        try {
            messageTypeBean.type = 1;
            messageTypeBean.imgData = readStream(imagePath);

            Gson gson = new Gson();

            body = gson.toJson(messageTypeBean).getBytes();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();
    }
}
