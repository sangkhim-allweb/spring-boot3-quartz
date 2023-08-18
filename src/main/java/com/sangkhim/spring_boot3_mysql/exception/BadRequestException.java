package com.sangkhim.spring_boot3_mysql.exception;

import com.sangkhim.spring_boot3_mysql.exception.base.ServiceException;

/** trigger for bad request exception */
public class BadRequestException extends ServiceException {

  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
