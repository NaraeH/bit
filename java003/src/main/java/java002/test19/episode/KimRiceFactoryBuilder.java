package java002.test19.episode;

public class KimRiceFactoryBuilder {
	static public KimRiceFactory build(String configFilePath){
		//설계 도면에 따라 김밥공장을 만든다.
		return new KimRiceFactory();
	}

}
