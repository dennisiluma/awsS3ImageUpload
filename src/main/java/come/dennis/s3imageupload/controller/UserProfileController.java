package come.dennis.s3imageupload.controller;

import come.dennis.s3imageupload.model.UserProfile;
import come.dennis.s3imageupload.service.UserProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("userprofile.com")
@CrossOrigin("*") //here we are saying it can be access from anywhere not appropriate in production but because we are in development and want our react app to be able to remove the CORS error
public class UserProfileController {

    private final UserProfileDataService userProfileDataAccessService;

    @Autowired
    public UserProfileController(UserProfileDataService userProfileDataAccessService) {
        this.userProfileDataAccessService = userProfileDataAccessService;
    }

    @GetMapping
    public List<UserProfile> getUserProfile() {
        return userProfileDataAccessService.getUserProfile();
    }
    @PostMapping(
            path = "{userProfileId}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file") MultipartFile file) throws IOException {
        userProfileDataAccessService.uploadProfileImage(userProfileId,file);
    }
    @GetMapping(path = "{userProfileId}/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID id){
            return userProfileDataAccessService.downloadUserProfileImage(id);
    }
}
