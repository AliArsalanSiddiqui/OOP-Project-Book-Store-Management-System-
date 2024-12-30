class Review {
    private String reviewerName;
    private int bookId;
    private String comment;
    private int rating;

    public Review(String reviewerName, int bookId, String comment, int rating) {
        this.reviewerName = reviewerName;
        this.bookId = bookId;
        this.comment = comment;
        this.rating = rating;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public int getBookId() {
        return bookId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review by " + reviewerName + ": " + comment + " (Rating: " + rating + "/5)";
    }
}