package come.dennis.s3imageupload.repository;

import come.dennis.s3imageupload.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserProfileDataRepository {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
    static {
        /**Here below, anytime we start the server, random id's are generated*/
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"dennisiluma",null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "joshuailuma", null));
        /**Here, we have given it a fixed id to avoid random generation*/
        USER_PROFILES.add(new UserProfile(UUID.fromString("415d3e7c-06a8-4618-9c5d-00d939e40ade"),"dennisiluma",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("3d02abc4-43df-4508-8f12-0e8a3df164e7"), "joshuailuma", null));
    }
    public List<UserProfile> getUserProfile(){
        return USER_PROFILES;
    }
}
