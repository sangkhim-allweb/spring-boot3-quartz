package com.sangkhim.spring_boot3_mysql.exception;

import com.sangkhim.spring_boot3_mysql.exception.base.ServiceException;

/** trigger for forbidden exception */
public class ForbiddenException extends ServiceException {

  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
