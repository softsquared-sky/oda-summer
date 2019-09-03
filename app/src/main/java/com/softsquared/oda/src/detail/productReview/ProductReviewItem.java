package com.softsquared.oda.src.detail.productReview;

public class ProductReviewItem {
    private String reviewImage;


    private String reviewTitle;
    private String reviewDate;
    private String id;
    private String reviewContent;



    public ProductReviewItem(String reviewImage, String reviewTitle, String reviewDate, String id, String reviewContent) {
        this.reviewImage = reviewImage;
        this.reviewTitle = reviewTitle;
        this.reviewDate = reviewDate;
        this.id = id;
        this.reviewContent = reviewContent;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
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
