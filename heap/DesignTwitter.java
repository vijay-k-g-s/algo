package heap;

// Problem (LC 355): Design a simplified version of Twitter.
//   postTweet(userId, tweetId)      — post a new tweet.
//   getNewsFeed(userId)             — return 10 most recent tweet IDs from the user
//                                     and users they follow, in order (most recent first).
//   follow(followerId, followeeId)  — followerId follows followeeId.
//   unfollow(followerId, followeeId)— followerId unfollows followeeId.
// Approach: HashMap for follows (userId → Set<followeeId>).
//   HashMap for tweets (userId → List of [timestamp, tweetId]).
//   getNewsFeed: collect the tweet lists of user + all followees, merge with
//   a max-heap keyed by timestamp, pick top 10.
// Time: O(n log k) getNewsFeed where k = number of followed users. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class DesignTwitter {

    private int timestamp = 0;
    private final Map<Integer, List<int[]>> tweets = new HashMap<>();  // userId → [{time, tweetId}]
    private final Map<Integer, Set<Integer>> follows = new HashMap<>(); // userId → {followeeIds}

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{timestamp++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        // Max-heap: most recent first
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        Set<Integer> feed = new HashSet<>(follows.getOrDefault(userId, Collections.emptySet()));
        feed.add(userId);

        for (int uid : feed) {
            List<int[]> userTweets = tweets.getOrDefault(uid, Collections.emptyList());
            // Add only the last 10 from each user (optimization)
            int start = Math.max(0, userTweets.size() - 10);
            for (int i = start; i < userTweets.size(); i++) {
                maxHeap.offer(userTweets.get(i));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty() && result.size() < 10) {
            result.add(maxHeap.poll()[1]);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        follows.getOrDefault(followerId, Collections.emptySet()).remove(followeeId);
    }

    public static void main(String[] args) {
        DesignTwitter twitter = new DesignTwitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1)); // [5]
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1)); // [6, 5]
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1)); // [5]
    }
}
