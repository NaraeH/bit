/*
  이 에노테이션은 명령어를 처리하는 메소드를 지정할 때 사용한다.
  즉, 이 명령어가 붙은 명령어를 호출할 것이다.
 * */

package java002.test16.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	String value() default "";

}
