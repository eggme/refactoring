package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
        GHIssue issue = getGhIssue();
        Set<String> participants = getUsernames(issue);
        print(participants);
    }

    private void printReviewers() throws IOException {
        GHIssue issue = getGhIssue();
        Set<String> reviewers = getUsernames(issue);
        print(reviewers);
    }

    private Set<String> getUsernames(GHIssue issue) throws IOException {
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));
        return participants;
    }

    private void print(Set<String> participants) {
        participants.forEach(System.out::println);
    }

    private GHIssue getGhIssue() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        return repository.getIssue(30);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
