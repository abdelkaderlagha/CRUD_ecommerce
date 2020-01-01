package com.formalab.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends Throwable {
public	ProduitIntrouvableException(String s) {
	super(s);
}
}
