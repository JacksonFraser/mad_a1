package com.example.s3529589.mad_a1.Model;


import java.util.UUID;

public class MeetingFriend {
    private UUID friendUUID;
    private UUID meetingUUID;

    public MeetingFriend(UUID meetingUUID, UUID friendUUID) {
        this.friendUUID = friendUUID;
        this.meetingUUID = meetingUUID;
    }

    public MeetingFriend() {

    }

    public void setFriendUUID(UUID friendUUID) {
        this.friendUUID = friendUUID;
    }

    public void setMeetingUUID(UUID meetingUUID) {
        this.meetingUUID = meetingUUID;
    }

    public UUID getFriendUUID() {
        return friendUUID;
    }

    public UUID getMeetingUUID() {
        return meetingUUID;
    }


}
