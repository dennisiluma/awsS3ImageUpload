package come.dennis.s3imageupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class S3ImageUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3ImageUploadApplication.class, args);
	}

}
