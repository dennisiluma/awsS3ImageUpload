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
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"dennisiluma",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "joshuailuma", null));
    }
    public List<UserProfile> getUserProfile(){
        return USER_PROFILES;
    }
}
