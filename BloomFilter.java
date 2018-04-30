package com.company;


import java.util.Arrays;

public class BloomFilter {

    // A filter created by a guy with the last name of bloom. It's goal is to return either no or maybe to a question
    // of if the item is within a set.

    // it is useful in situations where you want quick lookup times, false negatives are allowed, and you never need to
    // remove items from the filter

    // From a technical standpoint this is a set. But it is not a strict set. We cannot know if something is in the set,
    // we can know if they item isn't in the set.

    // It works by implementing two hashing functions.
    // For this example the quality of these functions are irrelevant.
    // Collisions are acceptable.
    // You can check them out in the Hasher file. One is intentionally bad to prove a point.

    // Now that we have these we need to add an item to the set, which means we should probably reserve some data. Time
    // to make a constructor.
    private Boolean[] setData;

    public BloomFilter(){
        // For style this should be first, for this presentation, not so much
        setData = new Boolean[1000];

        // we have to set these values to false to prevent null pointer errors in java
        Arrays.fill(setData, false);
    }

    // For the purpose of this experiment, we don't need much space. Ideally we would use a little bit more than this

    // now we need to define how to add an item:
    public void addItem(String newMember){
        int indexOne = Hasher.dumbHashFunc(newMember) % setData.length;
        int indexTwo = Hasher.smarterHashFunc(newMember) % setData.length;

        setData[indexOne] = setData[indexTwo] = true;
    }

    // finally we need to make a way to check if an item is within this set:
    public Boolean checkItem(String member){
        int indexOne = Hasher.dumbHashFunc(member) % setData.length;
        int indexTwo = Hasher.smarterHashFunc(member) % setData.length;

        return setData[indexOne] && setData[indexTwo];
    }

    // The nature of this filter means that an item may falsely be called as within the set,
    // But it will never say an item that is in the set isn't in the set.
    // It also has the benefit that you know how much memory it will take up.
    // it has O(c) or O(1) lookup time.
    // It can become full (especially with this size) at which point it will say everything is in the set. Meaning,
    // You should use a larger array than this

    // You cannot increase the size, doing so will cause the set to be meaningless and fail to deliver on the promise
    // that nothing will be said to not be in the set if it is in the set.

    // You can add additional hash functions to get less false responses at the cost of performance


    // This is here just to make it have some lovely readable output.

    public void prettyCheck(String member){

        if (checkItem(member)) {
            System.out.println("We do have a member named: " + member);
        } else {
            System.out.println("We don't have a member named: " + member);
        }

    }

}
