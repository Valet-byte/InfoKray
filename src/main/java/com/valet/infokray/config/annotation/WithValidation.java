package com.valet.infokray.config.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Primary
@Retention(RetentionPolicy.RUNTIME)
public @interface WithValidation {
}
