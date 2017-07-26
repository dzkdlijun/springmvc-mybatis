package com.leeo.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author lijun
 * @since 2017/7/26 9:45
 */
@Component
public class FastJsonMessageConverter extends AbstractJsonMessageConverter {
    /**
     * 默认生成message id
     */
    public FastJsonMessageConverter() {
        this.setCreateMessageIds(true);
    }

    /**
     * 将任意对象转换为AMQP消息，供生产者调用
     *
     * @param o
     * @param messageProperties
     * @return
     * @throws MessageConversionException
     */
    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        byte[] bytes = null;
        try {
            String json = JSON.toJSONString(o);
            bytes = json.getBytes(this.getDefaultCharset());
        } catch (UnsupportedEncodingException e) {
            throw new MessageConversionException("Failed to convert message content", e);
        }
        initMessageProperties(messageProperties, bytes);
        return new Message(bytes, messageProperties);
    }

    /**
     * 将AMQP消息转换为MQMessage对象，供消费者调用
     *
     * @param message
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String json = "";
        try {
            json = new String(message.getBody(), this.getDefaultCharset());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            MqMessage msg = JSONObject.parseObject(json, MqMessage.class);
            return msg;
        } catch (Throwable e) {
            throw new MessageConversionException("failed to parse string(" + json + ") to MQBizMessage", e);
        }
    }

    /**
     * 初始化message properties
     *
     * @param messageProperties
     * @param bytes
     */
    private void initMessageProperties(MessageProperties messageProperties, byte[] bytes) {
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(this.getDefaultCharset());
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }
    }
}
