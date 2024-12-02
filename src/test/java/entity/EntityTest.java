package entity;

import app.UserFactory;
import entity.reviews_thread.Reply;
import entity.reviews_thread.Review;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    private User testUser;
    private Review testReview;
    private UserProfile testProfile;
    private Location testLocation;
    private UserReview testUserReview;
    private UserReview testUserReply;
    private Reply testReply;

    @BeforeEach
    void setup() {
        testUser = new StudentUser("Steven", "1234");
        testReview = new Review(testUser, 3, "Test 1");
        testLocation = new Location("Truck 1", "43.77", "63.33");
        testProfile = new UserProfile("Steven", "I am a uoft Student");
        testUserReview = new UserReview(testUser, 3, "It is good", testLocation);
        testUserReply = new UserReview(testUser, 6, "It is bad");
        testReply = new Reply(testUser, "This is a reply");
    }


    @Test
    void likeTest() {

        Like testLike = new Like(testUser, testReview);
        testReview.updateListOfReplies(testReply);
        testReview.incrementLikes();
        List<Reply> replyList = List.of(testReply);
        assertEquals("Steven", testLike.getUser().getUsername());
        assertEquals("1234", testLike.getUser().getPassword());
        assertEquals(testUser, testReview.getUser());
        assertEquals(3, testLike.getReview().getRating());
        assertEquals("Test 1", testLike.getReview().getComment());
        assertEquals(replyList, testReview.getListOfReplies());
        assertEquals(1, testReview.getNumberOfLikes());
        assertNull(testReview.getId());
    }

    @Test
    void locationTest() {
        Integer numRating = Integer.valueOf(1);
        List<Integer> ratingList = List.of(numRating);
        testLocation.setAddress("12 Norton Ave");
        testLocation.setRating(ratingList);
        testLocation.setName("Test Building");
        testLocation.setDescription("This is a really good building");
        assertEquals("Test Building", testLocation.getName());
        assertEquals("12 Norton Ave", testLocation.getAddress());
        assertEquals(1, testLocation.getRating());
        assertEquals("43.77", testLocation.getLatitude());
        assertEquals("63.33", testLocation.getLongitude());
        assertEquals("This is a really good building", testLocation.getDescription());
    }

    @Test
    void userProfileTest() {
        assertEquals("Steven", testProfile.getUsername());
        assertEquals("I am a uoft Student", testProfile.getBio());
    }

    @Test
    void userReviewTest() {
        UserReview lazyReview = new UserReview(testUser, "I don't wanna give a rating");
        testUserReview.setKey("1234567");
        testUserReview.incrementLikes();
        testUserReview.updateListOfReplies(testUserReply);
        List<UserReview> replyList = List.of(testUserReply);
        assertEquals(testUser, testUserReview.getUser());
        assertEquals(3, testUserReview.getRating());
        assertEquals("It is good", testUserReview.getComment());
        assertEquals(1, testUserReview.getNumberOfLikes());
        assertEquals(testLocation, testUserReview.getLocation());
        assertEquals("1234567", testUserReview.getKey());
        assertEquals(replyList, testUserReview.getListOfReplies());
    }
}