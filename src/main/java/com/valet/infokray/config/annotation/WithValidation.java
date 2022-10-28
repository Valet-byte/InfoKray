package com.valet.infokray.config.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

@Qualifier
@Primary
@Retention(RetentionPolicy.RUNTIME)
public @interface WithValidation {}
