package com.hanhwa_tae.secondserver.user.command.application.dto;

public class TrackResponse {
    private Data data;

    public Data getData() { return data; }

    public static class Data {
        private Track track;

        public Track getTrack() { return track; }
    }

    public static class Track {
        private LastEvent lastEvent;

        public LastEvent getLastEvent() { return lastEvent; }
    }

    public static class LastEvent {
        private String time;
        private Status status;

        public String getTime() { return time; }
        public Status getStatus() { return status; }
    }

    public static class Status {
        private String code;

        public String getCode() { return code; }
    }
}
