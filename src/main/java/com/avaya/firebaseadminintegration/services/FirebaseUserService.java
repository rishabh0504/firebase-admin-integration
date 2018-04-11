package com.avaya.firebaseadminintegration.services;

import java.util.concurrent.ExecutionException;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

@Service
public class FirebaseUserService {

    public UserRecord findUserByAll(String keyword) throws FirebaseAuthException, ExecutionException, InterruptedException {

    	System.out.println(keyword);
        // is Email
        EmailValidator emailValidator = new EmailValidator();
        if (emailValidator.isValid(keyword, null)) {
            return findUserByEmail(keyword);
        }

        // is Phone
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(keyword, "KR");
            if (phoneNumberUtil.isValidNumber(phoneNumber)) {
                return findUserByPhoneNumber(keyword);
            }
        } catch (NumberParseException ignored) {

        }

        return findUserByUid(keyword);
    }

    public UserRecord findUserByUid(String uid) throws FirebaseAuthException, ExecutionException, InterruptedException {
        return FirebaseAuth.getInstance().getUserAsync(uid).get();
    }

    public UserRecord findUserByEmail(String email) throws FirebaseAuthException, ExecutionException, InterruptedException {
        return FirebaseAuth.getInstance().getUserByEmailAsync(email).get();
    }

    public UserRecord findUserByPhoneNumber(String phoneNumber) throws FirebaseAuthException, ExecutionException, InterruptedException {
        return FirebaseAuth.getInstance().getUserByPhoneNumberAsync(phoneNumber).get();
    }
}