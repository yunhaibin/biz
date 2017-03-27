package cn.leadeon.action.annotion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IsInteger {
	
	int max() default 99999999;
	
	int min() default -999999999;
}
