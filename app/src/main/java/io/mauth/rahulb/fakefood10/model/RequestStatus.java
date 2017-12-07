package io.mauth.rahulb.fakefood10.model;

/**
 * Created by rahulb on 1/11/17.
 */

public enum RequestStatus {
    PENDING, // It means the Request is not sent to the Server
    SUBMITTED,  // It means the Request is submitted to the Server
    FAKE,
    ORIGINAL,
    UNKNOWN;
}
