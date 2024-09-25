package com.xinchao.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PreAuthorize("hasRole('ADMIN') OR hasRole('SUPER_ADMIN') OR hasRole('USER')")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AllRolePermission {
}
