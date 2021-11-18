package come.dennis.s3imageupload.service;

import come.dennis.s3imageupload.model.UserProfile;
import come.dennis.s3imageupload.repository.UserProfileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileDataService {
    private final UserProfileDataRepository fakeUserProfileDataStore;

    @Autowired
    public UserProfileDataService(UserProfileDataRepository fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }
    public List<UserProfile> getUserProfile(){
        return fakeUserProfileDataStore.getUserProfile();
    }
    public void uploadProfileImage(UUID id, MultipartFile file){
        //check if file is not empty

        //check if file is an image

        //check if the user exist in our database

        //grab some metadata in file if any

        //store in s3 and update database with s3 image link
    }
}
