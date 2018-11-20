package com.example.archek.fitshedule.network;


import com.google.gson.annotations.SerializedName;

public class ObjectResponse {
    private String name;
    private String description;
    private String place;
    private String teacher;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String  endTime;
    @SerializedName("weekDay")
    private Integer weekDay;
    @SerializedName("appointment_id")
    private String appointmentId;
    @SerializedName("service_id")
    private String serviceId;
    private Boolean pay;
    private Boolean appointment;
    @SerializedName("teacher_v2")
    private TeacherV2 teacherV2;
    private String color;
    private Integer availability;
    public ObjectResponse(String name, String description, Integer weekDay, String startTime,
                          String endTime, String place, String teacher) {
        this.name = name;
        this.description = description;
        this.place = place;
        this.teacher = teacher;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekDay = weekDay;
    }
    public ObjectResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public Boolean getPay() {
        return pay;
    }

    public Boolean getAppointment() {
        return appointment;
    }

    public TeacherV2 getTeacherV2() {
        return teacherV2;
    }

    public String getColor() {
        return color;
    }

    public Integer getAvailability() {
        return availability;
    }

    public static class TeacherV2{
        @SerializedName("short_name")
        private String shortName;
        private String name;
        private String position;
        private String imageUrl;

        public String getShortName() {
            return shortName;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public String getImageUrl() {
            return imageUrl;
        }

    }

}
