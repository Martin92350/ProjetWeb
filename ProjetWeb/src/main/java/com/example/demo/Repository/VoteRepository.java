package com.example.demo.Repository;

import com.example.demo.Model.User;
import com.example.demo.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query(value = "SELECT * FROM auth_user WHERE email=?1", nativeQuery = true)
    public User scoreVote();

    @Transactional
    @Modifying
    @Query(value = "UPDATE vote SET vote.city_one_vote = vote.city_one_vote + 1 WHERE survey_id=?1", nativeQuery = true)
    public void scoreVoteCity1(int survey_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vote SET vote.city_two_vote = vote.city_two_vote +1 WHERE survey_id=?1", nativeQuery = true)
    public void scoreVoteCity2(int survey_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vote SET vote.city_three_vote = vote.city_three_vote +1 WHERE survey_id=?1", nativeQuery = true)
    public void scoreVoteCity3(int survey_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vote SET vote.date_one_vote = vote.date_one_vote +1 WHERE survey_id=?1", nativeQuery = true)
    public void scoreVoteDate1(int survey_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vote SET vote.date_two_vote = vote.date_two_vote +1 WHERE survey_id=?1", nativeQuery = true)
    public void scoreVoteDate2(int survey_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vote SET vote.date_three_vote = vote.date_three_vote +1 WHERE survey_id=?1", nativeQuery = true)
    public void scoreVoteDate3(int survey_id);

}
