package com.dong.mvp.factory;

import com.dong.mvp.base.IMvpBasePersenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @packInfo:com.dong.mvp.factory
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 11:00
 *
 * 说明：标注创建presenter的注解
 * @Retention(RetentionPolicy.RUNTIME)
 * 这个注解是运行时注解，只有在标注这样一个注解之后，
 * 我们才可以用反射的方式来处理我们这个自定义的注解
 *
 * @Inherited
 * 该注解表示子类可以集成加载父类上的注解。
 * 1.注解定义在类上面，子类是可以继承该注解
 * 2.注解定义在方法上面，子类也可以继承该注解，但是如果子类复写了父类中定义了注解的方法，那么子类将无法继承该方法的注解，
 * 也就是说，子类在复写父类中被@Inherited标注的方法时，会将该方法上面的注解覆盖掉
 * 3.Interface的实现类（implements实现）无法继承接口中所定义的被@Inherited标注的注解
 *
 * @Inherited
 * 与上面的注解配合使用，为了让反射代码检查展开工作
 *
 *
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {

    Class<? extends IMvpBasePersenter> value();

}
