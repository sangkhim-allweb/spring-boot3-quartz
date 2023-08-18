package com.sangkhim.spring_boot3_mysql.exception;

import com.sangkhim.spring_boot3_mysql.exception.base.ServiceException;

/** trigger for too many requests exception */
public class TooManyRequestsException extends ServiceException {

  public TooManyRequestsException() {
    super();
  }

  public TooManyRequestsException(String message) {
    super(message);
  }

  public TooManyRequestsException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
