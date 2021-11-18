package come.dennis.s3imageupload.service;

import come.dennis.s3imageupload.bucket.BucketName;
import come.dennis.s3imageupload.model.UserProfile;
import come.dennis.s3imageupload.repository.FileStore;
import come.dennis.s3imageupload.repository.UserProfileDataRepository;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileDataService {
    private final UserProfileDataRepository fakeUserProfileDataStore;
    private final FileStore fileStore;

    @Autowired
    public UserProfileDataService(UserProfileDataRepository fakeUserProfileDataStore, FileStore fileStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
        this.fileStore = fileStore;
    }
    public List<UserProfile> getUserProfile(){
        return fakeUserProfileDataStore.getUserProfile();
    }
    public void uploadProfileImage(UUID id, MultipartFile file) throws IOException {
        //1 check if file is not empty
        if (file.isEmpty()){
            throw new IllegalStateException("cannot upload file ["+ file.getSize() + "]");
        }
        //2 check if file is an image
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("file is not an immage [" + file.getContentType() + "]");
        }

        //3 check if the user exist in our database(inmemory for now)
        UserProfile user = fakeUserProfileDataStore.getUserProfile()
                .stream()
                .filter(item->item.getUserProfileId().equals(id))
                .findFirst()
                .orElseThrow(()->new IllegalStateException(String.format("User Profile %s not found", id)));

        //4 grab some metadata in file if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));


        //5 store the image in s3 and update database with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE_BUCKET.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path, filename,Optional.of(metadata),file.getInputStream());
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
    }
}
