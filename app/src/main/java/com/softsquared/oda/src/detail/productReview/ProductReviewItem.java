package com.softsquared.oda.src.detail.productReview;

import com.google.gson.annotations.SerializedName;

public class ProductReviewItem {

    @SerializedName("id")
    private String id;
    @SerializedName("reviewTitle")
    private String reviewTitle;
    @SerializedName("review")
    private String review;
    @SerializedName("reviewDate")
    private String reviewDate;
    @SerializedName("reviewImage")
    private String reviewImage;


    public ProductReviewItem(String reviewImage, String reviewTitle, String reviewDate, String id, String reviewContent) {
        this.reviewImage = reviewImage;
        this.reviewTitle = reviewTitle;
        this.reviewDate = reviewDate;
        this.id = id;
        this.review = reviewContent;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return review;
    }

    public void setReviewContent(String reviewContent) {
        this.review = reviewContent;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(String reviewImage) {
        this.reviewImage = reviewImage;
    }
}
