package com.cowin.notify.vaccinenotification.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LoggerUtil implements BeanPostProcessor
{
    /**
     * Runs before bean initialization.
     *
     * @param bean The bean.
     * @param beanName the bean in question.
     * @return The modified bean.
     * @throws BeansException If there is an issue in post processing.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
//        final Class realClass = ReflectionUtil.getUnderlyingClass(bean);
//        final Object realBean = ReflectionUtil.getUnderlyingObject(bean);
//
//        ReflectionUtil.getFieldsAnnotatedWith(Autowired.class, realClass)
//                .stream()
//                .filter(field -> field.getType() == Logger.class)
//                .forEach(field -> ReflectionUtil.setField(field, realBean, LoggerFactory.getLogger(realClass), Logger.class));

        return bean;
    }

    /**
     * Run after bean initialization.
     *
     * @param bean The bean.
     * @param beanName the bean in question.
     * @return The modified bean.
     * @throws BeansException If there is an issue in post processing.
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        return bean;
    }}
