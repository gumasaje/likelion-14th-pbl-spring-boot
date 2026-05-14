package com.likelion.springboot.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {

    @Override
    public boolean canSubmit() {
        return false;
    }
}
