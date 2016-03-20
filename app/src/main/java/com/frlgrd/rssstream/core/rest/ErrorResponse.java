package com.frlgrd.rssstream.core.rest;

public class ErrorResponse {
  Error error;
  
  public static class Error {
    Data data;
  
    public static class Data {
       String message;
    }  
  }
}