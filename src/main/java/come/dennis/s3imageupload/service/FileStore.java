package come.dennis.s3imageupload.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    public final AmazonS3 s3;

    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path,
                     String filename,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetadata.ifPresent(itemInMapForm->{
            if(!itemInMapForm.isEmpty()){
                itemInMapForm.forEach(objectMetadata::addUserMetadata); //this is same as below
                //itemInMapForm.forEach((key, value) -> objectMetadata.addUserMetadata(key, value));
        };
        });
        try{
            s3.putObject(path, filename, inputStream, objectMetadata);
        } catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }
}
