package com.arouter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author：wangcaiwen
 * Date：2019-07-16
 * Description：
 * @author wangcaiwen
 */
@Target(ElementType.TYPE)//声明这个注解是放在类上面的
//声明这个注解的生命周期  source源码期-->class编译期-->runtime运行期
@Retention(RetentionPolicy.CLASS)
public @interface BindPath {
    String value();
}
