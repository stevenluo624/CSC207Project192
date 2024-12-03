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
    private Reply testReply;

    @BeforeEach
    void setup() {
        testUser = new StudentUser("Steven", "1234");
        testLocation = new Location("Truck 1", "43.77", "63.33");
        testProfile = new UserProfile("Steven", "I am a uoft Student");
        testReview = new Review(testUser, 3, "It is good");
        testReply = new Reply(testUser, "It is bad");
        testReply = new Reply(testUser, "This is a reply");
    }


    @Test
    void likeTest() {

        Like testLike = new Like(testUser, testReview);
        testReview.updateListOfReplies("reply1");
        testReview.incrementLikes();
        List<String> replyList = List.of("reply1");
        assertEquals("Steven", testLike.getUser().getUsername());
        assertEquals("1234", testLike.getUser().getPassword());
        assertEquals(testUser, testReview.getUser());
        assertEquals(3, testLike.getReview().getRating());
        assertEquals("It is good", testLike.getReview().getComment());
        assertEquals(replyList, testReview.getListOfReplies());
        assertEquals(1, testReview.getNumberOfLikes());
        assertEquals(1, testReview.getId());
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
        testReview.incrementLikes();
        testReview.updateListOfReplies("reply1");
        testReview.setLocation(testLocation);
        List<String> replyList = List.of("reply1");
        assertEquals(testUser, testReview.getUser());
        assertEquals(3, testReview.getRating());
        assertEquals("It is good", testReview.getComment());
        assertEquals(1, testReview.getNumberOfLikes());
        assertEquals(testLocation, testReview.getLocation());
        assertEquals(replyList, testReview.getListOfReplies());
    }
}
