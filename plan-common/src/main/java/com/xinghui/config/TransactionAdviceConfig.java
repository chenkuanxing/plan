package com.xinghui.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;

/**
 * 全局事务拦截
 *
 * @author ckx
 */

@Aspect
@Configuration
@ConditionalOnBean({DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.xinghui..*.service..*.*(..))";

    @Resource
    private TransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        DefaultTransactionAttribute txAttrRequiredReadonly = new DefaultTransactionAttribute();
        txAttrRequiredReadonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttrRequiredReadonly.setReadOnly(true);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("insert*", txAttrRequired);
        source.addTransactionalMethod("import*", txAttrRequired);
        source.addTransactionalMethod("save*", txAttrRequired);
        source.addTransactionalMethod("add*", txAttrRequired);
        source.addTransactionalMethod("create*", txAttrRequired);
        source.addTransactionalMethod("delete*", txAttrRequired);
        source.addTransactionalMethod("remove*", txAttrRequired);
        source.addTransactionalMethod("update*", txAttrRequired);
        source.addTransactionalMethod("submit*", txAttrRequired);
        source.addTransactionalMethod("handle*", txAttrRequired);
        source.addTransactionalMethod("bind*", txAttrRequired);
        source.addTransactionalMethod("unbind*", txAttrRequired);
        source.addTransactionalMethod("get*", txAttrRequiredReadonly);
        source.addTransactionalMethod("select*", txAttrRequiredReadonly);
        source.addTransactionalMethod("query*", txAttrRequiredReadonly);
        source.addTransactionalMethod("find*", txAttrRequiredReadonly);
        source.addTransactionalMethod("list*", txAttrRequiredReadonly);
        source.addTransactionalMethod("count*", txAttrRequiredReadonly);
        source.addTransactionalMethod("page*", txAttrRequiredReadonly);
        source.addTransactionalMethod("is*", txAttrRequiredReadonly);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
