package com.likelion.springboot.policy;

public class LionSubmissionPolicy implements SubmissionPolicy {

    @Override
    public boolean canSubmit() {
        return true;
    }
}
