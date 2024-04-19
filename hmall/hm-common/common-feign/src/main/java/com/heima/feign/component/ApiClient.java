package com.heima.feign.component;

import com.heima.feign.note.Api;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@Import(FeignClientsConfiguration.class)
public class ApiClient {

    /**
     * Decodes an HTTP response
     */
    private final Decoder decoder;
    /**
     * Encodes an object
     */
    private final Encoder encoder;

    private final Client client;

    private List<RequestInterceptor> requestInterceptors;

    private final Contract contract;

    @Autowired(required = false)
    public void setRequestInterceptors(List<RequestInterceptor> requestInterceptors) {
        this.requestInterceptors = requestInterceptors;
    }

    public ApiClient(Decoder decoder, Encoder encoder, Client client,Contract contract ) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.client = client;
        this.contract = contract;
    }

    public <T> T buildClient(Class<T> clientClass) throws Exception {
        String serviceName = getServiceName((Class<Api>) clientClass);
        if (StringUtils.isEmpty(serviceName)) {
            throw new Exception("未配置@API，无法加载服务名称...");
        }
        return Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptors(CollectionUtils.isEmpty(requestInterceptors)?new ArrayList<>():requestInterceptors)
                .target(clientClass,"http://"+serviceName);


    }

    private String getServiceName(Class<?> clientClass) throws Exception {
        Annotation[] annotations = clientClass.getAnnotations();
        Api clientAnnotation = null;
        for (Class<?> anInterface : clientClass.getInterfaces()) {
            Api annotation = anInterface.getAnnotation(Api.class);
            if (Objects.nonNull(annotation));
            clientAnnotation = annotation;
        }
        if (Objects.isNull(clientAnnotation)){
            throw new Exception("该clinet未配置所属服务");
        }
        return clientAnnotation.serviceName();
    }


}
