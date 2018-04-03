package co.kr.jcone.server.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PathBean {

	@Value("${path.dev.uploadFile}")
	public String pathUploadFile;


	public static String PATH_UPLOADFILE = "";

	public PathBean() {
	}

	@Bean
	public PathBean initPathBean() {

		this.PATH_UPLOADFILE = this.pathUploadFile;

		return new PathBean();
	}

}
