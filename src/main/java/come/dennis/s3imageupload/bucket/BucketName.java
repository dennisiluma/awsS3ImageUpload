package come.dennis.s3imageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("dennis-s3-imageupload");
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getBucketName(){
        return bucketName;
    }
}
