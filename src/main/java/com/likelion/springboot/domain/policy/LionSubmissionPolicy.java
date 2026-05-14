package com.likelion.springboot.domain.policy;

public class LionSubmissionPolicy implements SubmissionPolicy {

    @Override
    public boolean canSubmit() {
        return true;
    }
}
