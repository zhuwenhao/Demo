package com.zhuwenhao.demo.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Subject {

    private Rating rating;
    private List<String> genres;
    private String title;
    private List<Cast> casts;
    private List<String> durations;
    @SerializedName("collect_count")
    private int collectCount;
    @SerializedName("mainland_pubdate")
    private String mainlandPubDate;
    @SerializedName("has_video")
    private boolean hasVideo;
    @SerializedName("original_title")
    private String originalTitle;
    private String subtype;
    private List<Director> directors;
    @SerializedName("pubdates")
    private List<String> pubDates;
    private String year;
    private Images images;
    private String alt;
    private String id;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getMainlandPubDate() {
        return mainlandPubDate;
    }

    public void setMainlandPubDate(String mainlandPubDate) {
        this.mainlandPubDate = mainlandPubDate;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<String> getPubDates() {
        return pubDates;
    }

    public void setPubDates(List<String> pubDates) {
        this.pubDates = pubDates;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class Rating {

        private int max;
        private double average;
        private Details details;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public static class Details {

            @SerializedName("1")
            private int v1;
            @SerializedName("2")
            private int v2;
            @SerializedName("3")
            private int v3;
            @SerializedName("4")
            private int v4;
            @SerializedName("5")
            private int v5;

            public int getV1() {
                return v1;
            }

            public void setV1(int v1) {
                this.v1 = v1;
            }

            public int getV2() {
                return v2;
            }

            public void setV2(int v2) {
                this.v2 = v2;
            }

            public int getV3() {
                return v3;
            }

            public void setV3(int v3) {
                this.v3 = v3;
            }

            public int getV4() {
                return v4;
            }

            public void setV4(int v4) {
                this.v4 = v4;
            }

            public int getV5() {
                return v5;
            }

            public void setV5(int v5) {
                this.v5 = v5;
            }
        }
    }

    public static class Cast {

        private Avatars avatars;
        @SerializedName("name_en")
        private String nameEn;
        private String name;
        private String alt;
        private String id;

        public Avatars getAvatars() {
            return avatars;
        }

        public void setAvatars(Avatars avatars) {
            this.avatars = avatars;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class Avatars {

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class Director {

        private Avatars avatars;
        @SerializedName("name_en")
        private String nameEn;
        private String name;
        private String alt;
        private String id;

        public Avatars getAvatars() {
            return avatars;
        }

        public void setAvatars(Avatars avatars) {
            this.avatars = avatars;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class Avatars {

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class Images {

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }
}