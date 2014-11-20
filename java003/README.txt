[Gradle 프로젝트 폴더 구조]
	src/
	src/main => 애플리케이션 기본 소스
	src/main/java => 자바 소스
	src/main/resources => .xml, .txt, .properties 등 파일
	src/main/webapp => .html, .css, .js, .gif 등 웹 관련 파일 
	src/main/webapp/WEB-INF => .xml 파일 넣기
	
	src/test => JUnit 등 단위 테스트를 수행할 소스
	src/test/main
	src/test/resources
	
	build/ => 컴파일된 파일들 
	
	.gradle/ => 그래들 설정 파일 및 의존 라이브러리 파일들
	
	build.gradle => groovy로 작성된 빌드 설정 파일
	
	* 주의!
	.project 파일에 Gradle을 사용한다고 설정해야만 
	메뉴에서 그래들 관련 명령을 실행할 수 있다.
	<nature>org.springsource.ide.eclipse.gradle.core.nature</nature>


[gardle 빌드하기]
	1) 컴파일 하기
		Project -> 팝업메뉴(컨텍스트 메뉴) -> RUN AS -> Gradle Build... 클릭
	
	2) build.gradle 설정
	   => java gradle plugin import
		  apply plugin: 'java'
	   
[gradle java 플러그인 명령어]
	1. clean
	 => build 폴더를 제거한다.
	 
	2. compileJava
	 => /src/main/java 소스파일 컴파일 
	 => build/classes/main 폴더에 클래스 파일 복사
	 
	3. complieTestJava
	 => /src/test.java 소스파일 컴파일
	 => build/classes/ 폴더에 클래스 파일 복사
	 
	4. processResources
	 => /src/main/resouces 폴더의 모든 파일을 
	    /build/resuouces/main 폴더에 복사한다.
	
	5. processTestResourses
	 => /src/test/resouces 폴더의 모든 파일을 
	    /build/resuouces/test 폴더에 복사한다.
	    	
	6. classes
	=> complieJava / processResourses 를 순서대로 모두 실행한다.
	=> 그 후 기타 컴파일 관련 작업을 추가한다.
	
	7. jar
	=> classes 작업을 수행한 후, 클래스 파일과 기타 자원 파일을 묶어서  .jar 파일로 만든다.
	
	8.build
	=> complieJava, processResuorses, classes, jar, assemble 명령어 전부 다 실행
	- 모든 작업수행

[gradle eclipse 플러그인 명령어]
	=> 이클립스 관련 설정파일을 다룬다.
	
	1. cleanEclipseClasspath
	=> .classpath 파일 삭제
	
	2.elipseClasspath
	=> .classpath 파일 생성
	
	3. cleanEclipse
	=> 이클립스 관련 모든 설정 파일제거
	=> cleanEclipseClasspath, cleanEclipseJdt, cleanEclipseProject, cleanEclipse 순서대로 실행
	
	4.eclipse
	=> 이클립스 관련 모든 설정 파일추가
	=> 기존에 이미 같은 파일이 있다면, 내용만 추가
	=> eclipseClasspath, eclipseJdt, eclipseProject, eclipse 순서대로 실행


[의존 라이브러리 설정]
	1) 의존 라이브러리를 다운로드 받을 서버지정
	  => Maven 서버를 공유한다.
	  repositories {
	    mavenCentral()
	  }
	  
	2) 다운로드 할 의존 라이브러리 이름 설정 
	dependencies {
	  //[의존라이브러리의 사용 용도] '그룹명:라이브러리명:버전'
	  compile 'commons-lang:commons-lang:2.6'
	  testCompile 'org.mockito:mockito:1.9.0-rc1'
	
	  //[의존라이브러리의 사용 용도] group:'그룹명', name:'라이브러리이름', version:'버전'
	  //[의존라이브러리의 사용 용도] '그룹명:라이브러리이름:버전'   => 이것도 가능
	  compile group: 'com.google.code.guice', name: 'guice', version: '1.0'
	
	  //[의존라이브러리의 사용 용도] files('파일경로', '파일경로', ..) 
	  //declaring arbitrary files as dependencies
	  compile files('hibernate.jar', 'libs/spring.jar')
	
	  //[의존라이브러리의 사용 용도] fileTree('.jar 파일들이 들어있는 경로') 
	  compile fileTree('libs')
	}
	
	*의존라이브러리 사용용도
	1)compile : 컴파일 할 때 사용하고 배포(.jar)할때도 포함하라
	2)testCompile: 단위 테스트 파일을 컴파일 할 때만 사용하라. 배포(.jar)에서 제외한다.
	
	*Recompile with -Xlint:unchecked for details. => 에러는 무시해도 되는 에러

[build.gradle 설정하기]
	-각 명령어 별로 매소드를 호출하여 설정한다.





