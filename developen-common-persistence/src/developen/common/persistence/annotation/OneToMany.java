package developen.common.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OneToMany {

	
	String mappedBy();  
	
	CascadeType[] cascade() default {CascadeType.ALL};

	FetchType fetch() default FetchType.LAZY;
	
	
}